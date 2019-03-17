DROP DATABASE IF EXISTS agenda;
CREATE DATABASE agenda;
USE agenda;


CREATE TABLE tipo_persona
(
  idTipoPersona int(10) NOT NULL AUTO_INCREMENT,
  tipo varchar(20),
  PRIMARY KEY (idTipoPersona)
);

CREATE TABLE localidad
(
  idLocalidad int(10) NOT NULL AUTO_INCREMENT,
  nombre_localidad varchar(20),
  PRIMARY KEY (idLocalidad)
);

CREATE TABLE domicilio
(
  idDomicilio int(10) NOT NULL AUTO_INCREMENT,
  calle varchar(20),
  altura int(10),
  piso int(10),
  depto varchar(20),
  idLocalidad int(10),
  PRIMARY KEY (idDomicilio),
  FOREIGN KEY (idLocalidad) REFERENCES localidad (idLocalidad)
);

CREATE TABLE persona
(
  idPersona int(10) NOT NULL AUTO_INCREMENT,
  nombre varchar(45) NOT NULL,
  telefono varchar(20) NOT NULL,
  PRIMARY KEY (idPersona),
  idDomicilio int(10),
  idTipoPersona int(10),
  FOREIGN KEY (idTipoPersona) REFERENCES tipo_persona  (idTipoPersona),
  FOREIGN KEY (idDomicilio) REFERENCES domicilio   (idDomicilio)
);