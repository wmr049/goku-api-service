DELETE FROM logradouro;
DELETE FROM tipo_logradouro;
DELETE FROM bairro;
DELETE FROM localidade;
DELETE FROM tipo_localidade;
DELETE FROM estado;
DELETE FROM pais;


ALTER SEQUENCE tipo_logradouro_seq RESTART WITH 1;
ALTER SEQUENCE logradouro_id_seq RESTART WITH 1;
ALTER SEQUENCE bairro_id_seq RESTART WITH 1;
ALTER SEQUENCE tipo_localidade_seq RESTART WITH 1;
ALTER SEQUENCE localidade_id_seq RESTART WITH 1;
ALTER SEQUENCE estado_id_seq RESTART WITH 1;
ALTER SEQUENCE pais_id_seq RESTART WITH 1;
