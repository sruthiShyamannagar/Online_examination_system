CREATE DATABASE IF NOT EXISTS online_examination;
USE online_examination;

CREATE TABLE IF NOT EXISTS admins (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS exams (
    exam_id INT PRIMARY KEY AUTO_INCREMENT,
    exam_name VARCHAR(100) NOT NULL,
    subject VARCHAR(100) NOT NULL,
    duration_minutes INT NOT NULL DEFAULT 10,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS questions (
    question_id INT PRIMARY KEY AUTO_INCREMENT,
    exam_id INT NOT NULL,
    question_text VARCHAR(500) NOT NULL,
    option_a VARCHAR(255) NOT NULL,
    option_b VARCHAR(255) NOT NULL,
    option_c VARCHAR(255) NOT NULL,
    option_d VARCHAR(255) NOT NULL,
    correct_answer CHAR(1) NOT NULL,
    FOREIGN KEY (exam_id) REFERENCES exams(exam_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS results (
    result_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    exam_id INT NOT NULL,
    score INT NOT NULL,
    total_questions INT NOT NULL,
    percentage DECIMAL(5,2) NOT NULL,
    exam_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (exam_id) REFERENCES exams(exam_id) ON DELETE CASCADE
);

INSERT IGNORE INTO admins(username, password) VALUES ('admin', 'admin123');

INSERT INTO exams(exam_name, subject, duration_minutes)
SELECT 'Java Fundamentals', 'Java', 10
WHERE NOT EXISTS (SELECT 1 FROM exams WHERE exam_name='Java Fundamentals');

SET @exam_id = (SELECT exam_id FROM exams WHERE exam_name='Java Fundamentals' LIMIT 1);

INSERT INTO questions(exam_id, question_text, option_a, option_b, option_c, option_d, correct_answer)
SELECT @exam_id, 'Which keyword is used to inherit a class in Java?', 'implements', 'extends', 'inherits', 'super', 'B'
WHERE NOT EXISTS (SELECT 1 FROM questions WHERE exam_id=@exam_id);

INSERT INTO questions(exam_id, question_text, option_a, option_b, option_c, option_d, correct_answer)
SELECT @exam_id, 'Which method is the entry point of a Java application?', 'start()', 'run()', 'main()', 'init()', 'C'
WHERE NOT EXISTS (SELECT 1 FROM questions WHERE exam_id=@exam_id AND question_text='Which method is the entry point of a Java application?');

INSERT INTO questions(exam_id, question_text, option_a, option_b, option_c, option_d, correct_answer)
SELECT @exam_id, 'Which collection does not allow duplicate elements?', 'List', 'Set', 'Queue', 'ArrayList', 'B'
WHERE NOT EXISTS (SELECT 1 FROM questions WHERE exam_id=@exam_id AND question_text='Which collection does not allow duplicate elements?');

INSERT INTO questions(exam_id, question_text, option_a, option_b, option_c, option_d, correct_answer)
SELECT @exam_id, 'Which keyword creates an object in Java?', 'class', 'object', 'new', 'create', 'C'
WHERE NOT EXISTS (SELECT 1 FROM questions WHERE exam_id=@exam_id AND question_text='Which keyword creates an object in Java?');

INSERT INTO questions(exam_id, question_text, option_a, option_b, option_c, option_d, correct_answer)
SELECT @exam_id, 'Which of these is not a primitive data type?', 'int', 'double', 'String', 'boolean', 'C'
WHERE NOT EXISTS (SELECT 1 FROM questions WHERE exam_id=@exam_id AND question_text='Which of these is not a primitive data type?');
