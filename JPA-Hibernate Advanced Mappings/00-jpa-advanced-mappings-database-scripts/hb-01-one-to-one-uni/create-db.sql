-- Drop schema if it exists
DROP SCHEMA IF EXISTS "hb-01-one-to-one-uni" CASCADE;

-- Create schema
CREATE SCHEMA "hb-01-one-to-one-uni";

-- Switch to the schema
SET search_path TO "hb-01-one-to-one-uni";


-- Create instructor_detail table
CREATE TABLE instructor_detail (
  id SERIAL PRIMARY KEY,
  youtube_channel VARCHAR(128),
  hobby VARCHAR(45)
);

-- Create instructor table
CREATE TABLE instructor (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(45),
  last_name VARCHAR(45),
  email VARCHAR(45),
  instructor_detail_id INT,
  CONSTRAINT FK_DETAIL FOREIGN KEY (instructor_detail_id) REFERENCES instructor_detail (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- No need to re-enable foreign key checks, constraints are always enforced in PostgreSQL.