-- ----------------------------
-- Question 1
-- Write the SQL code that will create the table structure for a table named
--  EMP_1. This table is a subset of the EMPLOYEE table. The basic EMP_1 table
--  structure is summarized in the following table. (Note that the JOB_CODE is
--  the FK to JOB.)
-- ----------------------------
CREATE TABLE EMP_1(
    EMP_NUM CHAR(3) PRIMARY KEY,
    EMP_LNAME VARCHAR(15) NOT NULL,
    EMP_FNAME VARCHAR(15) NOT NULL,
    EMP_INITIAL CHAR(1),
    EMP_HIREDATE DATE,
    JOB_CODE CHAR(3),
    FOREIGN KEY(JOB_CODE) REFERENCES JOB
);
-- ----------------------------
-- Question 2
-- Having created the table structure in Problem 1, write the SQL code to enter
--  the first two rows for the table shown in Figure P7.2.
-- ----------------------------
INSERT INTO EMP_1 VALUES('101', 'News', 'John', 'G', '2000-11-02', 502);
INSERT INTO EMP_1 VALUES('102', 'Senior', 'David', 'H', '1989-07-12', 501);
-- ----------------------------
-- Question 3
-- Assuming that the data shown in the EMP_1 table have been entered, write the
--  SQL code that will list all attributes for a job code of 502.
-- ----------------------------
SELECT * FROM EMP_1 WHERE JOB_CODE = '502';
-- ----------------------------
-- Question 4
-- Write the SQL code that will save the changes made to the EMP_1 table.
-- ----------------------------
COMMIT;
-- ----------------------------
-- Question 5
-- Write the SQL code to change the job code to 501 for the person whose employee
--  number (EMP_NUM) is 107. After you have completed the task, examine the
--  results, and then reset the job code to its original value.
-- ----------------------------
UPDATE EMP_1 SET JOB_CODE = '501' WHERE EMP_NUM = '107';
SELECT * FROM EMP_1 WHERE EMP_NUM = '107';
ROLLBACK;
-- ----------------------------
-- Question 6
-- Write the SQL code to delete the row for the person named William Smithfield,
--  who was hired on June 22, 2004, and whose job code classification is 500.
--  (Hint: Use logical operators to include all of the information given in this
--  problem.)
-- ----------------------------
DELETE FROM EMP_1
WHERE EMP_FNAME = 'William' AND EMP_LNAME = "Smithfield"
AND EMP_HIREDATE = '2004-06-22'
AND JOB_CODE = '500';
-- ----------------------------
-- Question 7
-- Write the SQL code that will restore the data to its original status; that is,
--  the table should contain the data that existed before you made the changes in
--  Problems 5 and 6.
-- ----------------------------
ROLLBACK;
-- ----------------------------
-- Question 8
-- Write the SQL code to create a copy of EMP_1, naming the copy EMP_2. Then
--  write the SQL code that will add the attributes EMP_PCT and PROJ_NUM to its
--  structure. The EMP_PCT is the bonus percentage to be paid to each employee.
--  The new attribute characteristics are:
--
-- EMP_PCT NUMBER(4,2)
-- PROJ_NUM CHAR(3)
-- ----------------------------
CREATE TABLE EMP_2 AS SELECT * FROM EMP_1;
ALTER TABLE EMP_2
ADD EMP_PCT NUMERIC(4,2),
ADD PROJ_NUM CHAR(3);
-- ----------------------------
-- Question 9
-- Write the SQL code to change the EMP_PCT value to 3.85 for the person whose
--  employee number (EMP_NUM) is 103. Next, write the SQL command sequences to
--  change the EMP_PCT values as shown in Figure P7.9.
-- ----------------------------
UPDATE EMP_2 SET EMP_PCT = '3.85' WHERE EMP_NUM = '103';
--- OTHERS
UPDATE EMP_2 SET EMP_PCT = '5.00' WHERE EMP_NUM = '101' OR EMP_NUM = '105';
UPDATE EMP_2 SET EMP_PCT = '8.00' WHERE EMP_NUM = '102';
UPDATE EMP_2 SET EMP_PCT = '10.00' WHERE EMP_NUM = '104' OR EMP_NUM = '108';
UPDATE EMP_2 SET EMP_PCT = '6.20' WHERE EMP_NUM = '106';
UPDATE EMP_2 SET EMP_PCT = '5.15' WHERE EMP_NUM = '107';
UPDATE EMP_2 SET EMP_PCT = '2.00' WHERE EMP_NUM = '109';
