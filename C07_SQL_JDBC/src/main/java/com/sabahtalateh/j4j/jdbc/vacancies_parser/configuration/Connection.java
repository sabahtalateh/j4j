package com.sabahtalateh.j4j.jdbc.vacancies_parser.configuration;

/**
 * DBConnectionManager.
 */
public class Connection {

    private String driver;
    private String host;
    private String dbName;
    private String dbUser;
    private String dbPass;

    /**
     * @return driver.
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver driver.
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @return host.
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host host.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return db name.
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * @param dbName db name.
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * @return db user.
     */
    public String getDbUser() {
        return dbUser;
    }

    /**
     * @param dbUser db user.
     */
    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    /**
     * @return db pass.
     */
    public String getDbPass() {
        return dbPass;
    }

    /**
     * @param dbPass db pass.
     */
    public void setDbPass(String dbPass) {
        this.dbPass = dbPass;
    }
}
