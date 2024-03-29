package orm;

import java.sql.*;
import java.util.logging.Logger;

public class User {
    static Logger logger = Logger.getLogger(User.class.getName());

    private User() {
        // Prevent instantiation
    }

    public static void selectUsers(Connection connection) throws Exception {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT \"ID\", \"FIO\" FROM \"User\"");

            while (resultSet.next()) {
                String columnValue = resultSet.getString("FIO");
                logger.info(columnValue);
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void insertUser(Connection connection, String email, String password, String fio, int passId,
            int roleId) throws Exception {

        String sql = "INSERT INTO \"User\"(\"Email\", \"Password\", \"FIO\", \"PassportID\", \"RoleID\", \"Balance\",) VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, fio);
            preparedStatement.setInt(4, passId);
            preparedStatement.setInt(5, roleId);
            preparedStatement.setDouble(6, 0.0);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void deleteUser(Connection connection, int id) throws Exception {

        String sql = "DELETE FROM \"User\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public static void updateUser(Connection connection, String fio, int id) throws Exception {

        String sql = "UPDATE \"User\" SET \"FIO\" = ? WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, fio);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
