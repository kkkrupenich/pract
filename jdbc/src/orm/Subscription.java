package orm;

import java.sql.*;

public class Subscription {

    public static void selectSubscription(Connection connection) throws Exception {
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Subscription\"");

        while (resultSet.next()) {
            int columnId = resultSet.getInt("ID");
            Boolean columnValue = resultSet.getBoolean("Status");
            System.out.println(columnId + " " + columnValue);
        }
    }

    public static void insertSubscription(Connection connection, Boolean status, Date expirationDate) throws Exception {

        String sql = "INSERT INTO \"Subscription\"(\"Status\",\"ExpirationDate\") VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setBoolean(1, status);
        preparedStatement.setDate(2, expirationDate);

        preparedStatement.executeUpdate();
    }

    public static void deleteSubscription(Connection connection, int id) throws Exception {

        String sql = "DELETE FROM \"Subscription\" WHERE \"ID\" = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
    }

    public static void updateSubscriptionStatus(Connection connection, Boolean status, int id) throws Exception {

        String sql = "UPDATE \"Subscription\" SET \"Status\" = ? WHERE \"ID\" = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setBoolean(1, status);
        preparedStatement.setInt(2, id);

        preparedStatement.executeUpdate();
    }
}
