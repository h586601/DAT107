-- SQL til Oblig 3 Firma

DROP SCHEMA IF EXISTS firma CASCADE;
CREATE SCHEMA firma;
SET search_path TO firma;

CREATE TABLE ansatt(
	ansattID SERIAL,
	brukernavn VARCHAR(4) NOT NULL,
	fornavn VARCHAR(30) NOT NULL,
	etternavn VARCHAR(30) NOT NULL,
	ansDato DATE NOT NULL,
	stilling VARCHAR(30),
	mndsLønn DECIMAL(10, 0),
	CONSTRAINT ansattpk primary key(ansattID, brukernavn)
);

INSERT INTO ansatt (brukernavn, fornavn, etternavn, ansdato, stilling, mndslønn) 
VALUES
	('ab1', 'ida', 'mjelde', '2019-08-14', 'kunderådgiver', 14000);