package orm;

import java.sql.*;

public class Review {
     
    public static void selectReview(Connection connection) throws Exception {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Review\"");

            while (resultSet.next()) {
                int columnId = resultSet.getInt("ID");
                String columnValue = resultSet.getString("Message");
                System.out.println(columnId + " " + columnValue);
            }
        }
    }

    public static void insertReview(Connection connection, int userId, int gameId, String message, String rating,
            Date date) throws Exception {

        String sql = "INSERT INTO \"Review\"(\"UserID\", \"GameID\", \"Message\", \"Rating\", \"Date\") VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, gameId);
            preparedStatement.setString(3, message);
            preparedStatement.setString(4, rating);
            preparedStatement.setDate(5, date);

            preparedStatement.executeUpdate();
        }
    }

    public static void deleteReview(Connection connection, int id) throws Exception {

        String sql = "DELETE FROM \"Review\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
    }

    public static void updateReview(Connection connection, String message, int id) throws Exception {

        String sql = "UPDATE \"User\" SET \"Message\" = ? WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, message);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        }
    }
}
