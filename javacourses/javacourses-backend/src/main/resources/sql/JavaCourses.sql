CREATE TABLE JavaCourse (
    uuid VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    duration_in_hours INT NOT NULL,
    max_participants INT NOT NULL,
    cost_in_euros DECIMAL(10,2) NOT NULL,
    course_type VARCHAR(255) NOT NULL,
    dbState VARCHAR(255) NOT NULL,
    FOREIGN KEY (course_type) REFERENCES CourseType(type_id),
    FOREIGN KEY (db_state) REFERENCES DBState(state_id)
);

CREATE TABLE DBState (
    state_id VARCHAR(255) PRIMARY KEY,
    state_name VARCHAR(255) NOT NULL,
    color VARCHAR(255)
);

CREATE TABLE CourseType (
    type_id VARCHAR(255) PRIMARY KEY,
    type_name VARCHAR(255) NOT NULL
);