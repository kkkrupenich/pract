package repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entities.Passport;
import entities.IEntity;

public class PassportRepository implements BaseRepository {
    static Logger logger = Logger.getLogger(PassportRepository.class.getName());

    public PassportRepository() {
        // Prevent instantiation
    }

    @Override
    public List<IEntity> getAll(Connection connection) throws SQLException {
        List<IEntity> list = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT \"ID\", \"SerialNumber\", \"IdentificationNumber\", \"Registration\", \"IssueDate\", \"ExpirationDate\" FROM \"Passport\"");

            while (resultSet.next()) {
                list.add(new Passport(resultSet.getLong("ID"), resultSet.getString("SerialNumber"),
                        resultSet.getString("IdentificationNumber"), resultSet.getString("Registration"),
                        resultSet.getDate("IssueDate"), resultSet.getDate("ExpirationDate")));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return list;
    }

    @Override
    public IEntity getById(Connection connection, Long id) throws SQLException {
        String sql = "SELECT \"ID\", \"SerialNumber\", \"IdentificationNumber\", \"Registration\", \"IssueDate\", \"ExpirationDate\" FROM \"Passport\" WHERE \"ID\" = ?";
        Passport passport = new Passport();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                passport = new Passport(id, resultSet.getString("SerialNumber"),
                        resultSet.getString("IdentificationNumber"), resultSet.getString("Registration"),
                        resultSet.getDate("IssueDate"), resultSet.getDate("ExpirationDate"));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return passport;
    }

    @Override
    public void insert(Connection connection, IEntity entity) throws SQLException {
        Passport passport = (Passport) entity;
        String sql = "INSERT INTO \"Passport\"(\"SerialNumber\", \"IdentificationNumber\", \"Registration\", \"IssueDate\", \"ExpirationDate\") VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, passport.getSerialNumber());
            preparedStatement.setString(2, passport.getIdentificationNumber());
            preparedStatement.setString(3, passport.getRegistration());
            preparedStatement.setDate(4, passport.getIssueDate());
            preparedStatement.setDate(5, passport.getExpirationDate());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void delete(Connection connection, Long id) throws SQLException {

        String sql = "DELETE FROM \"Passport\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void update(Connection connection, IEntity entity) throws SQLException {
        Passport passport = (Passport) entity;
        String sql = "UPDATE \"Passport\" SET \"Registration\" = ? WHERE \"ID\" = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, passport.getRegistration());
            preparedStatement.setLong(2, passport.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
