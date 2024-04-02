package repositories;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entities.IEntity;
import entities.Role;

public class RoleRepository implements BaseRepository {
    static Logger logger = Logger.getLogger(RoleRepository.class.getName());

    private RoleRepository() {
        // Prevent instantiation
    }

    @Override
    public List<IEntity> getAll(Connection connection) throws SQLException {
        List<IEntity> list = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT \"ID\", \"RoleName\" FROM \"Role\"");

            while (resultSet.next()) {
                list.add(new Role(resultSet.getLong("ID"), resultSet.getString("RoleName")));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return list;
    }

    @Override
    public IEntity getById(Connection connection, int id) throws SQLException {
        String sql = "SELECT \"RoleName\" FROM \"Role\" WHERE \"ID\" = ?";
        Role role = new Role();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                role = new Role((long) id, resultSet.getString("RoleName"));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return role;
    }

    @Override
    public void insert(Connection connection, IEntity entity) throws SQLException {
        Role role = (Role) entity;
        String sql = "INSERT INTO \"Role\"(\"RoleName\") VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, role.getRoleName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void delete(Connection connection, int id) throws SQLException {

        String sql = "DELETE FROM \"Role\" WHERE \"ID\" = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void update(Connection connection, IEntity entity) throws SQLException {
        Role role = (Role) entity;
        String sql = "UPDATE \"Subscription\" SET \"RoleName\" = ? WHERE \"ID\" = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setLong(2, role.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
