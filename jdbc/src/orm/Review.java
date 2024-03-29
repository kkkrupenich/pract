package orm;

import java.sql.*;
import java.util.logging.Logger;

public class Review {
    static Logger logger = Logger.getLogger(Review.class.getName());

    private Review() {
        // Prevent instantiation
    }

    public static void selectReview(Connection connection) throws Exception {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT \"ID\", \"Message\" FROM \"Review\"");

            while (resultSet.next()) {
                String columnValue = resultSet.getString("Message");
                logger.info(columnValue);
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void insertReview(Connection connection, int userId, int gameId, String message, String rating,
            Date date) throws Exception {

        String sql = "INSERT INTO \"Review\"(\"UserID\", \"GameID\", \"Message\", \"Rating\", \"Date\") VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, gameId);
            preparedStatement.setString(3, message);
            preparedStatement.setString(4, rating);
            preparedStatement.setDate(5, date);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void deleteReview(Connection connection, int id) throws Exception {

        String sql = "DELETE FROM \"Review\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void updateReview(Connection connection, String message, int id) throws Exception {

        String sql = "UPDATE \"User\" SET \"Message\" = ? WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, message);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
