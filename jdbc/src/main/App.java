package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;
import java.util.Properties;
import orm.*;

public class App {
    private static final String PROPERTIES_FILE = "db.properties";
    static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            logger.info(e.toString());
        }

        String jdbcUrl = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

        Logger logger = Logger.getLogger(App.class.getName());

        logger.info("Roles:");
        Role.selectRoles(connection);

        logger.info("Passports:");
        Passport.selectPassport(connection);

        connection.close();
    }
}
