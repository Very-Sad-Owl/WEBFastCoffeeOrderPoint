package by.epam.training.jwd.godot.dao.connection;

import by.epam.training.jwd.godot.dao.connection.ecxeption.ConnectionPoolException;

public class ConnectionProvider {

    private static ConnectionPool instance;

    public static ConnectionPool getConnectionPool() throws ConnectionPoolException {
        if (instance == null) {
            try {
                instance = new ConnectionPool();
            } catch (ConnectionPoolException e) {
                throw new ConnectionPoolException(e);
            }
        }
        return instance;
    }

    public static void destroyConnectionPool() throws ConnectionPoolException {
        if (instance != null) {
            instance.dispose();
        }
    }

    private ConnectionProvider(){}
}
