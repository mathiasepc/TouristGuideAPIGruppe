-- Her bruger vi begrebet DDL Data Definition Language

CREATE DATABASE IF NOT EXISTS TOURIST_GUIDE DEFAULT CHARACTER SET utf8;
USE TOURIST_GUIDE;
DROP TABLE if EXISTS ATTRACTION_TAG;
DROP TABLE if EXISTS TAG;
DROP TABLE if EXISTS ATTRACTION;
DROP TABLE if EXISTS CITY;

CREATE TABLE CITY(
                     Id integer NOT NULL AUTO_INCREMENT,
                     Name varchar(50),
                     PRIMARY KEY (Id)
);

CREATE TABLE ATTRACTION(
                           Id integer NOT NULL AUTO_INCREMENT,
                           Name varchar(50) UNIQUE NOT NULL,
                           Description varchar(255),
                           CityId integer NOT NULL,
                           PRIMARY KEY (Id),
                           FOREIGN KEY (CityId) REFERENCES CITY(Id)
);

CREATE TABLE TAG(
                    Id integer NOT NULL AUTO_INCREMENT,
                    Name varchar(50) UNIQUE NOT NULL,
                    PRIMARY KEY (Id)
);

CREATE TABLE ATTRACTION_TAG(
                               AttractionId integer NOT NULL,
                               TagId integer NOT NULL,
                               PRIMARY KEY (AttractionId, Tagid),
                               FOREIGN KEY (AttractionId) REFERENCES ATTRACTION(Id),
                               FOREIGN KEY (TagId) REFERENCES TAG(Id)
);

