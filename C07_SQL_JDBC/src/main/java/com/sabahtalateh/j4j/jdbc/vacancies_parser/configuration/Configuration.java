package com.sabahtalateh.j4j.jdbc.vacancies_parser.configuration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration.
 */
public final class Configuration {

    private Connection connection;

    private List<String> fetchTimeString;

    private List<LocalTime> fetchTime = new ArrayList<>();

    private boolean fetchOnStart;

    private String log4jPropsFile;

    /**
     * @return connection.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param connection connection.
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * @return fetch time as strings.
     */
    public List<String> getFetchTimeString() {
        return fetchTimeString;
    }

    /**
     * @param fetchTimeString fetch time as strings.
     */
    public void setFetchTimeString(List<String> fetchTimeString) {
        this.fetchTimeString = fetchTimeString;
        for (String time : this.fetchTimeString) {
            this.fetchTime.add(LocalTime.parse(time));
        }
    }

    /**
     * @return fetch time as LocalDates.
     */
    public List<LocalTime> getFetchTime() {
        return fetchTime;
    }

    /**
     * @return bool.
     */
    public boolean isFetchOnStart() {
        return fetchOnStart;
    }

    /**
     * @param fetchOnStart fetch on start.
     */
    public void setFetchOnStart(boolean fetchOnStart) {
        this.fetchOnStart = fetchOnStart;
    }

    /**
     * @return log4j props file path.
     */
    public String getLog4jPropsFile() {
        return log4jPropsFile;
    }

    /**
     * @param log4jPropsFile log4j props file path.
     */
    public void setLog4jPropsFile(String log4jPropsFile) {
        this.log4jPropsFile = log4jPropsFile;
    }
}
