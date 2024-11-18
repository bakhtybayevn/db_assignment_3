-- Populate Country Table
INSERT INTO country (cname, population) VALUES
('USA', 331000000),
('India', 1391000000),
('Canada', 38000000),
('China', 1440000000),
('France', 67000000),
('Germany', 83000000),
('Kazakhstan', 19000000),
('Japan', 125000000),
('Australia', 26000000),
('Russia', 144100000);

-- Populate Users Table
INSERT INTO users (email, name, surname, salary, phone, cname) VALUES
('john.doe@usa.com', 'John', 'Doe', 50000, '+123456789', 'USA'),
('john.jr@usa.com', 'John', 'Jr', 100000, '+1234567890', 'USA'),
('jane.doe@canada.com', 'Jane', 'Doe', 60000, '+123456789', 'Canada'),
('raj.patel@india.com', 'Raj', 'Patel', 40000, '+123456789', 'India'),
('li.wei@china.com', 'Li', 'Wei', 70000, '+123456789', 'China'),
('anna.schmidt@germany.com', 'Anna', 'Schmidt', 80000, '+123456789', 'Germany'),
('ivan.ivanov@russia.com', 'Ivan', 'Ivanov', 45000, '+123456789', 'Russia'),
('marie.curie@france.com', 'Marie', 'Curie', 55000, '+123456789', 'France'),
('antonie.atm@france.com', 'Antonie', 'Atm', 85000, '+1234567890', 'France'),
('hiro.tanaka@japan.com', 'Hiro', 'Tanaka', 90000, '+123456789', 'Japan'),
('bruce.smith@australia.com', 'Bruce', 'Smith', 50000, '+123456789', 'Australia'),
('azamat.bek@kazakhstan.com', 'Azamat', 'Bek', 40000, '+123456789', 'Kazakhstan');

-- Populate Disease Type Table
INSERT INTO disease_type (id, description) VALUES
(1, 'Infectious Diseases'),
(2, 'Bacterial Infections'),
(3, 'Neurological Disorders'),
(4, 'Cardiovascular Diseases'),
(5, 'Respiratory Diseases'),
(6, 'Parasites'),
(7, 'Metabolic Disorders'),
(8, 'Genetic Disorders'),
(9, 'Digestive Disorders'),
(10, 'Other'),
(11, 'Virology');

-- Populate Disease Table
INSERT INTO disease (disease_code, pathogen, description, id) VALUES
('TB', 'Bacteria', 'Tuberculosis', 2),
('COVID-19', 'Virus', 'Coronavirus disease 2019', 1),
('FLU', 'Virus', 'Seasonal Influenza', 1),
('ASTHMA', 'Chronic', 'Chronic respiratory disease', 5),
('DENGUE', 'Virus', 'Dengue Fever', 1),
('MALARIA', 'Parasite', 'Malaria caused by Plasmodium', 6),
('CANCER', 'Genetic', 'Cancer of various types', 8),
('HYPERTENSION', 'Unknown', 'High blood pressure', 4),
('DIABETES', 'Metabolic', 'Diabetes Mellitus', 7),
('DEPRESSION', 'Neurological', 'Major depressive disorder', 3);

-- Populate Discover Table
INSERT INTO discover (cname, disease_code, first_enc_date) VALUES
('USA', 'COVID-19', '2019-12-01'),
('India', 'TB', '1945-06-01'),
('China', 'FLU', '2000-01-01'),
('Kazakhstan', 'MALARIA', '1980-05-01'),
('Russia', 'ASTHMA', '1995-11-01'),
('Japan', 'CANCER', '1970-01-01'),
('France', 'HYPERTENSION', '2005-08-01'),
('Germany', 'DEPRESSION', '2010-03-01'),
('Australia', 'DIABETES', '1999-05-01'),
('Canada', 'DENGUE', '2015-07-01');

