package repositories;

import java.sql.*;
import java.util.logging.Logger;

public class CardRepository {
    static Logger logger = Logger.getLogger(CardRepository.class.getName());

    private CardRepository() {
        // Prevent instantiation
    }

    public static void selectCard(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT \"ID\", \"Number\",\"ExpirationDate\", \"HolderName\", \"CVV\" FROM \"Card\"");

            while (resultSet.next()) {
                String columnValue = resultSet.getString("Number");
                logger.info(columnValue);
            }
        }
    }

    public static void insertCard(Connection connection, String number, Date expirationDate,
            String holdersName, String cvv, int userId) throws SQLException {

        String sql = "INSERT INTO \"Card\"(\"Number\", \"ExpirationDate\", " +
                "\"HoldersName\", \"CVV\") VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, number);
            preparedStatement.setDate(2, expirationDate);
            preparedStatement.setString(3, holdersName);
            preparedStatement.setString(4, cvv);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        int id = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT TOP 1 \"ID\" FROM \"Card\" ORDER BY ID DESC");

            while (resultSet.next()) {
                id = resultSet.getInt("ID");
            }
        }

        sql = "INSERT INTO \"Card_User\"(\"CardID\", \"UserID\") VALUES (?, ?)";
        try (PreparedStatement preparedStatement2 = connection.prepareStatement(sql)) {
            preparedStatement2.setInt(1, id);
            preparedStatement2.setInt(2, userId);
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void deleteCard(Connection connection, int id) throws SQLException {

        String sql = "DELETE FROM \"Card\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void updateCard(Connection connection, String number, int id) throws SQLException {

        String sql = "UPDATE \"Card\" SET \"Number\" = ? WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, number);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
