package repositories;

import java.sql.*;
import java.util.logging.Logger;

public class ChanceRepository {
    static Logger logger = Logger.getLogger(ChanceRepository.class.getName());

    private ChanceRepository() {
        // Prevent instantiation
    }

    public static void selectChance(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT \"ID\", \"LoseChance\" FROM \"Chance\"");

            while (resultSet.next()) {
                String columnValue = resultSet.getString("LoseChance");
                logger.info(columnValue);
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void insertChance(Connection connection, double loseChance, double returnChance, double winChance)
            throws SQLException {

        String sql = "INSERT INTO \"Chance\"(\"LoseChance\",\"ReturnChance\", \"WinChance\") VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, loseChance);
            preparedStatement.setDouble(2, returnChance);
            preparedStatement.setDouble(3, winChance);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void deleteChance(Connection connection, int id) throws SQLException {

        String sql = "DELETE FROM \"Chance\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void updateChance(Connection connection, double loseChance, double returnChance, 
                                    double winChance, int id) throws SQLException {

        String sql = "UPDATE \"Subscription\" SET \"LoseChance\" = ?, \"ReturnChance\" = ?, \"WinChance\" = ? WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, loseChance);
            preparedStatement.setDouble(2, returnChance);
            preparedStatement.setDouble(3, winChance);
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
