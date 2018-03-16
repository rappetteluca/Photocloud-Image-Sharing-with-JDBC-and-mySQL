CREATE DATABASE PhotoCloud;
USE PhotoCloud;

CREATE USER 'username'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON  PhotoCloud.* TO 'username'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE User (
	Username NVARCHAR (20) NOT NULL,
	Password NVARCHAR (20) NOT NULL,
	JoinDate TIMESTAMP NOT NULL,
    FirstName NVARCHAR(20),
    LastName NVARCHAR(20),
	Phone NVARCHAR(20),
	Biography NVARCHAR(500),
	CreditCard NVARCHAR(20),
    CCV INT,
    ExpirationDate NVARCHAR(5),
	ZipCode INT,
	BillingAddress NVARCHAR (50),
	CardholdersName NVARCHAR (50),
    PRIMARY KEY(Username)
	
);

CREATE TABLE Image (
	ImageID INT NOT NULL AUTO_INCREMENT,
	Username NVARCHAR (20) NOT NULL,
    FilePath NVARCHAR(200) NOT NULL,
	Price NUMERIC (8,2) NOT NULL,
	Title NVARCHAR (50) NOT NULL,
	Description NVARCHAR (200) NOT NULL,
	FOREIGN KEY (Username) REFERENCES User(Username),
    PRIMARY KEY (ImageID)
);

CREATE TABLE Invoice (
	Username NVARCHAR (20) NOT NULL,
	PhotographerID NVARCHAR(20) NOT NULL,
    InvoiceID INT NOT NULL AUTO_INCREMENT,
    ImageID INT NOT NULL,
	PurchaseDate TIMESTAMP NOT NULL,
	SalePrice NUMERIC (8,2) NOT NULL,
	FOREIGN KEY (Username) REFERENCES User(Username),
	FOREIGN KEY (PhotographerID) REFERENCES User(Username),
    FOREIGN KEY (ImageID) REFERENCES Image(ImageID),
    PRIMARY KEY (InvoiceID)
);

CREATE TABLE Portfolio (
	Username NVARCHAR (20) NOT NULL,
    ImageID INT NOT NULL,
    FOREIGN KEY (Username) REFERENCES User(Username),
	FOREIGN KEY (ImageID) REFERENCES Image(ImageID)
);

CREATE TABLE PurchaseHistory (
	Username NVARCHAR (20) NOT NULL,
    InvoiceID INT NOT NULL,
    FOREIGN KEY (InvoiceID) REFERENCES Invoice(InvoiceID),
	FOREIGN KEY (Username) REFERENCES User(Username)
);
