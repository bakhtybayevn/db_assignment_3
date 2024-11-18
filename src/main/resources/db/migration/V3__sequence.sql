CREATE SEQUENCE disease_type_id_seq START 1;
ALTER TABLE disease_type ALTER COLUMN id SET DEFAULT nextval('disease_type_id_seq');