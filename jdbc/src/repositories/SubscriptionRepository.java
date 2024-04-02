package repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entities.IEntity;
import entities.Subscription;

public class SubscriptionRepository implements BaseRepository {
    static Logger logger = Logger.getLogger(SubscriptionRepository.class.getName());

    public SubscriptionRepository() {
        // Prevent instantiation
    }

    @Override
    public List<IEntity> getAll(Connection connection) throws SQLException {
        List<IEntity> list = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT \"ID\", \"Status\", \"ExpirationDate\" FROM \"Subscription\"");

            while (resultSet.next()) {
                list.add(new Subscription(resultSet.getLong("ID"), resultSet.getBoolean("ExpirationDate"),
                        resultSet.getDate("ExpirationDate")));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return list;
    }

    @Override
    public IEntity getById(Connection connection, Long id) throws SQLException {
        String sql = "SELECT \"Status\", \"ExpirationDate\" FROM \"Subscription\" WHERE \"ID\" = ?";
        Subscription subscription = new Subscription();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                subscription = new Subscription(id, resultSet.getBoolean("ExpirationDate"),
                        resultSet.getDate("ExpirationDate"));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return subscription;
    }

    @Override
    public void insert(Connection connection, IEntity entity) throws SQLException {
        Subscription subscription = (Subscription) entity;
        String sql = "INSERT INTO \"Subscription\"(\"Status\", \"ExpirationDate\") VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, subscription.isStatus());
            preparedStatement.setDate(2, subscription.getExpirationDate());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void delete(Connection connection, Long id) throws SQLException {

        String sql = "DELETE FROM \"Subscription\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void update(Connection connection, IEntity entity) throws SQLException {
        Subscription subscription = (Subscription) entity;
        String sql = "UPDATE \"Subscription\" SET \"Status\" = ? WHERE \"ID\" = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, subscription.isStatus());
            preparedStatement.setLong(2, subscription.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
