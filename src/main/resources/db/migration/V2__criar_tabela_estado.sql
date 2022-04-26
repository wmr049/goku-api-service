CREATE SEQUENCE estado_id_seq AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE TABLE estado(
	id BIGINT GENERATED BY DEFAULT AS SEQUENCE estado_id_seq PRIMARY KEY,
	sigla varchar(3) NOT NULL,	
	tipo_regiao varchar(20) NOT NULL,
	ibge_id varchar(20) NOT NULL,
	nome varchar(50) NOT NULL,
	conjuncao varchar(5) NOT NULL,
	reference varchar(50) NOT NULL,
	pais_id BIGINT NOT NULL,
	FOREIGN KEY (pais_id) REFERENCES pais(id)
);