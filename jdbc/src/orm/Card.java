package orm;

import java.sql.*;
import java.util.logging.Logger;

public class Card {
    static Logger logger = Logger.getLogger(Card.class.getName());

    private Card() {
        // Prevent instantiation
    }

    public static void selectCard(Connection connection) throws Exception {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT \"ID\", \"Number\",\"ExpirationDate\", \"HolderName\", \"CVV\" FROM \"Card\"");

            while (resultSet.next()) {
                int columnId = resultSet.getInt("ID");
                String columnValue = resultSet.getString("Number");
                logger.info(columnId + " " + columnValue);
            }
        }
    }

    public static void insertCard(Connection connection, String number, Date expirationDate,
            String holdersName, String cvv, int userId) throws Exception {

        String sql = "INSERT INTO \"Card\"(\"Number\", \"ExpirationDate\", " +
                "\"HoldersName\", \"CVV\") VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, number);
            preparedStatement.setDate(2, expirationDate);
            preparedStatement.setString(3, holdersName);
            preparedStatement.setString(4, cvv);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getSQLState());
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
            throw new SQLException(e.getSQLState());
        }
    }

    public static void deleteCard(Connection connection, int id) throws Exception {

        String sql = "DELETE FROM \"Card\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getSQLState());
        }
    }

    public static void updateCard(Connection connection, String number, int id) throws Exception {

        String sql = "UPDATE \"Card\" SET \"Number\" = ? WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, number);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getSQLState());
        }
    }
}
