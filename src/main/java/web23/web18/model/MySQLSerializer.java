package web23.web18.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface MySQLSerializer<T> {
    void serialize(PreparedStatement statement) throws SQLException;
}
