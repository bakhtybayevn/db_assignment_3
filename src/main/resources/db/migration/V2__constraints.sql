ALTER TABLE "users"
ADD CONSTRAINT fk_users_country
FOREIGN KEY ("cname")
REFERENCES "country" ("cname")
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE "patients"
ADD CONSTRAINT fk_patients_users
FOREIGN KEY ("email")
REFERENCES "users" ("email")
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE "disease"
ADD CONSTRAINT fk_disease_type
FOREIGN KEY ("id")
REFERENCES "disease_type" ("id")
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE "discover"
ADD CONSTRAINT fk_discover_country
FOREIGN KEY ("cname")
REFERENCES "country" ("cname")
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE "discover"
ADD CONSTRAINT fk_discover_disease
FOREIGN KEY ("disease_code")
REFERENCES "disease" ("disease_code")
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE "patient_disease"
ADD CONSTRAINT fk_patient_disease_users
FOREIGN KEY ("email")
REFERENCES "users" ("email")
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE "patient_disease"
ADD CONSTRAINT fk_patient_disease_disease
FOREIGN KEY ("disease_code")
REFERENCES "disease" ("disease_code")
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE "public_servant"
ADD CONSTRAINT fk_public_servant_users
FOREIGN KEY ("email")
REFERENCES "users" ("email")
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE "doctor"
ADD CONSTRAINT fk_doctor_users
FOREIGN KEY ("email")
REFERENCES "users" ("email")
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE "specialize"
ADD CONSTRAINT fk_specialize_disease_type
FOREIGN KEY ("id")
REFERENCES "disease_type" ("id")
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE "specialize"
ADD CONSTRAINT fk_specialize_doctor
FOREIGN KEY ("email")
REFERENCES "doctor" ("email")
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE "record"
ADD CONSTRAINT fk_record_public_servant
FOREIGN KEY ("email")
REFERENCES "public_servant" ("email")
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE "record"
ADD CONSTRAINT fk_record_country
FOREIGN KEY ("cname")
REFERENCES "country" ("cname")
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE "record"
ADD CONSTRAINT fk_record_disease
FOREIGN KEY ("disease_code")
REFERENCES "disease" ("disease_code")
ON UPDATE CASCADE
ON DELETE CASCADE;
