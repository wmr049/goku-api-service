--PAIS
INSERT INTO pais (ddi, iso, nacionalidade, nome, conjuncao) VALUES('055', 'BR', 'Brasileira', 'Brasil', 'Do');
INSERT INTO pais (ddi, iso, nacionalidade, nome, conjuncao) VALUES('001', 'US', 'Americana', 'Estados Unidos', 'Do');
INSERT INTO pais (ddi, iso, nacionalidade, nome, conjuncao) VALUES('002', 'AR', 'Argentina', 'Argentina', 'Do');
INSERT INTO pais (ddi, iso, nacionalidade, nome, conjuncao) VALUES('003', 'CH', 'Chileno', 'Chile', 'Do');
INSERT INTO pais (ddi, iso, nacionalidade, nome, conjuncao) VALUES('004', 'CN', 'Chines', 'China', 'Do');
--ESTADO
INSERT INTO estado (sigla, tipo_regiao, ibge_id, pais_id, nome, conjuncao, reference) VALUES('SP', 'SUDESTE', '35', (SELECT id FROM pais WHERE ddi = '055'), 'S�o Paulo', 'de SP', '01');
INSERT INTO estado (sigla, tipo_regiao, ibge_id, pais_id, nome, conjuncao, reference) VALUES('RJ', 'SUDESTE', '34', (SELECT id FROM pais WHERE ddi = '055'), 'Rio de Janeiro', 'de RJ', '02');
INSERT INTO estado (sigla, tipo_regiao, ibge_id, pais_id, nome, conjuncao, reference) VALUES('MA', 'NORDESTE', '33', (SELECT id FROM pais WHERE ddi = '055'), 'Maranh�o', 'de MA', '02');
INSERT INTO estado (sigla, tipo_regiao, ibge_id, pais_id, nome, conjuncao, reference) VALUES('CA', 'EAST', '01', (SELECT id FROM pais WHERE ddi = '001'), 'California', 'de CA', '03');
INSERT INTO estado (sigla, tipo_regiao, ibge_id, pais_id, nome, conjuncao, reference) VALUES('NY', 'WEST', '02', (SELECT id FROM pais WHERE ddi = '001'), 'Nova York', 'de NY', '04');

--TIPO LOCALIDADE
INSERT INTO tipo_localidade (nome, reference, codigo) VALUES( 'Municipio', null, 'MUNICIPIO');
INSERT INTO tipo_localidade (nome, reference, codigo) VALUES( 'Distrito', null, 'DISTRITO');
INSERT INTO tipo_localidade (nome, reference, codigo) VALUES( 'Povoado', null, 'POVOADO');

