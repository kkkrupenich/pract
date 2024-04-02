package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.lang.reflect.Field;
import java.util.logging.Logger;

import entities.*;
import repositories.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String PROPERTIES_FILE = "db.properties";
    static Logger logger = Logger.getLogger(App.class.getName());

    public static void printEntities(BaseRepository repository, Connection connection) throws SQLException {
        var listEntities = repository.getAll(connection);
        for (int i = 0; i < listEntities.size(); i++) {
            System.console().printf(String.format("%d. %s%n%n", i + 1, listEntities.get(i).toString()));
        }
    }

    public static void printEntities(String fieldName, Connection connection) throws SQLException {
        if (fieldName.startsWith("card")) {
            CardRepository cardRepository = new CardRepository();
            printEntities(cardRepository, connection);
        } else if (fieldName.startsWith("chance")) {
            ChanceRepository authorRepository = new ChanceRepository();
            printEntities(authorRepository, connection);
        } else if (fieldName.startsWith("game")) {
            GameRepository roleRepository = new GameRepository();
            printEntities(roleRepository, connection);
        } else if (fieldName.startsWith("passport")) {
            PassportRepository orderRepository = new PassportRepository();
            printEntities(orderRepository, connection);
        } else if (fieldName.startsWith("review")) {
            ReviewRepository genreRepository = new ReviewRepository();
            printEntities(genreRepository, connection);
        } else if (fieldName.startsWith("role")) {
            RoleRepository userRepository = new RoleRepository();
            printEntities(userRepository, connection);
        } else if (fieldName.startsWith("subscription")) {
            SubscriptionRepository userRepository = new SubscriptionRepository();
            printEntities(userRepository, connection);
        } else if (fieldName.startsWith("user")) {
            UserRepository userRepository = new UserRepository();
            printEntities(userRepository, connection);
        }
    }

    @SuppressWarnings("unchecked")
    public static void add(BaseRepository repository, IEntity entity, Connection connection)
            throws IllegalAccessException, ParseException, SQLException {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (var field : fields) {
            field.setAccessible(true);
            System.console().printf("Type " + field.getName());
            if (field.getType() == boolean.class) {
                System.console().printf(" (boolean)\n");
                String valueStr = scanner.next();
                var value = Boolean.parseBoolean(valueStr);
                field.set(entity, value);
            } else if (field.getType() == int.class) {
                System.console().printf(" (int number)\n");
                String valueStr = scanner.next();
                var value = Integer.parseInt(valueStr);
                field.set(entity, value);
            } else if (field.getType() == double.class) {
                System.console().printf(" (real number)\n");
                String valueStr = scanner.next();
                var value = Double.parseDouble(valueStr);
                field.set(entity, value);
            } else if (field.getType() == Long.class
                    && (field.getName().contains("Id") || field.getName().contains("ID"))) {
                System.console().printf(" (Long ID format)\n");
                printEntities(field.getName(), connection);
                var value = Long.parseLong(scanner.next());
                field.set(entity, value);
            } else if (field.getType() == Long.class && field.getName().equals("id")) {
                System.console().printf(" (Long ID format)\n");
                var value = Long.parseLong(scanner.next());
                field.set(entity, value);
            } else if (field.getType() == Long.class) {
                System.console().printf(" (long number)\n");
                String valueStr = scanner.next();
                var value = Long.parseLong(valueStr);
                field.set(entity, value);
            } else if (field.getType() == Date.class) {
                System.console().printf(" (date with dd-MM-yyyy format)\n");
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                var strValue = scanner.next();
                var value = new java.sql.Date(formatter.parse(strValue).getTime());
                field.set(entity, value);
            } else if (field.getType() == String.class) {
                System.console().printf(" (string)\n");
                var value = scanner.next();
                field.set(entity, value);
            } else if (field.getName().equals("cardsId")) {
                List<Long> cardsIds = (List<Long>) field.get(entity);
                if (cardsIds == null) {
                    cardsIds = new ArrayList<Long>();
                }
                System.console().printf(" (Long ID format)\n");
                printEntities(field.getName(), connection);
                while (true) {
                    System.console().printf("Type 'cancel' if you want to stop add cards\n");
                    var value = scanner.next();
                    if (value.equals("cancel")) {
                        break;
                    }
                    var valueLong = Long.parseLong(value);
                    cardsIds.add(valueLong);
                }
                field.set(entity, cardsIds);
            } else {
                System.console().printf("\nUnknown type\n");
            }
        }
        repository.insert(connection, entity);
    }

    @SuppressWarnings("unchecked")
    public static void update(BaseRepository repository, IEntity entity, Connection connection)
            throws IllegalAccessException, ParseException, SQLException {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (var field : fields) {
            if (field.getName().equals("id")) {
                continue;
            }
            field.setAccessible(true);
            System.console().printf("Type " + field.getName());
            if (field.getType() == boolean.class) {
                System.console().printf(" (boolean)\n");
                String valueStr = scanner.next();
                var value = Boolean.parseBoolean(valueStr);
                field.set(entity, value);
            } else if (field.getType() == int.class) {
                System.console().printf(" (int number)\n");
                String valueStr = scanner.next();
                var value = Integer.parseInt(valueStr);
                field.set(entity, value);
            } else if (field.getType() == double.class) {
                System.console().printf(" (real number)\n");
                String valueStr = scanner.next();
                var value = Double.parseDouble(valueStr);
                field.set(entity, value);
            } else if (field.getType() == Long.class
                    && (field.getName().contains("Id") || field.getName().contains("ID"))) {
                System.console().printf(" (Long ID format)\n");
                printEntities(field.getName(), connection);
                var value = Long.parseLong(scanner.next());
                field.set(entity, value);
            } else if (field.getType() == Long.class) {
                System.console().printf(" (long number)\n");
                String valueStr = scanner.next();
                var value = Long.parseLong(valueStr);
                field.set(entity, value);
            } else if (field.getType() == Date.class) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                System.console().printf(" (date with dd-MM-yyyy format)\n");
                var strValue = scanner.next();
                var value = new java.sql.Date(formatter.parse(strValue).getTime());
                field.set(entity, value);
            } else if (field.getType() == String.class) {
                System.console().printf(" (string)\n");
                var value = scanner.next();
                field.set(entity, value);
            } else if (field.getName().equals("cardsId")) {
                List<Long> cardsIds = (List<Long>) field.get(entity);

                if (cardsIds == null) {
                    cardsIds = new ArrayList<Long>();
                }

                System.console().printf(" (Long ID format)\n");
                printEntities(field.getName(), connection);
                while (true) {
                    System.console().printf("Type 'cancel' if you want to stop add cards\n");
                    var value = scanner.next();
                    if (value.equals("cancel")) {
                        break;
                    }
                    var valueLong = Long.parseLong(value);
                    cardsIds.add(valueLong);
                }
                field.set(entity, cardsIds);
            } else {
                System.console().printf("\nUnknown type\n");
            }
        }
        repository.update(connection, entity);
    }

    public static void drawMenu(BaseRepository repository, IEntity entity, Connection connection) throws Exception {
        try {
            System.console().printf("Choose action:\n");
            System.console().printf("1. Add\n");
            System.console().printf("2. Update\n");
            System.console().printf("3. Remove\n");
            System.console().printf("4. Show\n");
            String chosenNumberStr = scanner.next();
            var chosenNumber = Integer.parseInt(chosenNumberStr);
            switch (chosenNumber) {
                case 1:
                    add(repository, entity, connection);
                    break;
                case 2:
                    printEntities(repository, connection);
                    System.console().printf("\nType id for update\n");
                    Long id = Long.parseLong(scanner.next());
                    entity = repository.getById(connection, id);
                    update(repository, entity, connection);
                    break;
                case 3:
                    printEntities(repository, connection);
                    System.console().printf("\nType id for remove\n");
                    Long value = Long.parseLong(scanner.next());
                    repository.delete(connection, value);
                    break;
                case 4:
                    printEntities(repository, connection);
                    break;
                default:
                    return;
            }
        } catch (NumberFormatException e) {
            System.console().printf("Typed value not number, try again\n");
        } catch (IllegalArgumentException e) {
            System.console().printf(e.getMessage().toString());
            System.console().printf("Not correct format\n");
        } catch (ParseException e) {
            System.console().printf(e.getMessage().toString());
            System.console().printf("Not correct date format\n");
        } catch (SQLException e) {
            System.console().printf("ID not found in db or connection lost\n");
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            logger.warning(e.toString());
        }

        String jdbcUrl = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

        while (true) {
            try {
                System.console().printf("Choose repo:\n");
                System.console().printf("1. User\n");
                System.console().printf("2. Role\n");
                System.console().printf("3. Card\n");
                System.console().printf("4. Chance\n");
                System.console().printf("5. Game:\n");
                System.console().printf("6. Passport:\n");
                System.console().printf("7. Review:\n");
                System.console().printf("8. Subscription:\n");
                System.console().printf("Type any other number to end.\n");
                String chosenNumberStr = scanner.next();
                var chosenNumber = Integer.parseInt(chosenNumberStr);
                switch (chosenNumber) {
                    case 1:
                        UserRepository userRepository = new UserRepository();
                        User user = new User();
                        drawMenu(userRepository, user, connection);
                        break;
                    case 2:
                        RoleRepository roleRepository = new RoleRepository();
                        Role role = new Role();
                        drawMenu(roleRepository, role, connection);
                        break;
                    case 3:
                        CardRepository cardRepository = new CardRepository();
                        Card card = new Card();
                        drawMenu(cardRepository, card, connection);
                        break;
                    case 4:
                        ChanceRepository chanceRepository = new ChanceRepository();
                        Chance chance = new Chance();
                        drawMenu(chanceRepository, chance, connection);
                        break;
                    case 5:
                        GameRepository gameRepository = new GameRepository();
                        Game game = new Game();
                        drawMenu(gameRepository, game, connection);
                        break;
                    case 6:
                        PassportRepository passportRepository = new PassportRepository();
                        Passport passport = new Passport();
                        drawMenu(passportRepository, passport, connection);
                        break;
                    case 7:
                        ReviewRepository reviewRepo = new ReviewRepository();
                        Review review = new Review();
                        drawMenu(reviewRepo, review, connection);
                        break;
                    case 8:
                        SubscriptionRepository subscriptionRepository = new SubscriptionRepository();
                        Subscription subscription = new Subscription();
                        drawMenu(subscriptionRepository, subscription, connection);
                        break;
                    default:
                        connection.close();
                        return;
                }

            } catch (NumberFormatException e) {
                System.console().printf("Typed value not int, try again\n");
            } catch (SQLException e) {
                System.console().printf(e.getMessage());
            }
        }
    }
}
