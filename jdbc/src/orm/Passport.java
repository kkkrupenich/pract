package orm;
import java.sql.*;
import java.util.logging.Logger;

public class Passport {
    static Logger logger = Logger.getLogger(Card.class.getName());

    private Passport() {
        // Prevent instantiation
    }

    public static void selectPassport(Connection connection) throws Exception {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT \"ID\", \"SerialNumber\" FROM \"Passport\"");

            while (resultSet.next()) {
                int columnId = resultSet.getInt("ID");
                String columnValue = resultSet.getString("SerialNumber");
                logger.info(columnId + " " + columnValue);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getSQLState());
        }
    }

    public static void insertPassport(Connection connection, String serialNumber,
            String identificationNumber, String registration, Date issueDate,
            Date expirationDate) throws Exception {

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
            throw new SQLException(e.getSQLState());
        }
    }

    public static void deletePassport(Connection connection, int id) throws Exception {

        String sql = "DELETE FROM \"Passport\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getSQLState());
        }
    }

    public static void updatePassportSerialNumber(Connection connection, String serialNumber, int id) throws Exception {

        String sql = "UPDATE \"Passport\" SET \"SerialNumber\" = ? WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, serialNumber);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getSQLState());
        }
    }
}
