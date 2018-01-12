package com.sabahtalateh.j4j.jdbc.vacancies_parser.model;

import java.time.LocalDateTime;

/**
 * VacancyRepo.
 */
public class Vacancy {

    private int vacancyId;
    private String title;
    private String link;
    private String authorName;
    private int answersCount;
    private int viewsCount;
    private VacancyStatus status;
    private LocalDateTime updatedAt;

    /**
     * @param vacancyId    vacancy id from site.
     * @param title        title.
     * @param link         link.
     * @param authorName   author name.
     * @param answersCount answers count.
     * @param viewsCount   views count.
     * @param status       status from site.
     * @param updatedAt    updated at on site.
     */
    public Vacancy(int vacancyId, String title, String link, String authorName,
                   int answersCount, int viewsCount, VacancyStatus status, LocalDateTime updatedAt) {
        this.vacancyId = vacancyId;
        this.title = title;
        this.link = link;
        this.authorName = authorName;
        this.answersCount = answersCount;
        this.viewsCount = viewsCount;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    /**
     * @return vacancy id.
     */
    public int getVacancyId() {
        return vacancyId;
    }

    /**
     * @return title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return link.
     */
    public String getLink() {
        return link;
    }

    /**
     * @return author name.
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * @return answers count.
     */
    public int getAnswersCount() {
        return answersCount;
    }

    /**
     * @return views count.
     */
    public int getViewsCount() {
        return viewsCount;
    }

    /**
     * @return status.
     */
    public VacancyStatus getStatus() {
        return status;
    }

    /**
     * @return updated at.
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
