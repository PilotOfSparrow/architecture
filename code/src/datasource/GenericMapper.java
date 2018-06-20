package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

abstract class GenericMapper<T> {
    private final String CONNECTION_URL = "jdbc:sqlite:manproject.db";

    public abstract void add(T item);

    public abstract T get(int id);

    public abstract List<T> getAll();

    Connection connection;

    {
        try {
            connection = DriverManager.getConnection(CONNECTION_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (connection != null) {
            connection.close();
        }
    }
}
