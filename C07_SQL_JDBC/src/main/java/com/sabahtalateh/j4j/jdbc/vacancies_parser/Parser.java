package com.sabahtalateh.j4j.jdbc.vacancies_parser;

import com.sabahtalateh.j4j.jdbc.vacancies_parser.model.Vacancy;
import com.sabahtalateh.j4j.jdbc.vacancies_parser.model.VacancyStatus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.String.format;

/**
 * Parser.
 */
class Parser {

    private RussianMothsToLocalDateMothsMapper mothsMapper = new RussianMothsToLocalDateMothsMapper();

    /**
     * @param since since date.
     * @return vacancies list.
     */
    List<Vacancy> fetchJavaVacancies(LocalDateTime since) {

        List<Vacancy> vacancies = new ArrayList<>();

        Pattern javaPattern = Pattern.compile(".*java(?! ?script).*", Pattern.CASE_INSENSITIVE);
        out:
        for (int pageNum = 1; true; pageNum++) {
            try {
                Document doc = Jsoup.connect(format("http://www.sql.ru/forum/job-offers/%s", pageNum)).get();

                for (Element tableRow : doc.body().getElementsByClass("forumTable").get(0)
                        .getElementsByTag("tr")) {
                    List<Element> innerTds = tableRow.getElementsByTag("td");

                    if (innerTds.size() == 0) {
                        continue;
                    }

                    if (innerTds.get(1).text().startsWith("Важно")) {
                        continue;
                    }

                    // updated
                    String dateTimeFromSite = innerTds.get(5).text();
                    LocalDateTime updated = this.extractUpdatedDate(dateTimeFromSite);

                    if (updated.compareTo(since) < 1) {
                        break out;
                    }

                    // title
                    String title = innerTds.get(1).getElementsByTag("a").get(0).text();

                    // continue if there is some kind of javascript or java script in title
                    if (!javaPattern.matcher(title).matches()) {
                        continue;
                    }

                    // vacancy id
                    int vacancyId = Integer.valueOf(innerTds.get(1).getElementsByTag("a").get(0)
                            .attr("href").split("/")[4]);

                    // link
                    String link = innerTds.get(1).getElementsByTag("a").get(0).attr("href");

                    // author
                    String author = innerTds.get(2).text().trim();

                    // answers count
                    int answersCount = Integer.valueOf(innerTds.get(3).text().trim());

                    // views count
                    int viewsCount = Integer.valueOf(innerTds.get(4).text().trim());

                    // status
                    VacancyStatus status = innerTds.get(1).text().contains("[закрыт]")
                            ? VacancyStatus.CLOSED
                            : VacancyStatus.OPENED;

                    vacancies.add(new Vacancy(vacancyId, title, link, author, answersCount, viewsCount, status, updated));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return vacancies;
    }

    /**
     * @param updatedString updated as string.
     * @return date.
     */
    private LocalDateTime extractUpdatedDate(String updatedString) {
        String[] dateTimeParts = updatedString.split(",");
        String date = dateTimeParts[0].trim();
        String[] timeParts = dateTimeParts[1].split(":");
        int hours = Integer.valueOf(timeParts[0].trim());
        int minutes = Integer.valueOf(timeParts[1].trim());
        LocalDateTime updated;
        if ("сегодня".equals(date)) {
            updated = LocalDateTime.of(LocalDate.now(), LocalTime.of(hours, minutes));
        } else if ("вчера".equals(date)) {
            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.of(hours, minutes);

            updated = LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth() - 1,
                    localTime.getHour(), localTime.getMinute());
        } else {
            String[] dateParts = date.split(" ");
            int year = Integer.valueOf(dateParts[2]) + 2000;
            Month month = this.mothsMapper.map(dateParts[1]);
            int dayOfMonth = Integer.valueOf(dateParts[0]);

            updated = LocalDateTime.of(year, month, dayOfMonth, hours, minutes);
        }

        return updated;
    }
}
