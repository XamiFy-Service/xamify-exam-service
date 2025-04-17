CREATE DATABASE IF NOT EXISTS exam_db;
USE exam_db;

CREATE TABLE exams (
    id INT AUTO_INCREMENT PRIMARY KEY, -- Or UUID
    title VARCHAR(100) NOT NULL,
    description TEXT,
    duration_minutes INT NOT NULL,
    start_time DATETIME NOT NULL,
    final_time DATETIME NOT NULL
);