package repositories;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;

import entities.IEntity;

public interface BaseRepository {
    List<IEntity> getAll(Connection connection) throws SQLException;
    IEntity getById(Connection connection, int id) throws SQLException;
    void insert(Connection connection, IEntity entity) throws SQLException;
    void delete(Connection connection, int id) throws SQLException;
    void update(Connection connection, IEntity entity) throws SQLException;
}
