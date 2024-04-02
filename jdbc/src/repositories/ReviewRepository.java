package repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entities.IEntity;
import entities.Review;

public class ReviewRepository implements BaseRepository {
    static Logger logger = Logger.getLogger(ReviewRepository.class.getName());

    private ReviewRepository() {
        // Prevent instantiation
    }

    @Override
    public List<IEntity> getAll(Connection connection) throws SQLException {
        List<IEntity> list = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT \"ID\", \"UserID\", \"GameID\", \"Message\", \"Rating\", \"Date\" FROM \"Review\"");

            while (resultSet.next()) {
                list.add(new Review(resultSet.getLong("ID"), resultSet.getLong("UserID"),
                        resultSet.getLong("GameID"), resultSet.getString("Message"),
                        resultSet.getString("Rating"), resultSet.getDate("Date")));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return list;
    }

    @Override
    public IEntity getById(Connection connection, int id) throws SQLException {
        String sql = "SELECT \"UserID\", \"GameID\", \"Message\", \"Rating\", \"Date\" FROM \"Review\" WHERE \"ID\" = ?";
        Review review = new Review();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                review = new Review((long) id, resultSet.getLong("UserID"),
                        resultSet.getLong("GameID"), resultSet.getString("Message"),
                        resultSet.getString("Rating"), resultSet.getDate("Date"));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return review;
    }

    @Override
    public void insert(Connection connection, IEntity entity) throws SQLException {
        Review review = (Review) entity;
        String sql = "INSERT INTO \"Review\"(\"UserID\", \"GameID\", \"Message\", \"Rating\", \"Date\") VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, review.getUser());
            preparedStatement.setLong(2, review.getGame());
            preparedStatement.setString(3, review.getMessage());
            preparedStatement.setString(4, review.getRating());
            preparedStatement.setDate(5, review.getDate());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void delete(Connection connection, int id) throws SQLException {

        String sql = "DELETE FROM \"Review\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void update(Connection connection, IEntity entity) throws SQLException {
        Review review = (Review) entity;
        String sql = "UPDATE \"Review\" SET \"Message\" = ? WHERE \"ID\" = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, review.getMessage());
            preparedStatement.setLong(2, review.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
