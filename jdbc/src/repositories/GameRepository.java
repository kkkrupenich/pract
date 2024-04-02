package repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entities.IEntity;
import entities.Game;

public class GameRepository implements BaseRepository {
    static Logger logger = Logger.getLogger(GameRepository.class.getName());

    private GameRepository() {
        // Prevent instantiation
    }

    @Override
    public List<IEntity> getAll(Connection connection) throws SQLException {
        List<IEntity> list = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT \"ID\", \"Name\",\"PremiumStatus\", \"ChanceID\", \"MinimalBet\", \"MaximumBet\" FROM \"Game\"");

            while (resultSet.next()) {
                list.add(new Game(resultSet.getLong("ID"), resultSet.getString("Name"),
                        resultSet.getBoolean("PremiumStatus"), resultSet.getLong("ChanceID"),
                        resultSet.getDouble("MinimalBet"), resultSet.getDouble("MaximumBet")));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return list;
    }

    @Override
    public IEntity getById(Connection connection, int id) throws SQLException {
        String sql = "SELECT \"ID\", \"Name\",\"PremiumStatus\", \"ChanceID\", \"MinimalBet\", \"MaximumBet\" FROM \"Game\" WHERE \"ID\" = ?";
        Game game = new Game();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                game = new Game((long) id, resultSet.getString("Name"),
                        resultSet.getBoolean("PremiumStatus"), resultSet.getLong("ChanceID"),
                        resultSet.getDouble("MinimalBet"), resultSet.getDouble("MaximumBet"));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return game;
    }

    @Override
    public void insert(Connection connection, IEntity entity) throws SQLException {
        Game game = (Game) entity;
        String sql = "INSERT INTO \"Game\"(\"Name\", \"PremiumStatus\", \"ChanceID\"," +
                "\"MinimalBet\", \"MaximumBet\") VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, game.getName());
            preparedStatement.setBoolean(2, game.isPremiumStatus());
            preparedStatement.setLong(3, game.getChance());
            preparedStatement.setDouble(4, game.getMinimalBet());
            preparedStatement.setDouble(5, game.getMaximumBet());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void delete(Connection connection, int id) throws SQLException {

        String sql = "DELETE FROM \"Game\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void update(Connection connection, IEntity entity) throws SQLException {
        Game game = (Game) entity;
        String sql = "UPDATE \"Game\" SET \"PremiumStatus\" = ? WHERE \"ID\" = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, game.isPremiumStatus());
            preparedStatement.setLong(2, game.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
