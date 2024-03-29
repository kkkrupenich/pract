package orm;

import java.sql.*;
import java.util.logging.Logger;

public class Game {
    static Logger logger = Logger.getLogger(Game.class.getName());

    private Game() {
        // Prevent instantiation
    }

    public static void selectGame(Connection connection) throws Exception {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT \"ID\", \"Name\" FROM \"Game\"");

            while (resultSet.next()) {
                int columnId = resultSet.getInt("ID");
                String columnValue = resultSet.getString("Name");
                logger.info(columnId + " " + columnValue);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getSQLState());
        }
    }

    public static void insertGame(Connection connection, String name, Boolean status, int id, double minBet,
            double maxBet) throws Exception {

        String sql = "INSERT INTO \"Game\"(\"Name\", \"PremiumStatus\", \"ChanceID\", \"MinimalBet\", \"MaximumBet\") VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setBoolean(2, status);
            preparedStatement.setInt(3, id);
            preparedStatement.setDouble(4, minBet);
            preparedStatement.setDouble(5, maxBet);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getSQLState());
        }
    }

    public static void deleteGame(Connection connection, int id) throws Exception {

        String sql = "DELETE FROM \"Game\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getSQLState());
        }
    }

    public static void updateGameStatus(Connection connection, Boolean status, int id) throws Exception {

        String sql = "UPDATE \"Game\" SET \"PremiumStatus\" = ? WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, status);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getSQLState());
        }
    }
}
