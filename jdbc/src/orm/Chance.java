package orm;

import java.sql.*;
import java.util.logging.Logger;

public class Chance {
    static Logger logger = Logger.getLogger(Chance.class.getName());

    private Chance() {
        // Prevent instantiation
    }

    public static void selectChance(Connection connection) throws Exception {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT \"ID\", \"LoseChance\" FROM \"Chance\"");

            while (resultSet.next()) {
                double columnValue = resultSet.getDouble("LoseChance");
                logger.info(String.valueOf(columnValue));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void insertChance(Connection connection, double loseChance, double returnChance, double winChance)
            throws Exception {

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

    public static void deleteChance(Connection connection, int id) throws Exception {

        String sql = "DELETE FROM \"Chance\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void updateChance(Connection connection, double loseChance, double returnChance, double winChance,
            int id) throws Exception {

        String sql = "UPDATE \"Subscription\" SET \"LoseChance\" = ?, \"ReturnChance\" = ?, \"WinChance\" = ? WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, loseChance);
            preparedStatement.setDouble(2, returnChance);
            preparedStatement.setDouble(3, winChance);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
