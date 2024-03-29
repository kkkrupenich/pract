package orm;
import java.sql.*;
import java.util.logging.Logger;

public class Passport {
    static Logger logger = Logger.getLogger(Passport.class.getName());

    private Passport() {
        // Prevent instantiation
    }

    public static void selectPassport(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT \"ID\", \"SerialNumber\" FROM \"Passport\"");

            while (resultSet.next()) {
                String columnValue = resultSet.getString("SerialNumber");
                logger.info(columnValue);
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void insertPassport(Connection connection, String serialNumber,
            String identificationNumber, String registration, Date issueDate,
            Date expirationDate) throws SQLException {

        String sql = "INSERT INTO \"Passport\"(\"SerialNumber\", \"IdentificationNumber\", " +
                "\"Registration\", \"IssueDate\", \"ExpirationDate\") VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, serialNumber);
            preparedStatement.setString(2, identificationNumber);
            preparedStatement.setString(3, registration);
            preparedStatement.setDate(4, issueDate);
            preparedStatement.setDate(5, expirationDate);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void deletePassport(Connection connection, int id) throws SQLException {

        String sql = "DELETE FROM \"Passport\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void updatePassportSerialNumber(Connection connection, String serialNumber, int id) throws SQLException {

        String sql = "UPDATE \"Passport\" SET \"SerialNumber\" = ? WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, serialNumber);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
