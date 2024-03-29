package orm;
import java.sql.*;
import java.util.logging.Logger;

public class Role {
    static Logger logger = Logger.getLogger(Review.class.getName());

    private Role() {
        // Prevent instantiation
    }

    public static void selectRoles(Connection connection) throws Exception {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT \"ID\", \"RoleName\" FROM \"Role\"");

            while (resultSet.next()) {
                int columnId = resultSet.getInt("ID");
                String columnValue = resultSet.getString("RoleName");
                logger.info(columnId + " " + columnValue);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getSQLState());
        }
    }

    public static void insertRoles(Connection connection, String name) throws Exception {

        String sql = "INSERT INTO \"Role\"(\"RoleName\") VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getSQLState());
        }
    }

    public static void insertRolesWithID(Connection connection, String name, int id) throws Exception {

        String sql = "INSERT INTO \"Role\"(\"ID\",\"RoleName\") VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getSQLState());
        }
    }

    public static void deleteRoles(Connection connection, String name) throws Exception {

        String sql = "DELETE FROM \"Role\" WHERE \"RoleName\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getSQLState());
        }
    }

    public static void updateRoles(Connection connection, String name, int id) throws Exception {

        String sql = "UPDATE \"Role\" SET \"RoleName\" = ? WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getSQLState());
        }
    }
}