-- Populate Patients Table
INSERT INTO patients (email) VALUES
('john.doe@usa.com'),
('jane.doe@canada.com'),
('raj.patel@india.com'),
('li.wei@china.com'),
('anna.schmidt@germany.com'),
('ivan.ivanov@russia.com'),
('marie.curie@france.com'),
('hiro.tanaka@japan.com'),
('bruce.smith@australia.com'),
('azamat.bek@kazakhstan.com');

-- Populate Patient Disease Table
INSERT INTO patient_disease (email, disease_code) VALUES
('john.doe@usa.com', 'COVID-19'),
('jane.doe@canada.com', 'DENGUE'),
('raj.patel@india.com', 'TB'),
('li.wei@china.com', 'FLU'),
('anna.schmidt@germany.com', 'ASTHMA'),
('ivan.ivanov@russia.com', 'DEPRESSION'),
('marie.curie@france.com', 'CANCER'),
('hiro.tanaka@japan.com', 'MALARIA'),
('bruce.smith@australia.com', 'DIABETES'),
('azamat.bek@kazakhstan.com', 'HYPERTENSION');

-- Populate Public Servant Table
INSERT INTO public_servant (email, department) VALUES
('john.doe@usa.com', 'Health'),
('antonie.atm@france.com', 'Health'),
('raj.patel@india.com', 'Public Safety'),
('li.wei@china.com', 'Emergency'),
('hiro.tanaka@japan.com', 'Research'),
('marie.curie@france.com', 'Public Health'),
('jane.doe@canada.com', 'Wellness'),
('anna.schmidt@germany.com', 'Research'),
('bruce.smith@australia.com', 'Emergency'),
('ivan.ivanov@russia.com', 'Public Health'),
('azamat.bek@kazakhstan.com', 'Public Safety');

-- Populate Doctor Table
INSERT INTO doctor (email, degree) VALUES
('john.doe@usa.com', 'MD'),
('jane.doe@canada.com', 'PhD'),
('raj.patel@india.com', 'MBBS'),
('li.wei@china.com', 'MD'),
('anna.schmidt@germany.com', 'PhD'),
('ivan.ivanov@russia.com', 'MD'),
('marie.curie@france.com', 'MBBS'),
('hiro.tanaka@japan.com', 'MD'),
('bruce.smith@australia.com', 'PhD'),
('azamat.bek@kazakhstan.com', 'MBBS'),
('john.jr@usa.com', 'MD');

-- Populate Specialize Table
INSERT INTO specialize (id, email) VALUES
(1, 'john.doe@usa.com'),
(1, 'jane.doe@canada.com'),
(2, 'raj.patel@india.com'),
(3, 'li.wei@china.com'),
(4, 'anna.schmidt@germany.com'),
(5, 'ivan.ivanov@russia.com'),
(6, 'marie.curie@france.com'),
(7, 'hiro.tanaka@japan.com'),
(8, 'bruce.smith@australia.com'),
(9, 'azamat.bek@kazakhstan.com'),
(11, 'john.doe@usa.com'),
(11, 'jane.doe@canada.com'),
(2, 'jane.doe@canada.com'),
(11, 'john.jr@usa.com');

-- Populate Record Table
INSERT INTO record (email, cname, disease_code, total_deaths, total_patients) VALUES
('john.doe@usa.com', 'USA', 'COVID-19', 1000, 50000),
('raj.patel@india.com', 'India', 'TB', 2000, 100000),
('li.wei@china.com', 'China', 'FLU', 100, 30000),
('hiro.tanaka@japan.com', 'Japan', 'MALARIA', 300, 5000),
('marie.curie@france.com', 'France', 'CANCER', 500, 10000),
('antonie.atm@france.com', 'France', 'COVID-19', 100, 10000),
('jane.doe@canada.com', 'Canada', 'DENGUE', 50, 1000),
('anna.schmidt@germany.com', 'Germany', 'DEPRESSION', 0, 7000),
('bruce.smith@australia.com', 'Australia', 'DIABETES', 100, 2000),
('ivan.ivanov@russia.com', 'Russia', 'ASTHMA', 200, 9000),
('azamat.bek@kazakhstan.com', 'Kazakhstan', 'HYPERTENSION', 0, 4000);
