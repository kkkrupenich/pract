package orm;

import java.sql.*;

public class Game {
    
    public static void selectGame(Connection connection) throws Exception {
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Game\"");

        while (resultSet.next()) {
            int columnId = resultSet.getInt("ID");
            String columnValue = resultSet.getString("Name");
            System.out.println(columnId + " " + columnValue);
        }
    }

    public static void insertGame(Connection connection, String name, Boolean status, int id, double minBet,
            double maxBet) throws Exception {

        String sql = "INSERT INTO \"Game\"(\"Name\", \"PremiumStatus\", \"ChanceID\", \"MinimalBet\", \"MaximumBet\") VALUES (?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, name);
        preparedStatement.setBoolean(2, status);
        preparedStatement.setInt(3, id);
        preparedStatement.setDouble(4, minBet);
        preparedStatement.setDouble(5, maxBet);

        preparedStatement.executeUpdate();
    }

    public static void deleteGame(Connection connection, int id) throws Exception {

        String sql = "DELETE FROM \"Game\" WHERE \"ID\" = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
    }

    public static void updateGameStatus(Connection connection, Boolean status, int id) throws Exception {

        String sql = "UPDATE \"Game\" SET \"PremiumStatus\" = ? WHERE \"ID\" = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setBoolean(1, status);
        preparedStatement.setInt(2, id);

        preparedStatement.executeUpdate();
    }
}
