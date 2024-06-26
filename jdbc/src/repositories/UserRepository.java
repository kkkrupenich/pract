package repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entities.IEntity;
import entities.User;

public class UserRepository implements BaseRepository {
    static Logger logger = Logger.getLogger(UserRepository.class.getName());

    public UserRepository() {
        // Prevent instantiation
    }

    @Override
    public List<IEntity> getAll(Connection connection) throws SQLException {
        List<IEntity> list = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT \"ID\", \"Email\", \"Password\", \"FIO\", \"PassportID\", \"RoleID\", \"Balance\", \"SubscriptionID\" FROM \"User\"");

            while (resultSet.next()) {
                User user = new User(resultSet.getLong("ID"), resultSet.getString("Email"),
                        resultSet.getString("Password"), resultSet.getString("FIO"),
                        resultSet.getLong("PassportID"), resultSet.getLong("RoleID"),
                        resultSet.getLong("SubscriptionID"));
                user.setBalance(resultSet.getDouble("Balance"));
                user.setCardsId(new ArrayList<>());
                list.add(user);
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return list;
    }

    @Override
    public IEntity getById(Connection connection, Long id) throws SQLException {
        String sql = "SELECT \"Email\", \"Password\", \"FIO\", \"PassportID\", \"RoleID\", \"Balance\", \"SubscriptionID\" FROM \"User\"; WHERE \"ID\" = ?";
        User user = new User();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                user = new User(id, resultSet.getString("Email"),
                        resultSet.getString("Password"), resultSet.getString("FIO"),
                        resultSet.getLong("PassportID"), resultSet.getLong("RoleID"),
                        resultSet.getLong("SubscriptionID"));
                user.setBalance(resultSet.getDouble("Balance"));
                user.setCardsId(new ArrayList<>());
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return user;
    }

    @Override
    public void insert(Connection connection, IEntity entity) throws SQLException {
        User user = (User) entity;
        String sql = "INSERT INTO \"User\"(\"Email\", \"Password\", \"FIO\", \"PassportID\", \"RoleID\", \"Balance\", \"SubscriptionID\") VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFio());
            preparedStatement.setLong(4, user.getPassportID());
            preparedStatement.setLong(5, user.getRoleID());
            preparedStatement.setDouble(6, user.getBalance());
            preparedStatement.setString(7, "null");

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
            return;
        }

        if (!user.getCards().isEmpty()) {
            for (Long card : user.getCards()) {
                Long id = (long) 0;
                try (Statement statement = connection.createStatement()) {
                    ResultSet resultSet = statement.executeQuery(
                            "SELECT \"ID\" FROM \"User\" ORDER BY \"ID\" DESC LIMIT 1;");

                    while (resultSet.next()) {
                        id = resultSet.getLong("ID");
                    }
                } catch (SQLException e) {
                    logger.info(e.toString());
                }

                sql = "INSERT INTO \"Card_User\"(\"CardID\", \"UserID\") VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setLong(1, card);
                    preparedStatement.setLong(2, id);

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    logger.info(e.toString());
                }
            }
        }
    }

    @Override
    public void delete(Connection connection, Long id) throws SQLException {

        String sql = "DELETE FROM \"User\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void update(Connection connection, IEntity entity) throws SQLException {
        User user = (User) entity;
        String sql = "UPDATE \"Subscription\" SET \"FIO\" = ? WHERE \"ID\" = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getFio());
            preparedStatement.setLong(2, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
