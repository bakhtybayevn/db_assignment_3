CREATE TABLE IF NOT EXISTS "country" (
  "cname" varchar(50) UNIQUE PRIMARY KEY,
  "population" bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS "users" (
  "email" varchar(60) UNIQUE PRIMARY KEY,
  "name" varchar(30) NOT NULL,
  "surname" varchar(40) NOT NULL,
  "salary" integer,
  "phone" varchar(20) NOT NULL,
  "cname" varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS "patients" (
  "email" varchar(60) PRIMARY KEY NOT NULL
);

CREATE TABLE IF NOT EXISTS "disease_type" (
  "id" integer PRIMARY KEY,
  "description" varchar(140) NOT NULL
);

CREATE TABLE IF NOT EXISTS "disease" (
  "disease_code" varchar(50) UNIQUE PRIMARY KEY,
  "pathogen" varchar(20) NOT NULL,
  "description" varchar(140) NOT NULL,
  "id" integer NOT NULL
);

CREATE TABLE IF NOT EXISTS "discover" (
  "cname" varchar(50) NOT NULL,
  "disease_code" varchar(50) NOT NULL,
  "first_enc_date" timestamptz NOT NULL
);

CREATE TABLE IF NOT EXISTS "patient_disease" (
  "email" varchar(60) NOT NULL,
  "disease_code" varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS "public_servant" (
  "email" varchar(60) PRIMARY KEY NOT NULL,
  "department" varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS "doctor" (
  "email" varchar(60) PRIMARY KEY NOT NULL,
  "degree" varchar(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS "specialize" (
  "id" integer NOT NULL,
  "email" varchar(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS "record" (
  "email" varchar(60) NOT NULL,
  "cname" varchar(50) NOT NULL,
  "disease_code" varchar(50) NOT NULL,
  "total_deaths" integer NOT NULL,
  "total_patients" integer NOT NULL
);
