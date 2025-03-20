-- DML Data Manipulating Language

USE TOURIST_GUIDE;

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE ATTRACTION_TAG;
TRUNCATE ATTRACTION;
TRUNCATE TAG;
TRUNCATE CITY;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO CITY(Id, Name) Values(1, 'København');
INSERT INTO CITY(Id, Name) Values(2, 'Helsingør');
INSERT INTO CITY(Id, Name) Values(3, 'Klampenborg');
INSERT INTO CITY(Id, Name) Values(4, 'Aarhus');
INSERT INTO CITY(Id, Name) Values(5, 'Odense');
INSERT INTO CITY(Id, Name) Values(6, 'Aalborg');
INSERT INTO CITY(Id, Name) Values(7, 'Esbjerg');
INSERT INTO CITY(Id, Name) Values(8, 'Randers');
INSERT INTO CITY(Id, Name) Values(9, 'Kolding');
INSERT INTO CITY(Id, Name) Values(10, 'Horsens');
INSERT INTO CITY(Id, Name) Values(11, 'Vejle');
INSERT INTO CITY(Id, Name) Values(12, 'Roskilde');
INSERT INTO CITY(Id, Name) Values(13, 'Herning');
INSERT INTO CITY(Id, Name) Values(14, 'Hørsholm');
INSERT INTO CITY(Id, Name) Values(15, 'Fredericia');
INSERT INTO CITY(Id, Name) Values(16, 'Næstved');
INSERT INTO CITY(Id, Name) Values(17, 'Silkeborg');
INSERT INTO CITY(Id, Name) Values(18, 'Frederikshavn');
INSERT INTO CITY(Id, Name) Values(19, 'Hillerød');
INSERT INTO CITY(Id, Name) Values(20, 'Svendborg');
INSERT INTO CITY(Id, Name) Values(21, 'Holstebro');
INSERT INTO CITY(Id, Name) Values(22, 'Slagelse');
INSERT INTO CITY(Id, Name) Values(23, 'Tønder');
INSERT INTO CITY(Id, Name) Values(24, 'Skagen');
INSERT INTO CITY(Id, Name) Values(25, 'Viborg');

INSERT INTO ATTRACTION(Id, Name, Description, CityId) Values(1,'Tivoli','Det er en forlystelsespark på sjælland', 1);
INSERT INTO ATTRACTION(Id, Name, Description, CityId) Values(2,'Assistent kirkegården','Det er en kirkegård med mange kendte', 1);
INSERT INTO ATTRACTION(Id, Name, Description, CityId) Values(3,'Bakken','Det er en forlystelsespark på sjælland', 3);
INSERT INTO ATTRACTION(Id, Name, Description, CityId) Values(4,'Kronborg','Det er et historisk slot', 2);
INSERT INTO ATTRACTION(Id, Name, Description, CityId) Values(5,'Zoologisk have','Det er en park med dyr', 1);
INSERT INTO ATTRACTION(Id, Name, Description, CityId) Values(6,'Amalienborg','Det er et historisk slot', 1);
INSERT INTO ATTRACTION(Id, Name, Description, CityId) Values(7,'Den lille havfrue','Det er en historisk statue', 1);
INSERT INTO ATTRACTION(Id, Name, Description, CityId) Values(8,'Rundetårn','Det er en et højt tårn', 1);
INSERT INTO ATTRACTION(Id, Name, Description, CityId) Values(9,'SMK','Det er et museum', 1);
INSERT INTO ATTRACTION(Id, Name, Description, CityId) Values(10,'Glyptoteket','Det er et museum', 1);
INSERT INTO ATTRACTION(Id, Name, Description, CityId) Values(11,'Nyhavn','Det er et sted med mange restaurant', 1);


INSERT INTO TAG(Id, Name) Values(1, 'Børne venlig');
INSERT INTO TAG(Id, Name) Values(2, 'Gratis');
INSERT INTO TAG(Id, Name) Values(3, 'Museum');
INSERT INTO TAG(Id, Name) Values(4, 'Kunst');
INSERT INTO TAG(Id, Name) Values(5, 'Natur');
INSERT INTO TAG(Id, Name) Values(6, 'Restaurant');
INSERT INTO TAG(Id, Name) Values(7, 'Underholdning');

INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(1, 1);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(1, 7);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(1, 6);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(2, 5);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(2, 2);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(3, 5);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(3, 7);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(3, 1);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(3, 6);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(4, 5);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(4, 4);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(4, 3);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(5, 5);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(5, 1);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(5, 6);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(6, 4);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(6, 3);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(7, 2);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(7, 4);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(7, 5);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(8, 4);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(9, 4);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(9, 3);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(10, 4);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(10, 3);
INSERT INTO ATTRACTION_TAG(AttractionId,TagId) Values(11, 6);
