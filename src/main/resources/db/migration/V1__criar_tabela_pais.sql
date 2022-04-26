CREATE SEQUENCE pais_id_seq AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE TABLE pais(
	id BIGINT GENERATED BY DEFAULT AS SEQUENCE pais_id_seq PRIMARY KEY,
	nome varchar(50) NOT NULL,	
	conjuncao varchar(5) NOT NULL,
	nacionalidade varchar(50) NOT NULL,
	ddi varchar(5) NOT NULL,
	iso varchar(5) NOT NULL
);