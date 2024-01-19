# Flight-Management-System
## Description 

This is a Java, SQL and MySQL application that can be used by an Airline desk worker to interact with their flight management system. 

This application allows the user to view all flights departing from or arriving to Cape Town. They can also add a flight, add a passenger, book a flight and print a passengers ticket. It makes use of a intuitive text-based user interface. 

## Importance 

This repository is important as it shows my understanding and comprehension of Java, SQL, MySQL and databases fundamentals. It has a menu-driven interface for the user to interact with the program. User input handling is taken into consideration, as well as dealing with potential input errors. Their is database interactions which involves involves connecting to a MySQL database, creating a statement, and executing SQL queries. The application makes and complies SQL queries dynamically based on user input. 

## Installation

To install this project locally, open the repository in your web browser: [Flight-Management-System](https://github.com/Darren0422/Flight-Management-System). On the repository page, click on the green "Code" button located near the top-right of the page. From the dropdown menu, select "Download ZIP". This will start the download of the repository as a ZIP file to your computer. Save it on your local storage device. 

<img width="1728" alt="Screenshot 2024-01-19 at 12 02 53" src="https://github.com/Darren0422/Flight-Management-System/assets/134398985/2d6c5f5a-f441-43bd-8307-99a14f3df5e2">

##  Usage

You may open this file with a code editor of your choice, such as, VSCode and gain access to the written lines of code.
Run the program in your editor of choice.

<img width="1728" alt="Screenshot 2024-01-19 at 11 37 29" src="https://github.com/Darren0422/Flight-Management-System/assets/134398985/9867de6d-1c9b-4501-8953-76a962ed1bd7">

### MySQL Database 

a MySQL Database called "AMS" was created. A table for "Airlines", "Aeroplanes", "Flights", "Passengers" and "Bookings" was created.

<img width="1049" alt="Screenshot 2024-01-19 at 11 50 29" src="https://github.com/Darren0422/Flight-Management-System/assets/134398985/5b27e845-04e9-47f5-9c6c-347dd58eee06">
<img width="1049" alt="Screenshot 2024-01-19 at 11 50 44" src="https://github.com/Darren0422/Flight-Management-System/assets/134398985/f8f0b928-e851-48d1-bfe0-ea5ef3c1edf8">

### Airlines
To add a new record to the airlines table, you can use the following example SQL statement:

"INSERT INTO airlines (airlineName, headquarters, contactNumber)
VALUES ('New Airline', 'City, Country', '+1-123-456-7890');"

### Aeroplanes
To add a new record to the aeroplanes table, you can use the following example SQL statement:

"INSERT INTO aeroplanes (airlineId, aeroplaneName, capacity, bookedSeats)
VALUES (1, 'New Emirates Plane', 280, 0);"

### Flights
To add a new record to the flights table, you can use the following example SQL statement:

"INSERT INTO flights (aeroplaneId, departureCity, destinationCity, departureTime, arrivalTime)
VALUES (10, 'Cape Town', 'New Destination City', '2024-01-20 09:00:00', '2024-01-20 12:00:00');"

### Passengers
To add a new record to the passengers table, you can use the following example SQL statement:

"INSERT INTO passengers (firstName, lastName, email, phoneNumber, passportNumber)
VALUES ('NewFirst', 'NewLast', 'new.email@example.com', '111-222-3333', 'XY111222333');"

### Bookings
To add a new record to the bookings table, you can use the following example SQL statement:

"INSERT INTO bookings (flightId, passengerId, bookingDate, seatNumber)
VALUES (2, 3, '2024-01-20', 12);"

### Classes 
#### Main 

This is the main class of the application. It holds the functionailty for the user interactions.

### Features  
#### Main Menu
The user is prompted to enter their choice for the option they would like to make use of. This prompt will repeat until a valid option is selected or the user quits.

<img width="1367" alt="Screenshot 2024-01-19 at 11 43 34" src="https://github.com/Darren0422/Flight-Management-System/assets/134398985/104349af-8b83-4b0a-a141-ec33f27bd77d">


##### View All Flights 

<img width="1367" alt="Screenshot 2024-01-19 at 11 45 17" src="https://github.com/Darren0422/Flight-Management-System/assets/134398985/33792f41-0654-4d81-9078-c6bc846358ae">

###### View All Flights 

This displays all the flight records within the "AMS" database.

###### View All Departing Flights 

This displays all the flight records that are departing from Cape Town within the "AMS" database.

###### View All Arriving Flights 

This displays all the flight records that are arriving in Cape Town within the "AMS" database.

###### Back to Main Menu

This returns the user to the main menu.

##### Add Flight 

This adds a new record to the "Flights" table in the "AMS" database. The user is prompted to enter the flight ID, aeroplane ID, departure city, destination city, departure time and arrval time.

##### Add Passenger 

This adds a new record to the "Passenger" table in the "AMS" database. The user is prompted to enter the passengers name, surname, email address, phone number and passport number.

##### Book Flight 

This adds a new record to the "Bookings" table in the "AMS" database. The user is prompted to enter the flights ID and the passengers ID.

##### Print Passenger Ticket 

This retrieves and prints information about the passenger, their associated booking and the associated flight details. The user is prompted to enter the passengers ID.\

##### Quit

The user quits the application and the flight management system is closed.

## Credits
[Darren Chen](https://github.com/Darren0422)
