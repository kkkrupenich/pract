package repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entities.Chance;
import entities.IEntity;

public class ChanceRepository implements BaseRepository {
    static Logger logger = Logger.getLogger(ChanceRepository.class.getName());

    private ChanceRepository() {
        // Prevent instantiation
    }

    @Override
    public List<IEntity> getAll(Connection connection) throws SQLException {
        List<IEntity> list = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT \"ID\", \"LoseChance\",\"ReturnChance\", \"WinChance\", FROM \"Chance\"");

            while (resultSet.next()) {
                list.add(new Chance(resultSet.getLong("ID"), resultSet.getDouble("LoseChance"),
                        resultSet.getDouble("ReturnChance"), resultSet.getDouble("WinChance")));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return list;
    }

    @Override
    public IEntity getById(Connection connection, int id) throws SQLException {
        String sql = "SELECT \"ID\", \"LoseChance\",\"ReturnChance\", \"WinChance\", FROM \"Chance\" WHERE \"ID\" = ?";
        Chance chance = new Chance();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                chance = new Chance((long) id, resultSet.getDouble("LoseChance"),
                        resultSet.getDouble("ReturnChance"), resultSet.getDouble("WinChance"));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return chance;
    }

    @Override
    public void insert(Connection connection, IEntity entity) throws SQLException {
        Chance chance = (Chance) entity;
        String sql = "INSERT INTO \"Chance\"(\"LoseChance\", \"ReturnChance\", \"WinChance\") VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, chance.getLoseChance());
            preparedStatement.setDouble(2, chance.getReturnChance());
            preparedStatement.setDouble(3, chance.getWinChance());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void delete(Connection connection, int id) throws SQLException {

        String sql = "DELETE FROM \"Chance\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void update(Connection connection, IEntity entity) throws SQLException {
        Chance chance = (Chance) entity;
        String sql = "UPDATE \"Chance\" SET \"LoseChance\" = ? WHERE \"ID\" = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, chance.getLoseChance());
            preparedStatement.setLong(2, chance.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
