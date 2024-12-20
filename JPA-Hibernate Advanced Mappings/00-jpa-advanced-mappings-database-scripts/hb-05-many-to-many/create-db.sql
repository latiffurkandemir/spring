-- Drop schema if it exists
DROP SCHEMA IF EXISTS "hb-05-many-to-many" CASCADE;

-- Create the schema
CREATE SCHEMA "hb-05-many-to-many";

-- Switch to the new schema
SET search_path TO "hb-05-many-to-many";

-- Create the instructor_detail table
CREATE TABLE instructor_detail (
    id SERIAL PRIMARY KEY,
    youtube_channel VARCHAR(128),
    hobby VARCHAR(45)
);

-- Create the instructor table
CREATE TABLE instructor (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(45),
    last_name VARCHAR(45),
    email VARCHAR(45),
    instructor_detail_id INT,
    CONSTRAINT FK_DETAIL FOREIGN KEY (instructor_detail_id)
    REFERENCES instructor_detail (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- Create the course table
CREATE TABLE course (
    id SERIAL PRIMARY KEY,
    title VARCHAR(128) UNIQUE,
    instructor_id INT,
    CONSTRAINT FK_INSTRUCTOR FOREIGN KEY (instructor_id)
    REFERENCES instructor (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- Create the review table
CREATE TABLE review (
    id SERIAL PRIMARY KEY,
    comment VARCHAR(256),
    course_id INT,
    CONSTRAINT FK_COURSE FOREIGN KEY (course_id)
    REFERENCES course (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- Create the student table
CREATE TABLE student (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(45),
    last_name VARCHAR(45),
    email VARCHAR(45)
);

-- Create the course_student join table
CREATE TABLE course_student (
    course_id INT NOT NULL,
    student_id INT NOT NULL,
    PRIMARY KEY (course_id, student_id),
    CONSTRAINT FK_COURSE_05 FOREIGN KEY (course_id)
    REFERENCES course (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT FK_STUDENT FOREIGN KEY (student_id)
    REFERENCES student (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
