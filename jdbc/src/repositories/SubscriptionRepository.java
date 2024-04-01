package repositories;

import java.sql.*;
import java.util.logging.Logger;

public class SubscriptionRepository {
    static Logger logger = Logger.getLogger(SubscriptionRepository.class.getName());

    private SubscriptionRepository() {
        // Prevent instantiation
    }
    
    public static void selectSubscription(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT \"ID\", \"Status\" FROM \"Subscription\"");

            while (resultSet.next()) {
                String columnValue = resultSet.getString("Status");
                logger.info(columnValue);
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void insertSubscription(Connection connection, Boolean status, Date expirationDate) throws SQLException {

        String sql = "INSERT INTO \"Subscription\"(\"Status\",\"ExpirationDate\") VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, status);
            preparedStatement.setDate(2, expirationDate);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void deleteSubscription(Connection connection, int id) throws SQLException {

        String sql = "DELETE FROM \"Subscription\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void updateSubscriptionStatus(Connection connection, Boolean status, int id) throws SQLException {

        String sql = "UPDATE \"Subscription\" SET \"Status\" = ? WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, status);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
