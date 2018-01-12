package com.sabahtalateh.j4j.jdbc.vacancies_parser.model;

/**
 * VacancyStatus.
 */
public enum VacancyStatus {
    OPENED("Opened"),
    CLOSED("Closed");

    private String name;

    /**
     * @param name name.
     */
    VacancyStatus(String name) {
        this.name = name;
    }

    /**
     * @return name.
     */
    public String getName() {
        return name;
    }
}
