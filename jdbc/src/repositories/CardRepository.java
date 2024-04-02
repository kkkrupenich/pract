package repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entities.IEntity;
import entities.Card;

public class CardRepository implements BaseRepository {
    static Logger logger = Logger.getLogger(CardRepository.class.getName());

    private CardRepository() {
        // Prevent instantiation
    }

    @Override
    public List<IEntity> getAll(Connection connection) throws SQLException {
        List<IEntity> list = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT \"ID\", \"Number\",\"ExpirationDate\", \"HoldersName\", \"CVV\" FROM \"Card\"");

            while (resultSet.next()) {
                list.add(new Card(resultSet.getLong("ID"), resultSet.getString("Number"),
                        resultSet.getDate("ExpirationDate"), resultSet.getString("HoldersName"),
                        resultSet.getInt("CVV")));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return list;
    }

    @Override
    public IEntity getById(Connection connection, int id) throws SQLException {
        String sql = "SELECT \"ID\", \"Number\",\"ExpirationDate\", \"HoldersName\", \"CVV\" FROM \"Card\" WHERE \"ID\" = ?";
        Card card = new Card();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                card = new Card((long) id, resultSet.getString("Number"),
                        resultSet.getDate("ExpirationDate"), resultSet.getString("HoldersName"),
                        resultSet.getInt("CVV"));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return card;
    }

    @Override
    public void insert(Connection connection, IEntity entity) throws SQLException {
        Card card = (Card) entity;
        String sql = "INSERT INTO \"Card\"(\"Number\", \"ExpirationDate\", " +
                "\"HoldersName\", \"CVV\") VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, card.getNumber());
            preparedStatement.setDate(2, card.getExpirationDate());
            preparedStatement.setString(3, card.getHoldersName());
            preparedStatement.setInt(4, card.getCvv());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void delete(Connection connection, int id) throws SQLException {

        String sql = "DELETE FROM \"Card\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void update(Connection connection, IEntity entity) throws SQLException {
        Card card = (Card) entity;
        String sql = "UPDATE \"Card\" SET \"Number\" = ? WHERE \"ID\" = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, card.getNumber());
            preparedStatement.setLong(2, card.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
