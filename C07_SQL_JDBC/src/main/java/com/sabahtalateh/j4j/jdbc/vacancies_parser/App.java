package com.sabahtalateh.j4j.jdbc.vacancies_parser;

import com.sabahtalateh.j4j.jdbc.vacancies_parser.configuration.Configuration;
import com.sabahtalateh.j4j.jdbc.vacancies_parser.model.Vacancy;
import com.sabahtalateh.j4j.jdbc.vacancies_parser.repository.VacancyRepo;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.String.format;
import static java.time.temporal.ChronoUnit.MILLIS;

/**
 * App.
 */
class App {

    private Configuration configuration = null;

    private DBConnectionManager DBConnectionManagerManager = null;

    private RussianMothsToLocalDateMothsMapper mothsMapper = new RussianMothsToLocalDateMothsMapper();

    private Parser parser = new Parser();

    private VacancyRepo vacancyRepo;

    private static final LocalDateTime START_DATE = LocalDateTime.now().minusDays(100);

    private static Logger logger = Logger.getLogger(App.class);

    /**
     * @throws InterruptedException exception.
     * @throws IOException          exception.
     */
    public void start() throws InterruptedException, IOException {
        // Load config.
        this.loadConfig();

        // Configure log4j.
        Properties properties = new Properties();
        properties.load(new FileInputStream(this.configuration.getLog4jPropsFile()));
        PropertyConfigurator.configure(properties);

        // Create connection manager.
        this.createConnectionManager();

        logger.info("App started.");

        this.vacancyRepo = new VacancyRepo(this.DBConnectionManagerManager);

        if (this.configuration.isFetchOnStart()) {
            this.fetch();
        }

        if (!this.configuration.getFetchTime().isEmpty()) {
            while (true) {
                synchronized (this) {
                    this.wait(this.getMillisUntilNextFetch());
                }
                this.fetch();
            }
        }
        logger.info("App finished.");
    }

    /**
     * Load app configuration.
     */
    private void loadConfig() {
        Yaml yaml = new Yaml();
        try (InputStream in = Files.newInputStream(Paths.get("src/main/resources/vacancies_parser/parser_params.yml"))) {
            this.configuration = yaml.loadAs(in, Configuration.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create connection manager.
     */
    private void createConnectionManager() {
        this.DBConnectionManagerManager = new DBConnectionManager(
                this.configuration.getConnection().getDriver(),
                this.configuration.getConnection().getHost(),
                this.configuration.getConnection().getDbName(),
                this.configuration.getConnection().getDbUser(),
                this.configuration.getConnection().getDbPass()
        );
    }

    /**
     * @return millis until next fetch.
     */
    private long getMillisUntilNextFetch() {
        List<LocalTime> schedule = this.configuration.getFetchTime();
        List<Long> waitPeriods = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (LocalTime time : schedule) {
            LocalDateTime nextFetch = LocalDateTime.of(LocalDate.now(), time);
            if (Duration.between(now, nextFetch).isNegative()) {
                nextFetch = LocalDateTime.of(nextFetch.getYear(), nextFetch.getMonth(), nextFetch.getDayOfMonth() + 1,
                        nextFetch.getHour(), nextFetch.getMinute(), nextFetch.getSecond());
            }
            waitPeriods.add(Duration.between(now, nextFetch).toMillis());
        }

        Long millisUntilNextFetch = waitPeriods.stream().min(Long::compareTo).orElse(0L);
        logger.info(format("Next fetch scheduled on %s",
                now.plus(millisUntilNextFetch, MILLIS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));

        return millisUntilNextFetch;
    }

    /**
     * Fetch.
     */
    private void fetch() {
        logger.info("Fetch started.");
        LocalDateTime since = this.vacancyRepo.getLastUpdateDate();
        if (since == null) {
            since = START_DATE;
        }

        List<Vacancy> vacancies = this.parser.fetchJavaVacancies(since);
        this.vacancyRepo.save(vacancies);
        logger.info(format("Fetch finished [%s vacancies added].", vacancies.size()));
    }
}
