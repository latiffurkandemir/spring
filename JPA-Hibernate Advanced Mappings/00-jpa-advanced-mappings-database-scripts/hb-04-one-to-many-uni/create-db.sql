-- Drop schema if it exists
DROP SCHEMA IF EXISTS "hb-04-one-to-many-uni" CASCADE;

-- Create schema
CREATE SCHEMA "hb-04-one-to-many-uni";

-- Set search path to the new schema
SET search_path TO "hb-04-one-to-many-uni";

-- Disable foreign key checks (PostgreSQL does not have this direct equivalent, so we skip this)

-- Create instructor_detail table
CREATE TABLE instructor_detail (
  id SERIAL PRIMARY KEY,
  youtube_channel VARCHAR(128),
  hobby VARCHAR(45)
);

-- Create instructor table
CREATE TABLE instructor (
  id SERIAL PRIMARY KEY,s
  first_name VARCHAR(45),
  last_name VARCHAR(45),
  email VARCHAR(45),
  instructor_detail_id INT REFERENCES instructor_detail(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Create course table
CREATE TABLE course (
  id SERIAL PRIMARY KEY,
  title VARCHAR(128) UNIQUE,
  instructor_id INT REFERENCES instructor(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Create review table
CREATE TABLE review (
  id SERIAL PRIMARY KEY,
  comment VARCHAR(256),
  course_id INT REFERENCES course(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

