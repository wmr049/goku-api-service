CREATE SEQUENCE localidade_id_seq AS BIGINT START WITH 1 INCREMENT BY 1;

CREATE TABLE localidade (
	id BIGINT GENERATED BY DEFAULT AS SEQUENCE localidade_id_seq PRIMARY KEY,
	nome_abreviado varchar(50) NULL,
	nome varchar(60) NULL,
	cep varchar(16) NULL,
	situacao int NULL,
	loc_nu_sequencial_sub int NULL,
	temp varchar(8) NULL,
	estado_id int NULL,
	tipo_localidade_id int NULL,
	municipio_id int NULL,
	populacao_data timestamp NULL,
	ibge_id varchar(100) NULL,
	naturalidade varchar(100) NULL,
	ddd varchar(3) NULL,
	municipio_pai_id int NULL,
	conjuncao varchar(50) NULL,
	populacao int NULL,
	reference varchar(100) NULL,
	ibge_id_2 varchar(10) NULL,
	FOREIGN KEY (estado_id) REFERENCES estado(id),
	FOREIGN KEY (tipo_localidade_id) REFERENCES tipo_localidade(id)
);
