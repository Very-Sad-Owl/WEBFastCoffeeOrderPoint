package by.epam.training.jwd.godot.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.epam.training.jwd.godot.dao.connection.ConnectionPool;
import by.epam.training.jwd.godot.dao.connection.ConnectionProvider;
import by.epam.training.jwd.godot.dao.connection.ecxeption.ConnectionPoolException;
import org.apache.log4j.Logger;

public class WebListener implements ServletContextListener {
    private final static Logger log = Logger.getLogger(ConnectionPool.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionProvider.getConnectionPool();
        } catch (ConnectionPoolException e) {
            log.error("Error while creating connection pool");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionProvider.getConnectionPool().dispose();
        } catch (ConnectionPoolException e) {
            log.error("Error while closing connection pool");
        }
    }
}