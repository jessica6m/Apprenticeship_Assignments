DROP DATABASE IF EXISTS HotelReservation;

CREATE DATABASE HotelReservation;

USE HotelReservation;

CREATE TABLE `PromotionCode` (
  `Promo_ID` INT NOT NULL auto_increment,
  `PromoAmt` INT,
  `PromoPercentage` INT,
  `StartDate` INT,
  `EndDate` INT NOT NULL,
  PRIMARY KEY (`Promo_ID`)
);

CREATE TABLE `States` (
  `State_ID` INT NOT NULL auto_increment,
  `State` varchar(30) NOT NULL,
  PRIMARY KEY (`State_ID`),
  KEY `Key` (`State`)
);

CREATE TABLE `AddOns` (
  `AddOns_ID` INT NOT NULL auto_increment,
  `Addons` varchar(30) NOT NULL,
  `Price` INT NOT NULL, 
  PRIMARY KEY (`AddOns_ID`)
);


CREATE TABLE `AdditionalGuest` (
  `Guest_ID` INT NOT NULL auto_increment,
  `FirstName` varchar(30) NOT NULL,
  `LastName` varchar(30),
  `Age` INT NOT NULL,
  PRIMARY KEY (`Guest_ID`)
);

CREATE TABLE `BillingDetails` (
  `Details_ID` INT NOT NULL auto_increment,
  `Details` varchar(30) NOT NULL,
  `Price` INT NOT NULL,
  PRIMARY KEY (`Details_ID`)
);

CREATE TABLE `RoomType` (
  `RoomType_ID` INT NOT NULL auto_increment,
  `RoomType` varchar(30) NOT NULL,
  PRIMARY KEY (`RoomType_ID`)
);

CREATE TABLE `Amenities` (
  `Amenities_ID` INT NOT NULL auto_increment,
  `AmenitiesType` varchar(30) NOT NULL,
  `Price` INT NOT NULL,
  PRIMARY KEY (`Amenities_ID`)
);

CREATE TABLE `RoomRates` (
  `RoomRates_ID` INT NOT NULL auto_increment,
  `Peak` boolean NOT NULL,
  `Price` INT NOT NULL,
  PRIMARY KEY (`RoomRates_ID`)
);

CREATE TABLE `Phone Type` (
  `PhoneType_ID` INT NOT NULL auto_increment,
  `PhoneType` varchar(30) NOT NULL,
  PRIMARY KEY (`PhoneType_ID`)
);

CREATE TABLE `Customer` (
  `Customer_ID` INT NOT NULL auto_increment,
  `FirstName` varchar(30) NOT NULL,
  `LastName` varchar(30) NOT NULL,
  `Age` INT,
  `Address` varchar(55) NOT NULL,
  `City` varchar(30) NOT NULL,
  `Zip` INT NOT NULL,
  `E-Mail` varchar(30),
  `PhoneNumber` INT,
  `PhoneType_ID` INT,
  `State_ID` INT,
  PRIMARY KEY (`Customer_ID`),
  FOREIGN KEY (`PhoneType_ID`) REFERENCES `Phone Type`(`PhoneType_ID`),
  FOREIGN KEY (`State_ID`) REFERENCES `States`(`State_ID`)
);


CREATE TABLE `Room` (
  `Room_ID` INT NOT NULL auto_increment,
  `RoomType_ID` INT,
  `RumNum` INT,
  `Floor` INT,
  `Occupancy` INT,
  `Amenities_ID` INT,
  `Customer_ID` INT,
  `RoomRates_ID` INT,
  PRIMARY KEY (`Room_ID`),
  FOREIGN KEY (`RoomType_ID`)  REFERENCES `RoomType`(`RoomType_ID`),
  FOREIGN KEY (`Amenities_ID`)  REFERENCES `Amenities`(`Amenities_ID`),
  FOREIGN KEY (`Customer_ID`)  REFERENCES `Customer`(`Customer_ID`),
  FOREIGN KEY (`RoomRates_ID`)  REFERENCES `RoomRates`(`RoomRates_ID`)
);


CREATE TABLE `Reservation` (
  `Reservation_ID` INT NOT NULL auto_increment,
  `Customer_ID` INT,
  `Guest_ID` INT,
  `Room_ID` INT,
  `StartDate` date NOT NULL,
  `EndDate` date,
  PRIMARY KEY (`Reservation_ID`),
  FOREIGN KEY (`Customer_ID`)  REFERENCES `Customer`(`Customer_ID`),
  FOREIGN KEY (`Guest_ID`)  REFERENCES `AdditionalGuest`(`Customer_ID`),
  FOREIGN KEY (`Room_ID`)  REFERENCES `Room`(`Room_ID`)
);

CREATE TABLE `Billing` (
  `Billing_ID` INT NOT NULL auto_increment,
  `Total` decimal(13, 2) NOT NULL,
  `Tax` decimal(8, 2) NOT NULL,
  `Promo_ID` INT,
  `Details_ID` INT,
  `Customer_ID` INT,
  `AddOns_ID` INT,
  `Reservation_ID` INT,
  PRIMARY KEY (`Billing_ID`),
  FOREIGN KEY (`Promo_ID`)  REFERENCES `PromotionCode`(`Promo_ID`),
  FOREIGN KEY (`Details_ID`)  REFERENCES `BillingDetails`(`Details_ID`),
  FOREIGN KEY (`Customer_ID`)  REFERENCES `Customer`(`Customer_ID`),
  FOREIGN KEY (`AddOns_ID`)  REFERENCES `AddOns`(`AddOns_ID`),
  FOREIGN KEY (`Reservation_ID`)  REFERENCES `Reservation`(`Reservation_ID`)
);

