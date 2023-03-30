DROP DATABASE IF EXISTS MysqlDatabase;
CREATE DATABASE MysqlDatabase;
USE MysqlDatabase;

CREATE TABLE StudentDetails
(
ID VARCHAR(10) NOT NULL,
StudentName VARCHAR(25) NOT NULL,
Std VARCHAR(10) NOT NULL,
Age TINYINT NOT NULL,
CONSTRAINT Student_PK PRIMARY KEY (ID)
);

INSERT INTO StudentDetails VALUES('1','teja vardhan','ug',21);
INSERT INTO StudentDetails VALUES('2','akash','11th',18);
INSERT INTO StudentDetails VALUES('3','harish','12th',18);
INSERT INTO StudentDetails VALUES('4','karthick','ug',21);
INSERT INTO StudentDetails VALUES('5','harsha','7th',15);
INSERT INTO StudentDetails VALUES('6','vamsi','9th',16);
INSERT INTO StudentDetails VALUES('7','ramanand','12th',20);

SELECT * FROM StudentDetails;
-- SELECT StudentName,Std FROM StudentDetails  WHERE ID='1'; 

CREATE TABLE StudentMarks
(
ID VARCHAR(10) NOT NULL,
maths INT ,
social INT,
hindi INT
);
INSERT INTO StudentMarks VALUES('1',90,99,89);
INSERT INTO StudentMarks VALUES('2',78,80,87);
INSERT INTO StudentMarks VALUES('3',99,76,82);
INSERT INTO StudentMarks VALUES('4',76,90,92);
INSERT INTO StudentMarks VALUES('5',67,95,80);
INSERT INTO StudentMarks VALUES('6',84,87,70);
INSERT INTO StudentMarks VALUES('7',95,77,60);

SELECT * FROM StudentMarks;









