import java.sql.Connection;
import java.sql.DriverManager;
import orm.*;

public class App {
    public static void main(String[] args) throws Exception {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/casino";
        String username = "postgres";
        String password = "blessed";

        Class.forName("org.postgresql.Driver");

        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

        System.out.println("Roles:");
        Role.selectRoles(connection);

        System.out.println("Passports:");
        Passport.selectPassport(connection);

        connection.close();
    }
}
