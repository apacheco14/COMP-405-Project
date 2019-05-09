BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Customer" (
	"Email"	TEXT,
	"Password"	TEXT,
	"FirstName"	TEXT,
	"LastName"	TEXT,
	"DoB"	TEXT,
	PRIMARY KEY("Email")
);
CREATE TABLE IF NOT EXISTS "Flight" (
	"Number"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"Departure"	TEXT,
	"DepartureDate"	TEXT,
	"DepartureTime"	TEXT,
	"Arrival"	TEXT,
	"ArrivalDate"	TEXT,
	"ArrivalTime"	TEXT,
	"PlaneId"	INTEGER,
	FOREIGN KEY("Arrival") REFERENCES "Airport"("Code"),
	FOREIGN KEY("Departure") REFERENCES "Airport"("Code"),
	FOREIGN KEY("PlaneId") REFERENCES "Airplane"("Id")
);
CREATE TABLE IF NOT EXISTS "Airplane" (
	"Id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"Name"	TEXT,
	"Rows"	INTEGER,
	"Columns"	INTEGER
);
CREATE TABLE IF NOT EXISTS "Ticket" (
	"Passenger"	TEXT,
	"Flight"	INTEGER,
	"Seat"	INTEGER,
	"Class"	TEXT,
	"Price"	NUMERIC,
	FOREIGN KEY("Passenger") REFERENCES "Customer"("Email"),
	FOREIGN KEY("Flight") REFERENCES "Flight"("Number"),
	PRIMARY KEY("Passenger","Flight","Seat")
);
CREATE TABLE IF NOT EXISTS "Airport" (
	"Code"	TEXT,
	"City"	TEXT,
	"State"	TEXT,
	"Country"	TEXT,
	PRIMARY KEY("Code")
);
INSERT INTO "Customer" VALUES ('jdoe@aol.net','password','Jane','Doe',NULL);
INSERT INTO "Customer" VALUES ('jsmith@hotmal.gov','wordpass','John','Smith',NULL);
INSERT INTO "Flight" VALUES (1,'BOS','5/8/2019','12:30','JFK','5/8/2019','13:20',1);
INSERT INTO "Airplane" VALUES (1,'Boeing 737',30,6);
INSERT INTO "Airplane" VALUES (2,'Airbus A320',38,6);
INSERT INTO "Ticket" VALUES (NULL,1,1,'economy',10);
INSERT INTO "Ticket" VALUES (NULL,1,2,'economy',10);
INSERT INTO "Ticket" VALUES (NULL,1,3,'economy',10);
INSERT INTO "Ticket" VALUES (NULL,1,4,'economy',10);
INSERT INTO "Ticket" VALUES (NULL,1,5,'economy',10);
INSERT INTO "Ticket" VALUES (NULL,1,6,'economy',10);
INSERT INTO "Ticket" VALUES (NULL,1,7,'economy',10);
INSERT INTO "Ticket" VALUES (NULL,1,8,'economy',10);
INSERT INTO "Ticket" VALUES (NULL,1,9,'economy',10);
INSERT INTO "Ticket" VALUES (NULL,1,10,'economy',10);
INSERT INTO "Airport" VALUES ('BOS','Boston','MA','US');
INSERT INTO "Airport" VALUES ('JFK','New York','NY','US');
INSERT INTO "Airport" VALUES ('ATL','Atlanta','GA','US');
INSERT INTO "Airport" VALUES ('LAX','Los Angeles','CA','US');
INSERT INTO "Airport" VALUES ('SFO','San Fransisco','CA','US');
INSERT INTO "Airport" VALUES ('DEN','Denver','CO','US');
INSERT INTO "Airport" VALUES ('MIA','Miami','FL','US');
INSERT INTO "Airport" VALUES ('BWI','Baltimore','MD','US');
INSERT INTO "Airport" VALUES ('ORD','Chicago','IL','US');
INSERT INTO "Airport" VALUES ('PHL','Philadelphia','PA','US');
INSERT INTO "Airport" VALUES ('DFW','Dallas','TX','US');
INSERT INTO "Airport" VALUES ('SEA','Seattle','WA','US');
COMMIT;
