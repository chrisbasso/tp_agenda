USE agenda;

INSERT INTO tipo_contacto
	(tipo) 
	VALUES("facu");

INSERT INTO tipo_contacto
	(tipo) 
	VALUES("trabajo");
	
INSERT INTO tipo_contacto
	(tipo) 
	VALUES("familia");
	
INSERT INTO localidad
	(nombre_localidad) 
	VALUES("San Miguel");

INSERT INTO localidad
	(nombre_localidad) 
	VALUES("Pilar");
		
INSERT INTO localidad
	(nombre_localidad) 
	VALUES("San isidro");

INSERT INTO domicilio
	(calle, altura, piso, depto, idLocalidad)
	VALUES("Siempre viva", "678", "12","A",1);

INSERT INTO domicilio
	(calle, altura, piso, depto, idLocalidad)
	VALUES("No Siempre", "876", "1","A",2);

INSERT INTO domicilio
	(calle, altura, piso, depto, idLocalidad)
	VALUES("Casi Siempre", "444", "8","c",3);

INSERT INTO persona
	(nombre, apellido, telefono, idDomicilio, idTipoContacto, email, fecha_nacimiento)
	VALUES( "Ricardo", "Gisbert", "11", 1, 1,"rag@email.com", "1982-05-05");

INSERT INTO persona
	(nombre, apellido, telefono, idDomicilio, idTipoContacto, email, fecha_nacimiento)
	VALUES( "Christian", "Basso", "22", 2, 2,"cb@email.com", "1983-06-07");

INSERT INTO persona
	(nombre, apellido, telefono, idDomicilio, idTipoContacto, email, fecha_nacimiento)
	VALUES( "Gonzalo", "Montenegro", "33", 3, 3,"gm@email.com", "1983-07-08");