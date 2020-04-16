-- Team 11 Flight Reservation System
-- Yauheniya Nikulyak, Thach Doan, Christopher Mendonca
-- Final Project, CST 438 SP2020

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- Schema FlightReservation
DROP SCHEMA IF EXISTS FlightReservation;
CREATE SCHEMA IF NOT EXISTS `FlightReservation` DEFAULT CHARACTER SET utf8 ;
USE `FlightReservation` ;

-- Table `Airport`
DROP TABLE IF EXISTS `Airport`;
CREATE TABLE IF NOT EXISTS `Airport` (
  `id` VARCHAR(3) NOT NULL, # IATA Airport code, example SFO.
  `title` VARCHAR(100) NOT NULL,
  `location` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `title_index` (`title`)
)
ENGINE = InnoDB;



INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("DAB", "Daytona Beach International Airport", "Daytona Beach, Florida, United States");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("DAC", "Shahjalal International Airport", "Dhaka, Bangladesh");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("DAL", "Dallas Love Field", "Dallas, Texas, United States");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("DME", "Domodedovo International Airport", "Moscow, Russia");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("DUD", "Dunedin International Airport", "Dunedin, New Zealand");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("DXB", "Dubai International Airport", "Dubai, United Arab Emirates");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("SAN", "San Diego International Airport", "San Diego, California, United States");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("SAT", "San Antonio International Airport", "San Antonio, Texas, United States");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("SEA", "Seattle–Tacoma International Airport", "Seattle, Washington, United States");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("SHG", "Shungnak Airport", "Shungnak, Alaska, United States");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("SHH", "Shishmaref Airport", "Shishmaref, Alaska, United States");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("SHJ", "Sharjah International Airport", "Sharjah, United Arab Emirates");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("SMF", "Sacramento International Airport", "Sacramento, California, United States");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("SPI", "Abraham Lincoln Capital Airport", "Springfield, Illinois, United States");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("SQN", "Sanana Airport", "Sanana Island, Indonesia");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("SQO", "Storuman Airport", "Storuman, Sweden");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("SSH", "Sharm el-Sheikh International Airport", "Sharm el-Sheikh, Egypt");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("SST", "Santa Teresita Airport", "Santa Teresita, Buenos Aires, Argentina");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("STN", "London Stansted Airport", "London, England, United Kingdom");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("SXB", "Strasbourg Airport", "Strasbourg, Alsace, France");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("SXF", "Berlin Schönefeld Airport", "Berlin, Germany");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("LAX", "Los Angeles International Airport", "Los Angeles, California, United States");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("SFO", "San Francisco International Airport", "San Francisco, California, United States");
INSERT INTO `Airport` (`id`, `title`, `location`) VALUES ("SJC", "San Jose International Airport", "San Jose, California, United States");



-- Table `Flight`
DROP TABLE IF EXISTS `Flight`;
CREATE TABLE IF NOT EXISTS `Flight` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `short_code` VARCHAR(30) NOT NULL, # LH454
  `from_airport_id` VARCHAR(3) NOT NULL,
  `to_airport_id` VARCHAR(3) NOT NULL,
  `scheduled_departure_time` DATETIME NOT NULL,
  `scheduled_arrival_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `from_index` (`from_airport_id`, `scheduled_departure_time`),
  INDEX `to_index` (`to_airport_id`, `scheduled_arrival_time`)
)
ENGINE = InnoDB;

INSERT INTO `Flight` (`short_code`,`from_airport_id`,`to_airport_id`,`scheduled_departure_time`,`scheduled_arrival_time`) 
VALUES("AS1922","SFO","LAX","2020-05-01 08:30:00","2020-05-01 10:30:00");
INSERT INTO `Flight` (`short_code`,`from_airport_id`,`to_airport_id`,`scheduled_departure_time`,`scheduled_arrival_time`) 
VALUES("AS1921","SFO","LAX","2020-05-01 10:30:00","2020-05-01 12:30:00");
INSERT INTO `Flight` (`short_code`,`from_airport_id`,`to_airport_id`,`scheduled_departure_time`,`scheduled_arrival_time`) 
VALUES("AS1920","SFO","LAX","2020-05-01 05:30:00","2020-05-01 07:30:00");
INSERT INTO `Flight` (`short_code`,`from_airport_id`,`to_airport_id`,`scheduled_departure_time`,`scheduled_arrival_time`) 
VALUES("AS1923","SFO","LAX","2020-05-01 18:30:00","2020-05-01 20:30:00");

INSERT INTO `Flight` (`short_code`,`from_airport_id`,`to_airport_id`,`scheduled_departure_time`,`scheduled_arrival_time`) 
VALUES("AS1943","LAX","SFO","2020-05-10 09:30:00","2020-05-10 11:30:00");
INSERT INTO `Flight` (`short_code`,`from_airport_id`,`to_airport_id`,`scheduled_departure_time`,`scheduled_arrival_time`) 
VALUES("AS1942","LAX","SFO","2020-05-10 05:30:00","2020-05-10 07:30:00");
INSERT INTO `Flight` (`short_code`,`from_airport_id`,`to_airport_id`,`scheduled_departure_time`,`scheduled_arrival_time`) 
VALUES("AS1941","LAX","SFO","2020-05-10 12:30:00","2020-05-10 14:30:00");
INSERT INTO `Flight` (`short_code`,`from_airport_id`,`to_airport_id`,`scheduled_departure_time`,`scheduled_arrival_time`) 
VALUES("AS1940","LAX","SFO","2020-05-10 20:30:00","2020-05-10 22:30:00");

INSERT INTO `Flight` (`short_code`,`from_airport_id`,`to_airport_id`,`scheduled_departure_time`,`scheduled_arrival_time`) 
VALUES("AS3335","SAN","SJC","2020-06-10 08:30:00","2020-06-10 10:30:00");

INSERT INTO `Flight` (`short_code`,`from_airport_id`,`to_airport_id`,`scheduled_departure_time`,`scheduled_arrival_time`) 
VALUES("AS3334","SJC","SAN","2020-06-20 09:30:00","2020-06-20 11:30:00");

-- Table `Passenger`
DROP TABLE IF EXISTS `Passenger`;
CREATE TABLE IF NOT EXISTS `Passenger` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  `user_password_hash` BINARY(128) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `username_index` (`username`)
)
ENGINE = InnoDB;

-- Table `Reservation`
DROP TABLE IF EXISTS `Reservation`;
CREATE TABLE IF NOT EXISTS `Reservation` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `passenger_id` INT(11) NOT NULL,
  `flight_id` INT(11) NOT NULL,
  `rental_car` VARCHAR(3) NULL DEFAULT 'No',
  `shuttle` VARCHAR(3) NULL DEFAULT 'No',
  `hotel` VARCHAR(3) NULL DEFAULT 'No',
  `seat_pref` VARCHAR(15) NULL DEFAULT 'No Preference',
  PRIMARY KEY (`id`),
  INDEX `passenger_id_index` (`passenger_id`),
  INDEX `flight_id_index` (`flight_id`)
)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;