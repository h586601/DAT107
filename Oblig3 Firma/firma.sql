-- SQL til Oblig 3 Firma

DROP SCHEMA IF EXISTS firma CASCADE;
CREATE SCHEMA firma;
SET search_path TO firma;

CREATE TABLE avdeling(
	avdelingID SERIAL,
	avdNavn VARCHAR(30),
	avdelingsleder INTEGER,
	CONSTRAINT avdelingpk PRIMARY KEY(avdelingID)
);

CREATE TABLE ansatt(
	ansattID SERIAL,
	brukernavn VARCHAR(4) NOT NULL Unique,
	fornavn VARCHAR(30) NOT NULL,
	etternavn VARCHAR(30) NOT NULL,
	ansDato DATE NOT NULL,
	stilling VARCHAR(30),
	mndsLonn DECIMAL(10, 0),
	avdelingID INTEGER REFERENCES avdeling(avdelingID) NOT NULL,
	CONSTRAINT ansattpk PRIMARY KEY(ansattID)
);

-- SQL til Oblig 3 Firma

DROP SCHEMA IF EXISTS firma CASCADE;
CREATE SCHEMA firma;
SET search_path TO firma;

CREATE TABLE avdeling(
	avdelingID SERIAL,
	avdNavn VARCHAR(30),
	avdelingsleder INTEGER,
	CONSTRAINT avdelingpk PRIMARY KEY(avdelingID)
);

CREATE TABLE ansatt(
	ansattID SERIAL,
	brukernavn VARCHAR(4) NOT NULL Unique,
	fornavn VARCHAR(30) NOT NULL,
	etternavn VARCHAR(30) NOT NULL,
	ansDato DATE NOT NULL,
	stilling VARCHAR(30),
	mndsLonn DECIMAL(10, 0),
	avdelingID INTEGER REFERENCES avdeling(avdelingID) NOT NULL,
	CONSTRAINT ansattpk PRIMARY KEY(ansattID)
);


INSERT INTO avdeling(avdNavn)
VALUES
	('Test Bergen'),
	('Test Oslo'),
	('Analyse Oslo');

INSERT INTO ansatt (brukernavn, fornavn, etternavn, ansdato, stilling, mndslonn, avdelingid) 
VALUES
	('AB1', 'Ida', 'Mjelde', '2019-08-14', 'Testleder', 30000, 1),
	('AB2', 'Thomas', 'Smith', '2019-08-14', 'Tester', 23000, 1),
	('AB3', 'Thomas', 'Blom', '2014-08-14', 'Senior tester', 27000, 1),
	('AB4', 'Ola', 'Nordmann', '2019-08-14', 'Testleder', 30000, 2),
	('AB5', 'Nils', 'Nilsen', '2019-08-14', 'Tester', 23000, 2),
	('AB6', 'Kari', 'Nordmann', '2014-08-14', 'Senior tester', 27000, 2),
	('AB7', 'Sven-Olai', 'Etternavn', '2019-08-14', 'Leder', 30000, 3),
	('AB8', 'Harald', 'Etternavn', '2019-08-14', 'Analytiker', 23000, 3),
	('AB9', 'Tomfor', 'Ideer', '2014-08-14', 'Analytiker', 27000, 3);

ALTER TABLE avdeling
ADD CONSTRAINT ansattfk
FOREIGN KEY (avdelingsleder) REFERENCES Ansatt(ansattid); 

UPDATE avdeling
SET avdelingsleder = 1
WHERE avdelingID = 1;

UPDATE avdeling
SET avdelingsleder = 4
WHERE avdelingID = 2;

UPDATE avdeling
SET avdelingsleder = 7
WHERE avdelingID = 3;


CREATE TABLE prosjekt(
	prosjektID SERIAL,
	prosjektnavn VARCHAR(30),
	beskrivelse VARCHAR(50),
	CONSTRAINT prosjektpk PRIMARY KEY(prosjektID)
);

CREATE TABLE prosjektdeltagelse(
	prosjektdeltaID SERIAL,
	ansattID INTEGER,
	prosjektID INTEGER,
	rolle VARCHAR(30),
	timer INTEGER,
	CONSTRAINT prosjektdeltagelsepk PRIMARY KEY(prosjektdeltaID),
	CONSTRAINT ansattprosjekt_unik UNIQUE (ansattID, prosjektID),
	CONSTRAINT ansattfk FOREIGN KEY(ansattID) REFERENCES ansatt(ansattID),
	CONSTRAINT prosjektfk FOREIGN KEY(prosjektID) REFERENCES prosjekt(prosjektID)
);

INSERT INTO prosjekt(prosjektnavn, beskrivelse)
VALUES
	('Svikdetektering', 'System som skal oppdage svik'),
	('Sentralt Bergen', 'Videreutvikling av Bybanen i Bergen'),
	('Bergen Brainstorm', 'Arrangementsprosjekt');

INSERT INTO prosjektdeltagelse(ansattID, prosjektID, rolle, timer)
VALUES
	(1, 1, 'Prosjektleder', 150),
	(2, 1, 'Testleder', 100),
	(3, 1, 'Software tester', 100);