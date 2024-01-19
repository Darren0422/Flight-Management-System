import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Try and catch block related to the database connection.
        try {
            // Connect to the library_db database, via the jdbc:mysql: channel on localhost
            // (this PC)
            // Use username "otheruser", password "swordfish".
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/AMS?useSSL=false",
                    "otheruser",
                    "swordfish");

            System.out.println("\nSuccessful connection to MySQL Database 'AMS'\n ");
            // Create a direct line to the database for running our queries
            Statement statement = connection.createStatement();

            Scanner scanner = new Scanner(System.in);
            int choice;

            // Do while loop allows the user to repeatedly interact with the program until
            // they choose to exit
            do {

                try {
                    mainMenu();
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            int viewChoice;

                            do {
                                viewMenu();
                                viewChoice = scanner.nextInt();
                                scanner.nextLine();

                                switch (viewChoice) {
                                    case 1:
                                        viewAllFlights(statement);
                                        break;
                                    case 2:
                                        viewDeparting(statement);
                                        break;
                                    case 3:
                                        viewArrivingFlights(statement);
                                        break;
                                    case 0:
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please enter a valid number.");
                                }
                            } while (viewChoice != 0);
                            break;
                        case 2:
                            addFlight(statement, scanner);
                            break;
                        case 3:
                            addPassenger(statement, scanner);
                            break;
                        case 4:
                            bookFlight(statement, scanner);
                            break;

                        case 5:
                            printPassengerBooking(statement, scanner);
                            break;
                        case 0:
                            System.out.println("Exiting... Goodbye!");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a valid number.");
                    }

                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid number for your choice.");
                    scanner.nextLine();
                    choice = -1;
                }

            } while (choice != 0);

            statement.close();
            connection.close();
            scanner.close();

        } catch (SQLException e) {
            System.out.println("Database connection error:");
            e.printStackTrace();

        }
    }

    /**
     * Displays the main menu options for a flight management system.
     * Users are prompted to enter the number corresponding to their choice.
     */
    public static void mainMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. View All Flights ");
        System.out.println("2. Add Flight ");
        System.out.println("3. Add Passenger ");
        System.out.println("4. Book Flight ");
        System.out.println("5. Print Passenger Ticket ");

        System.out.println("0. Exit");

        System.out.print("\nEnter the number of your choice: ");
    }

    /**
     * Displays the "View All Flights" menu options.
     * Users are prompted to enter the number corresponding to their choice.
     */
    public static void viewMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. View All Flights ");
        System.out.println("2. View All Departing Flights");
        System.out.println("3. View All Arriving Flights ");

        System.out.println("0. Back to Main Menu");

        System.out.print("\nEnter the number of your choice: ");
    }

    /**
     * Retrieves and displays information about all flights from the database.
     * The flight details include Flight ID, Aeroplane ID, Departure City,
     * Destination City,
     * Departure Time, and Arrival Time.
     *
     * @param statement SQL Statement object for executing the database query.
     * @throws SQLException If a database access error occurs.
     */
    public static void viewAllFlights(Statement statement) throws SQLException {
        ResultSet results = statement.executeQuery(
                "SELECT flightId, aeroplaneId, departureCity, destinationCity, departureTime, arrivalTime FROM Flights");

        System.out.printf("\n%-10s %-12s %-20s %-20s %-20s %-20s\n", "Flight ID", "Aeroplane ID", "Departure City",
                "Destination City", "Departure Time", "Arrival Time");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------");

        while (results.next()) {
            System.out.printf("%-10d %-12d %-20s %-20s %-20s %-20s\n",
                    results.getInt("flightId"),
                    results.getInt("aeroplaneId"),
                    results.getString("departureCity"),
                    results.getString("destinationCity"),
                    results.getString("departureTime"),
                    results.getString("arrivalTime"));
        }
    }

    /**
     * Retrieves and displays information about departing flights.
     * The flight details include Flight ID, Aeroplane ID, Departure City,
     * Destination City, Departure Time, and Arrival Time. 
     * Only flights departing from 'Cape Town'.
     * 
     * @param statement SQL Statement object for executing the database query.
     * @throws SQLException If a database access error occurs.
     */
    public static void viewDeparting(Statement statement) throws SQLException {
        ResultSet results = statement.executeQuery(
                "SELECT flightId, aeroplaneId, departureCity, destinationCity, departureTime, arrivalTime FROM Flights WHERE departureCity = 'Cape Town'");

        System.out.printf("\n%-10s %-12s %-20s %-20s %-20s %-20s\n", "Flight ID", "Aeroplane ID", "Departure City",
                "Destination City", "Departure Time", "Arrival Time");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------");

        while (results.next()) {
            System.out.printf("%-10d %-12d %-20s %-20s %-20s %-20s\n",
                    results.getInt("flightId"),
                    results.getInt("aeroplaneId"),
                    results.getString("departureCity"),
                    results.getString("destinationCity"),
                    results.getString("departureTime"),
                    results.getString("arrivalTime"));
        }
    }

    /**
     * Retrieves and displays information about departing flights.
     * The flight details include Flight ID, Aeroplane ID, Departure City,
     * Destination City, Departure Time, and Arrival Time. 
     * Only flights arriving to 'Cape Town'.
     * 
     * @param statement SQL Statement object for executing the database query.
     * @throws SQLException If a database access error occurs.
     */
    public static void viewArrivingFlights(Statement statement) throws SQLException {
        ResultSet results = statement.executeQuery(
                "SELECT flightId, aeroplaneId, departureCity, destinationCity, departureTime, arrivalTime FROM Flights WHERE destinationCity = 'Cape Town'");

        System.out.printf("\n%-10s %-12s %-20s %-20s %-20s %-20s\n", "Flight ID", "Aeroplane ID", "Departure City",
                "Destination City", "Departure Time", "Arrival Time");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------");

        while (results.next()) {
            System.out.printf("%-10d %-12d %-20s %-20s %-20s %-20s\n",
                    results.getInt("flightId"),
                    results.getInt("aeroplaneId"),
                    results.getString("departureCity"),
                    results.getString("destinationCity"),
                    results.getString("departureTime"),
                    results.getString("arrivalTime"));
        }
    }

    /**
     * Adds a new flight to the database based on user input for airline, aeroplane, departure and destination cities,
     * departure time, and arrival time.
     *
     * @param statement SQL Statement object for executing database queries.
     * @param scanner   Scanner object for user input.
     * @throws SQLException If a database access error occurs.
     */
    public static void addFlight(Statement statement, Scanner scanner) throws SQLException {
        // Shows available airlines 
        viewAirlines(statement);
        System.out.print("\nEnter Airline ID: ");
        int airlineChoice = scanner.nextInt();

        // Shows available aeroplanes 
        viewAeroplanes(statement, airlineChoice);
        System.out.print("\nEnter Aeroplane ID: ");
        int aeroplaneChoice = scanner.nextInt();

        System.out.print("\nEnter Departure City: ");
        String departureCity = scanner.nextLine();

        System.out.print("\nEnter Destination City: ");
        String destinationCity = scanner.nextLine();

        Date parsedDepartureTime = getDateInput(scanner, "Departure");
        Date parsedArrivalTime = getDateInput(scanner, "Arrival");

        // Converts parsed dates to java.sql.Date
        java.sql.Date departureTime = new java.sql.Date(parsedDepartureTime.getTime());
        java.sql.Date arrivalTime = new java.sql.Date(parsedArrivalTime.getTime());

        String insertQuery = String.format(
                "INSERT INTO Flights (aeroplaneId, departureCity, destinationCity, departureTime, arrivalTime) " +
                        "VALUES (%d, '%s', '%s', '%s', '%s');",
                aeroplaneChoice, departureCity, destinationCity, departureTime, arrivalTime);

        try {
            statement.executeUpdate(insertQuery);
            System.out.println("Flight added successfully!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prompts the user to enter a date and time in the given format and validates the input.
     *
     * @param scanner   Scanner object for user input.
     * @param timeType  String indicating the type of time (e.g., "Departure" or "Arrival").
     * @return A Date object representing the parsed date and time input.
     */
    private static Date getDateInput(Scanner scanner, String timeType) {
        Date parsedTime = null;
        boolean validFormat = false;

        do {
            System.out.print("\nEnter " + timeType + " Time (yyyy-MM-dd HH:mm:ss): ");
            String timeString = scanner.nextLine();

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateFormat.setLenient(false);

                parsedTime = dateFormat.parse(timeString);
                validFormat = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in the correct format.");
            }

        } while (!validFormat);

        return parsedTime;
    }

    public static void viewAirlines(Statement statement) throws SQLException {
        ResultSet results = statement.executeQuery("SELECT * FROM Airlines");

        System.out.printf("\n%-15s %-12s\n", "Airline ID", "Airline Name");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------");

        while (results.next()) {
            System.out.printf("%-15d %-12s\n",
                    results.getInt("airlineId"),
                    results.getString("airlineName"));
        }
    }

    /**
     * Retrieves and displays information about all airlines from the database.
     *
     * @param statement SQL Statement object for executing the database query.
     * @throws SQLException If a database access error occurs.
     */
    public static void viewAeroplanes(Statement statement, int airlineChoice) throws SQLException {
        ResultSet results = statement
                .executeQuery("SELECT * FROM Aeroplanes WHERE airlineId = '" + airlineChoice + "'");

        System.out.printf("\n%-15s %-12s %-12s\n", "Aeroplane ID", "Aeroplane Name", "Capacity");
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------------------");

        while (results.next()) {
            System.out.printf("%-15d %-12s %-12s\n",
                    results.getInt("aeroplaneId"),
                    results.getString("aeroplaneName"),
                    results.getInt("capacity"));
        }
    }

    /**
     * Adds a new passenger to the database based on user input for first name, last name, email,
     * phone number, and passport number.
     *
     * @param statement SQL Statement object for executing database queries.
     * @param scanner   Scanner object for user input.
     * @throws SQLException If a database access error occurs.
     */
    public static void addPassenger(Statement statement, Scanner scanner) throws SQLException {
        System.out.print("\nAdd a Passenger: ");

        System.out.print("\nEnter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("\nEnter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("\nEnter Email Address: ");
        String email = scanner.nextLine();

        System.out.print("\nEnter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        String passportNumber;
        do {
            System.out.print("\nEnter Passport Number (13 characters): ");
            passportNumber = scanner.nextLine();

            if (passportNumber.length() != 13) {
                System.out.println(
                        "Passport Number must be exactly 13 characters. Please enter a valid Passport Number.");
            }

        } while (passportNumber.length() != 13);

        String insertQuery = String.format(
                "INSERT INTO Passengers (firstName, lastName, email, phoneNumber, passportNumber) " +
                        "VALUES ('%s', '%s', '%s', '%s', '%s');",
                firstName, lastName, email, phoneNumber, passportNumber);

        try {
            statement.executeUpdate(insertQuery);
            System.out.println("Passenger added successfully!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves and displays information about all passengers from the database.
     * The passenger details include Passenger ID, First Name, Last Name, Email, Phone Number, and Passport Number.
     *
     * @param statement SQL Statement object for executing the database query.
     * @throws SQLException If a database access error occurs.
     */
    public static void viewAllPassengers(Statement statement) throws SQLException {
        ResultSet results = statement.executeQuery("SELECT * FROM Passengers");

        System.out.printf("\n%-12s %-15s %-15s %-25s %-15s %-15s\n",
                "Passenger ID", "First Name", "Last Name", "Email", "Phone Number", "Passport Number");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------");

        while (results.next()) {
            System.out.printf("%-12d %-15s %-15s %-25s %-15s %-15s\n",
                    results.getInt("passengerId"),
                    results.getString("firstName"),
                    results.getString("lastName"),
                    results.getString("email"),
                    results.getString("phoneNumber"),
                    results.getString("passportNumber"));
        }
    }

    /**
     * Books a seat on a departing flight for a selected passenger based on user input.
     * Displays departing flights, allows the user to choose a flight, select a passenger and assigns a seat number.
     * Updates the database with the booking information.
     *
     * @param statement SQL Statement object for executing database queries.
     * @param scanner   Scanner object for user input.
     * @throws SQLException If a database access error occurs.
     */
    public static void bookFlight(Statement statement, Scanner scanner) throws SQLException {
        System.out.println("\nBook a Departing Flight:");

        viewDeparting(statement);
        System.out.print("\nEnter Flight ID: ");
        int flightChoice = scanner.nextInt();

        // Retrieve aeroplane details based on the selected flight
        ResultSet aeroplaneResult = getAeroplaneDetails(statement, flightChoice);

        if (aeroplaneResult.next()) {
            int capacity = aeroplaneResult.getInt("capacity");
            int bookedSeats = aeroplaneResult.getInt("bookedSeats");

            viewAllPassengers(statement);
            System.out.print("\nEnter Passenger ID: ");
            int passengerChoice = scanner.nextInt();

            String bookingDate = getCurrentDate();

            // Assign a seat number within the capacity of the aeroplane
            int seatNumber = assignSeatNumber(capacity, bookedSeats);

            if (seatNumber == -1) {
                System.out.println("The flight is full.");
            } else {
                String insertQuery = String.format(
                        "INSERT INTO bookings (flightId, passengerId, bookingDate, seatNumber) VALUES (%d, %d, '%s', %d);",
                        flightChoice, passengerChoice, bookingDate, seatNumber);

                try {
                    // Update bookedSeats in the aeroplanes table
                    int updatedBookedSeats = bookedSeats + 1;
                    updateBookedSeats(statement, flightChoice, updatedBookedSeats);

                    statement.executeUpdate(insertQuery);

                    System.out.println("Booking successfully made!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Retrieves details of the aeroplane associated with a specific flight from the database.
     *
     * @param statement SQL Statement object for executing the database query.
     * @param flightId   ID of the flight for which aeroplane details are to be retrieved.
     * @return ResultSet containing details of the aeroplane.
     * @throws SQLException If a database access error occurs.
     */
    public static ResultSet getAeroplaneDetails(Statement statement, int flightId) throws SQLException {
        String aeroplaneQuery = String.format(
                "SELECT * FROM aeroplanes WHERE aeroplaneId = (SELECT aeroplaneId FROM flights WHERE flightId = %d)",
                flightId);

        return statement.executeQuery(aeroplaneQuery);
    }

    /**
     * Updates the number of booked seats for a specific flight in the aeroplanes table.
     *
     * @param statement SQL Statement object for executing the database update query.
     * @param flightId   ID of the flight for which booked seats are to be updated.
     * @param bookedSeats Number of booked seats to be set for the specified flight.
     * @throws SQLException If a database access error occurs.
     */
    public static void updateBookedSeats(Statement statement, int flightId, int bookedSeats) throws SQLException {
        String updateQuery = String.format(
                "UPDATE aeroplanes SET bookedSeats = %d WHERE aeroplaneId = (SELECT aeroplaneId FROM flights WHERE flightId = %d);",
                bookedSeats, flightId);
        statement.executeUpdate(updateQuery);
    }

    /**
     * Assigns a seat number for a passenger on a flight based on the current booked seats and flight capacity.
     * If the flight is full, returns -1.
     *
     * @param capacity    Total capacity of the aeroplane.
     * @param bookedSeats Number of seats already booked on the flight.
     * @return Assigned seat number or -1 if the flight is full.
     */
    public static int assignSeatNumber(int capacity, int bookedSeats) {
        int allocatedSeat = bookedSeats + 1;
        int assignedSeat = allocatedSeat;

        if (allocatedSeat > capacity) {
            assignedSeat = -1;
            System.out.println("The flight is full.");
        }

        return assignedSeat;
    }

    /**
     * Retrieves the current date (yyyy-MM-dd).
     *
     * @return String representing the current date.
     */
    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    /**
     * Retrieves and displays information about all passengers with bookings and their flight details.
     * The displayed information includes Passenger ID, First Name, Last Name, Passport Number,
     * Flight ID, Booking Date, Seat Number, Destination City, Departure Time, and Arrival Time.
     *
     * @param statement SQL Statement object for executing the database query.
     * @throws SQLException If a database access error occurs.
     */
    public static void viewAllPassengersWithBooking(Statement statement) throws SQLException {
        String query = "SELECT p.passengerId, p.firstName, p.lastName, p.passportNumber, " +
                "b.flightId, b.bookingDate, b.seatNumber, " +
                "f.destinationCity, f.departureTime, f.arrivalTime " +
                "FROM passengers p " +
                "JOIN bookings b ON p.passengerId = b.passengerId " +
                "JOIN flights f ON b.flightId = f.flightId";

        try (ResultSet results = statement.executeQuery(query)) {
            System.out.printf("\n%-12s %-15s %-15s %-20s %-10s %-15s %-15s %-20s %-25s %-25s\n",
                    "Passenger ID", "First Name", "Last Name", "Passport Number",
                    "Flight ID", "Booking Date", "Seat Number",
                    "Destination City", "Departure Time", "Arrival Time");
            System.out.println(
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while (results.next()) {
                int passengerId = results.getInt("passengerId");
                String firstName = results.getString("firstName");
                String lastName = results.getString("lastName");
                String passportNumber = results.getString("passportNumber");

                int flightId = results.getInt("flightId");
                Date bookingDate = results.getDate("bookingDate");
                int seatNumber = results.getInt("seatNumber");

                String destinationCity = results.getString("destinationCity");
                Timestamp departureTime = results.getTimestamp("departureTime");
                Timestamp arrivalTime = results.getTimestamp("arrivalTime");

                System.out.printf("%-12d %-15s %-15s %-20s %-10d %-15s %-15d %-20s %-25s %-25s\n",
                        passengerId, firstName, lastName, passportNumber,
                        flightId, bookingDate, seatNumber,
                        destinationCity, departureTime, arrivalTime);
            }
        }
    }

    /**
     * Displays detailed booking information for a selected passenger based on their Passenger ID.
     * Retrieves and prints information about the passenger, associated booking and the flight details.
     *
     * @param statement SQL Statement object for executing the database query.
     * @param scanner   Scanner object for user input.
     * @throws SQLException If a database access error occurs.
     */
    public static void printPassengerBooking(Statement statement, Scanner scanner) throws SQLException {
        viewAllPassengersWithBooking(statement);

        System.out.println("\nSelect the Passenger ID to print:");
        int passengerChoice = scanner.nextInt();

        // Retrieve information for the selected passenger
        String query = "SELECT p.passengerId, p.firstName, p.lastName, p.passportNumber, " +
                "b.flightId, b.bookingDate, b.seatNumber, " +
                "f.destinationCity, f.departureTime, f.arrivalTime " +
                "FROM passengers p " +
                "JOIN bookings b ON p.passengerId = b.passengerId " +
                "JOIN flights f ON b.flightId = f.flightId " +
                "WHERE p.passengerId = " + passengerChoice;

        try (ResultSet results = statement.executeQuery(query)) {
            if (results.next()) {
                int passengerId = results.getInt("passengerId");
                String firstName = results.getString("firstName");
                String lastName = results.getString("lastName");
                String passportNumber = results.getString("passportNumber");

                int flightId = results.getInt("flightId");
                Date bookingDate = results.getDate("bookingDate");
                int seatNumber = results.getInt("seatNumber");

                String destinationCity = results.getString("destinationCity");
                Date departureTime = results.getTimestamp("departureTime");
                Date arrivalTime = results.getTimestamp("arrivalTime");

                // Display passenger and booking information
                System.out.println(
                        "\n*****************************************************************************************************************************************");
                System.out.println("\nTicket:");
                System.out.println("\nPassenger Information:");
                System.out.printf("Passenger ID: %-12d Name: %-15s %-15s Passport Number: %-20s\n",
                        passengerId, firstName, lastName, passportNumber);

                System.out.println("\nBooking Information:");
                System.out.printf("Flight ID: %-10d Booking Date: %-15s Seat Number: %-5d\n",
                        flightId, bookingDate, seatNumber);
                System.out.printf("Destination City: %-20s Departure Time: %-25s Arrival Time: %-25s\n",
                        destinationCity, departureTime, arrivalTime);
                System.out.println(
                        "\n*****************************************************************************************************************************************");
            } else {
                System.out.println("Passenger not found with ID: " + passengerChoice);
            }
        }
    }

}
