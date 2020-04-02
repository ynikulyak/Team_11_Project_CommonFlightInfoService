INSERT INTO Flightreservation.Login (idAccount, username, userPassword)
VALUES
	(10, 'jsmith', 'jsmithpassword'),
	(20, 'ijones', 'mjonespassword'),
	(30, 'bross', 'brosspassword'),
	(40, 'jbravo', 'jbravopassword'),
	(50, 'adamsfamily', 'adamsfamilypassword');

INSERT INTO Flightreservation.Passenger (idPassenger, firstName, lastName, email, Login_idAccount)
VALUES
	(1, 'Judy', 'Smith', 'jsmith@fakedomain.com',10),
	(2, 'Indiana', 'Jones', 'indy@fakedomain.com',20),
	(3, 'Bob', 'Ross', 'happymistakes@arejustbirds.com',30),
	(4, 'Johhny', 'Bravo', 'muscleman@gmail.com',40),
	(5, 'Morticia', 'Addams', 'darkmistress@yahoo.com',50),
	(6, 'Lurch', 'Addams', 'batman21@yamil.com',50),
	(7, 'Wednesday', 'Addams', 'everydaywednesday@gmail.com',50),
	(8, 'Pugsley', 'Addams', 'toad@gmail.com',50),
	(9, 'Fester', 'Addams', 'unclefes@yahoo.com',50),
	(10, 'It', 'Addams', 'hairyisscary@hotmail.com',50);

INSERT INTO Flightreservation.Airport (idAirport, city, country, countryCode)
VALUES 
	(100, 'Los Angeles', 'United Stateds of America', 'USA'),
	(200, 'San Antonio', 'United Stateds of America', 'USA'),
	(300, 'Fresno', 'United Stateds of America', 'USA'),
	(400, 'Nashville', 'United Stateds of America', 'USA'),
	(500, 'Boise', 'United Stateds of America', 'USA');

INSERT INTO Flightreservation.Flight (idFlight, departureTime, arrivalTime, Flightcol, Arrival_Airport_id, Departure_Airport_id)
VALUES
	(1000, '10:00am', '3:00pm', 'Virgin Airlines', 100, 200),
	(2000, '10:00am', '3:00pm', 'Delta Airlines', 300, 500),
	(3000, '11:30am', '2:00pm', 'United Airlines', 300, 100),
	(4000, '11:30am', '3:00pm', 'Virgin Airlines', 400, 500),
	(5000, '4:000pm', '11:00pm', 'Delta Airlines', 100, 500);

INSERT INTO Flightreservation.Reservation (idReservation, rentalCar, shuttle, hotel, seatPref, Flight_idFlight, Passenger_idPassenger)
VALUES
	(10000, 'No', 'No', 'No', 'Aisle', 1000, 1),
	(20000, 'Yes', 'No', 'Yes', 'No Preference', 2000, 2),
	(30000, 'No', 'No', 'No', 'No Preference', 5000, 3),
	(40000, 'No', 'No', 'Yes', 'Window', 4000, 4),
	(50000, 'No', 'Yes', 'No', 'No Preference', 3000, 5),
	(50001, 'No', 'Yes', 'No', 'No Preference', 3000, 6),
	(50002, 'No', 'Yes', 'No', 'No Preference', 3000, 7),
	(50003, 'No', 'Yes', 'No', 'Window', 3000, 8),
	(50004, 'No', 'Yes', 'No', 'Window', 3000, 9),
	(50005, 'No', 'Yes', 'No', 'Aisle', 3000, 10);