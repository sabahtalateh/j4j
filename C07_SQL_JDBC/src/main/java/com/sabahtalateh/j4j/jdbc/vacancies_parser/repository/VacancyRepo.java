package com.sabahtalateh.j4j.jdbc.vacancies_parser.repository;

import com.sabahtalateh.j4j.jdbc.vacancies_parser.DBConnectionManager;
import com.sabahtalateh.j4j.jdbc.vacancies_parser.model.Vacancy;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * VacancyRepo.
 */
public class VacancyRepo {

    private DBConnectionManager DBconnectionManager;

    /**
     * @param DBConnectionManager connection manager.
     */
    public VacancyRepo(DBConnectionManager DBConnectionManager) {
        this.DBconnectionManager = DBConnectionManager;
    }

    /**
     * @return last updated date.
     */
    public LocalDateTime getLastUpdateDate() {
        try (Connection connection = this.DBconnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT updated_at FROM vacancy ORDER BY updated_at DESC LIMIT 1");

            if (!resultSet.next()) {
                return null;
            }

            return resultSet.getTimestamp(1).toLocalDateTime();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param vacancies vacancies.
     */
    public void save(List<Vacancy> vacancies) {
        try (Connection connection = this.DBconnectionManager.getConnection()) {

            if (vacancies.size() == 0) {
                return;
            }

            // Remove vacancies which are in vacancies list because of vacancy_id is uniques field.
            List<Integer> vacancyIds = vacancies.stream().map(Vacancy::getVacancyId).collect(Collectors.toList());
            // Make (?,?...?) string to add it to prepared statement.
            String questionSigns = String.join(",", Collections.nCopies(vacancyIds.size(), "?"));
            PreparedStatement removeExistingVacanciesStatement = connection.prepareStatement(format("DELETE FROM vacancy"
                    + " WHERE vacancy_id IN (%s)", questionSigns));
            // Set existing vacancies ids.
            int i = 1;
            for (Integer vacancyId : vacancyIds) {
                removeExistingVacanciesStatement.setInt(i, vacancyId);
                i++;
            }
            removeExistingVacanciesStatement.execute();

            // Insert all the passed vacancies into database.
            connection.setAutoCommit(false);
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO vacancy(vacancy_id, title, link, author_name, status, updated_at, answers_count, views_count) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            for (Vacancy v : vacancies) {
                insertStatement.setInt(1, v.getVacancyId());
                insertStatement.setString(2, v.getTitle());
                insertStatement.setString(3, v.getLink());
                insertStatement.setString(4, v.getAuthorName());
                insertStatement.setString(5, v.getStatus().getName());
                insertStatement.setTimestamp(6, Timestamp.valueOf(v.getUpdatedAt()));
                insertStatement.setInt(7, v.getAnswersCount());
                insertStatement.setInt(8, v.getViewsCount());
                insertStatement.addBatch();
            }
            insertStatement.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