--LOCALIDADE
INSERT INTO localidade (nome_abreviado, nome, cep, situacao, loc_nu_sequencial_sub, temp, estado_id, tipo_localidade_id, municipio_id, populacao_data, ibge_id, naturalidade, ddd, municipio_pai_id, conjuncao, populacao, reference, ibge_id_2) VALUES ('Areias','Areias','12820000',0,0,null,1,1,8793,'2010-01-28 14:42:57','3503505','Areias',null,null,'De',3571,'01270061',null);
INSERT INTO localidade (nome_abreviado, nome, cep, situacao, loc_nu_sequencial_sub, temp, estado_id, tipo_localidade_id, municipio_id, populacao_data, ibge_id, naturalidade, ddd, municipio_pai_id, conjuncao, populacao, reference, ibge_id_2) VALUES ('Arapixi','Arapixi',null,0,0,null,3,3,242,null,'1302603','Arapixi',null,null,'De',0,'01030091','1302603');
INSERT INTO localidade (nome_abreviado, nome, cep, situacao, loc_nu_sequencial_sub, temp, estado_id, tipo_localidade_id, municipio_id, populacao_data, ibge_id, naturalidade, ddd, municipio_pai_id, conjuncao, populacao, reference, ibge_id_2) VALUES ('Anan�s','Anan�s',null,0,0,null,3,3,2305,null,'1701002','Anan�s',null,null,'De',0,'01100357',null);
INSERT INTO localidade (nome_abreviado, nome, cep, situacao, loc_nu_sequencial_sub, temp, estado_id, tipo_localidade_id, municipio_id, populacao_data, ibge_id, naturalidade, ddd, municipio_pai_id, conjuncao, populacao, reference, ibge_id_2) VALUES ('Rondominas','Rondominas',null,0,0,null,3,1,7276,null,'110015525','Rondominas',null,7225,'De',0,'01220065',null);
INSERT INTO localidade (nome_abreviado, nome, cep, situacao, loc_nu_sequencial_sub, temp, estado_id, tipo_localidade_id, municipio_id, populacao_data, ibge_id, naturalidade, ddd, municipio_pai_id, conjuncao, populacao, reference, ibge_id_2) VALUES ('Po�','Po�',null,0,0,null,2,3,7778,null,null,'Po�',null,null,'De',0,'01240936',null);
INSERT INTO localidade (nome_abreviado, nome, cep, situacao, loc_nu_sequencial_sub, temp, estado_id, tipo_localidade_id, municipio_id, populacao_data, ibge_id, naturalidade, ddd, municipio_pai_id, conjuncao, populacao, reference, ibge_id_2) VALUES ('Araras','Araras',null,1,0,null,1,1,8790,'2010-01-28 14:42:57','3503307','Araras',null,null,'De',108683,'01270056',null);
INSERT INTO localidade (nome_abreviado, nome, cep, situacao, loc_nu_sequencial_sub, temp, estado_id, tipo_localidade_id, municipio_id, populacao_data, ibge_id, naturalidade, ddd, municipio_pai_id, conjuncao, populacao, reference, ibge_id_2) VALUES ('Arax�s','Arax�s (Presidente Bernardes)','19310000',0,9530,null,1,2,9637,null,'3541208','Arax�s',null,9213,'De',0,'01270057',null);
INSERT INTO localidade (nome_abreviado, nome, cep, situacao, loc_nu_sequencial_sub, temp, estado_id, tipo_localidade_id, municipio_id, populacao_data, ibge_id, naturalidade, ddd, municipio_pai_id, conjuncao, populacao, reference, ibge_id_2) VALUES ('Arcadas','Arcadas (Amparo)',null,2,8886,null,1,2,9433,null,'3501905','Arcadas',null,8774,'De',0,'01270058',null);
INSERT INTO localidade (nome_abreviado, nome, cep, situacao, loc_nu_sequencial_sub, temp, estado_id, tipo_localidade_id, municipio_id, populacao_data, ibge_id, naturalidade, ddd, municipio_pai_id, conjuncao, populacao, reference, ibge_id_2) VALUES ('Arco-�ris','Arco-�ris','17630000',0,0,null,1,1,8791,'2010-01-28 14:42:57','3503356','Arco-�ris',null,null,'De',2002,'01270059',null);
INSERT INTO localidade (nome_abreviado, nome, cep, situacao, loc_nu_sequencial_sub, temp, estado_id, tipo_localidade_id, municipio_id, populacao_data, ibge_id, naturalidade, ddd, municipio_pai_id, conjuncao, populacao, reference, ibge_id_2) VALUES ('Arealva','Arealva','17160000',0,0,null,1,1,8792,'2010-01-28 14:42:57','3503406','Arealva',null,null,'De',7504,'01270060',null);
INSERT INTO localidade (nome_abreviado, nome, cep, situacao, loc_nu_sequencial_sub, temp, estado_id, tipo_localidade_id, municipio_id, populacao_data, ibge_id, naturalidade, ddd, municipio_pai_id, conjuncao, populacao, reference, ibge_id_2) VALUES ('Campinas','Campinas',null,1,0,null,1,1,8861,'2010-01-28 14:42:57','3509502','Campinas',null,null,'De',1039297,'01270162',null);
INSERT INTO localidade (nome_abreviado, nome, cep, situacao, loc_nu_sequencial_sub, temp, estado_id, tipo_localidade_id, municipio_id, populacao_data, ibge_id, naturalidade, ddd, municipio_pai_id, conjuncao, populacao, reference, ibge_id_2) VALUES ('Paul�nia','Paul�nia',null,1,0,null,1,1,9161,'2010-01-28 14:42:57','3536505','Paul�nia',null,null,'De',73118,'01270583',null);

--BAIRRO
INSERT INTO public.bairro (localidade_id, nome, nome_abreviado) VALUES(12, 'Jo�o Aranha', 'J Aranha');
INSERT INTO public.bairro (localidade_id, nome, nome_abreviado) VALUES(11, 'Jardim do Lago', 'Jd Lago');

--TIPO LOGRADOURO
INSERT INTO public.tipo_logradouro (nome, codigo) VALUES('Avenida', 'AVENIDA');
INSERT INTO public.tipo_logradouro (nome, codigo) VALUES('Rua', 'RUA');

--LOGRADOURO
INSERT INTO public.logradouro (ufe, localidade_id, nome_abreviado, nome, bairro_inicio_id, bairro_fim_id, cep, complemento, status_tipo_log, nome_sem_acento, correio, referencia, tipo_logradouro_id, bairro, localidade) VALUES('SP', 11, 'Senador Antônio Lacerda Franco', 'Avenida Senador Antônio Lacerda Franco - de 522/523 ao fim', 2, null, '13050030', '- de 522/523 ao fim', 'S', 'Senador Antonio Lacerda Franco', 'N', 'N', 1, 'Jardim do Lago', 'Campinas');
