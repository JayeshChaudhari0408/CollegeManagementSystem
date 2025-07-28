INSERT INTO professor (title) VALUES
('Dr. A. Kumar'),
('Dr. S. Mehta'),
('Dr. R. Verma');

INSERT INTO student (name) VALUES
('Arjun Sharma'),
('Sneha Patel'),
('Rahul Nair');


INSERT INTO subject (title) VALUES
('Data Structures'),
('Operating Systems'),
('Database Systems'),
('Algorithms');

INSERT INTO admission_record (fees, student_id) VALUES
(50000, 1),
(55000, 2),
(60000, 3);

INSERT INTO student_professor (student_id, professor_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3);

INSERT INTO student_subject (student_id, subject_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3),
(3, 4);