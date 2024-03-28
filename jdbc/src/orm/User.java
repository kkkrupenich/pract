package orm;

import java.sql.*;

public class User {
    
    public static void selectUsers(Connection connection) throws Exception {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"User\"");

            while (resultSet.next()) {
                int columnId = resultSet.getInt("ID");
                String columnValue = resultSet.getString("FIO");
                System.out.println(columnId + " " + columnValue);
            }
        }
    }

    public static void insertUser(Connection connection, String email, String password, String FIO, int passId,
            int roleId) throws Exception {

        String sql = "INSERT INTO \"User\"(\"Email\", \"Password\", \"FIO\", \"PassportID\", \"RoleID\", \"Balance\",) VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, FIO);
            preparedStatement.setInt(4, passId);
            preparedStatement.setInt(5, roleId);
            preparedStatement.setDouble(6, 0.0);

            preparedStatement.executeUpdate();
        }
    }

    public static void deleteUser(Connection connection, int id) throws Exception {

        String sql = "DELETE FROM \"User\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
    }

    public static void updateUser(Connection connection, String FIO, int id) throws Exception {

        String sql = "UPDATE \"User\" SET \"FIO\" = ? WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, FIO);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        }
    }
}
