package orm;
import java.sql.*;
import java.util.logging.Logger;

public class Role {
    static Logger logger = Logger.getLogger(Role.class.getName());

    private Role() {
        // Prevent instantiation
    }

    public static void selectRoles(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT \"ID\", \"RoleName\" FROM \"Role\"");

            while (resultSet.next()) {
                String columnValue = resultSet.getString("RoleName");
                logger.info(columnValue);
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void insertRoles(Connection connection, String name) throws SQLException {

        String sql = "INSERT INTO \"Role\"(\"RoleName\") VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void insertRolesWithID(Connection connection, String name, int id) throws SQLException {

        String sql = "INSERT INTO \"Role\"(\"ID\",\"RoleName\") VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void deleteRoles(Connection connection, String name) throws SQLException {

        String sql = "DELETE FROM \"Role\" WHERE \"RoleName\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void updateRoles(Connection connection, String name, int id) throws SQLException {

        String sql = "UPDATE \"Role\" SET \"RoleName\" = ? WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
