package orm;
import java.sql.*;

public class Role {
    
    public static void selectRoles(Connection connection) throws Exception {
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Role\"");

        while (resultSet.next()) {
            int columnId = resultSet.getInt("ID");
            String columnValue = resultSet.getString("RoleName");
            System.out.println(columnId + " " + columnValue);
        }
    }

    public static void insertRoles(Connection connection, String name) throws Exception {

        String sql = "INSERT INTO \"Role\"(\"RoleName\") VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
    }

    public static void insertRolesWithID(Connection connection, String name, int id) throws Exception {

        String sql = "INSERT INTO \"Role\"(\"ID\",\"RoleName\") VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.executeUpdate();
    }

    public static void deleteRoles(Connection connection, String name) throws Exception {

        String sql = "DELETE FROM \"Role\" WHERE \"RoleName\" = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, name);

        preparedStatement.executeUpdate();
    }

    public static void updateRoles(Connection connection, String name, int id) throws Exception {

        String sql = "UPDATE \"Role\" SET \"RoleName\" = ? WHERE \"ID\" = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, id);

        preparedStatement.executeUpdate();
    }
}
