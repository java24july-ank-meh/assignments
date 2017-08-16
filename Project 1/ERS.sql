/**** Creates the ers user ***/
DROP USER ers CASCADE;
DROP ROLE employee_role;
DROP ROLE manager_role;

CREATE USER ers
IDENTIFIED BY ers
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 20M ON users;

GRANT CONNECT TO ers with admin option;
GRANT RESOURCE TO ers with admin option;
GRANT CREATE SESSION TO ers with admin option;
GRANT CREATE TABLE TO ers with admin option;
GRANT CREATE VIEW TO ers with admin option;
GRANT CREATE USER TO ers with admin option;
GRANT CREATE ROLE TO ers with admin option;

CONNECT ers/ers;


/**** Creates the tables in the database ****/

CREATE TABLE ers.ERS_REIMBURSEMENT_TYPE (
    RT_ID NUMBER(*,0) PRIMARY KEY,
    RT_TYPE VARCHAR2(30)
);

INSERT INTO ers.ERS_REIMBURSEMENT_TYPE VALUES (1, 'Travel');
INSERT INTO ers.ERS_REIMBURSEMENT_TYPE VALUES (2, 'Materials');
INSERT INTO ers.ERS_REIMBURSEMENT_TYPE VALUES (3, 'Licensing/Certification');
INSERT INTO ers.ERS_REIMBURSEMENT_TYPE VALUES (4, 'Other');

CREATE TABLE ers.ERS_REIMBURSEMENT_STATUS (
    RS_ID NUMBER(*,0) PRIMARY KEY,
    RS_STATUS VARCHAR2(30)
);
INSERT INTO ers.ERS_REIMBURSEMENT_STATUS VALUES (1, 'PENDING');
INSERT INTO ers.ERS_REIMBURSEMENT_STATUS VALUES (2, 'APPROVED');
INSERT INTO ers.ERS_REIMBURSEMENT_STATUS VALUES (3, 'DENIED');

CREATE TABLE ers.ERS_USER_ROLES (
    UR_ID NUMBER(*,0) PRIMARY KEY,
    UR_ROLE VARCHAR2(40)
);
INSERT INTO ers.ERS_USER_ROLES VALUES (1, 'Manager');
INSERT INTO ers.ERS_USER_ROLES VALUES (2, 'Employee');

CREATE TABLE ers.ERS_USERS (
    U_ID NUMBER(*,0) PRIMARY KEY,
    U_USERNAME VARCHAR2(40) UNIQUE,
    U_PASSWORD VARCHAR2(40),
    U_FIRSTNAME VARCHAR2(30),
    U_LASTNAME VARCHAR2(30),
    U_EMAIL VARCHAR2(100) UNIQUE,
    UR_ID NUMBER(*,0)
);
ALTER TABLE ers.ERS_USERS
    ADD CONSTRAINT FK_UR_ID
    FOREIGN KEY (UR_ID) REFERENCES ers.ERS_USER_ROLES(UR_ID);

CREATE TABLE ers.ERS_REIMBURSEMENTS (
    R_ID NUMBER(*,0) PRIMARY KEY,
    R_AMOUNT NUMBER(22,2),
    R_DESCRIPTION VARCHAR2(100),
    R_RECEIPT BLOB,
    R_SUBMITTED TIMESTAMP,
    R_RESOLVED TIMESTAMP,
    U_ID_AUTHOR NUMBER(*,0),
    U_ID_RESOLVER NUMBER(*,0),
    RT_TYPE NUMBER(*,0),
    RT_STATUS NUMBER (*,0)
);
ALTER TABLE ers.ERS_REIMBURSEMENTS
    ADD CONSTRAINT FK_U_ID_AUTHOR 
    FOREIGN KEY (U_ID_AUTHOR) REFERENCES ers.ERS_USERS(U_ID);
ALTER TABLE ers.ERS_REIMBURSEMENTS
    ADD CONSTRAINT FK_U_ID_RESOLVER
    FOREIGN KEY (U_ID_RESOLVER) REFERENCES ers.ERS_USERS(U_ID);
ALTER TABLE ers.ERS_REIMBURSEMENTS
    ADD CONSTRAINT FK_RT_TYPE
    FOREIGN KEY (RT_TYPE) REFERENCES ers.ERS_REIMBURSEMENT_TYPE(RT_ID);
ALTER TABLE ers.ERS_REIMBURSEMENTS
    ADD CONSTRAINT FK_RT_STATUS
    FOREIGN KEY (RT_STATUS) REFERENCES ers.ERS_REIMBURSEMENT_STATUS(RS_ID);
 
 
 
/*** Create sequences ***/
CREATE SEQUENCE SQ_ERS_USERS_U_ID
MINVALUE 2000000
MAXVALUE 2999999
START WITH 2000000
INCREMENT BY 1;
/

CREATE SEQUENCE SQ_ERS_REIMBURSEMENTS_R_ID
MINVALUE 1000000
MAXVALUE 1999999
START WITH 1000000
INCREMENT BY 1;
/


/*** Create triggers ***/
CREATE OR REPLACE TRIGGER TR_INSERT_ERS_USERS
BEFORE INSERT ON ers.ERS_USERS
FOR EACH ROW
BEGIN
    SELECT SQ_ERS_USERS_U_ID.NEXTVAL INTO :NEW.U_ID FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER TR_INSERT_ERS_REIMBURSEMENTS
BEFORE INSERT ON ers.ERS_REIMBURSEMENTS
FOR EACH ROW
BEGIN
    SELECT SQ_ERS_REIMBURSEMENTS_R_ID.NEXTVAL INTO :NEW.R_ID FROM DUAL;
END;
/

/** Creates user privilege roles **/
CREATE ROLE employee_role;
GRANT CONNECT TO employee_role; 
GRANT RESOURCE TO employee_role; 
GRANT CREATE SESSION TO employee_role; 
GRANT CREATE VIEW TO employee_role; 
GRANT SELECT ON ers.ERS_REIMBURSEMENTS TO employee_role;
GRANT INSERT ON ers.ERS_REIMBURSEMENTS TO employee_role;
GRANT UPDATE ON ers.ERS_REIMBURSEMENTS TO employee_role;
GRANT SELECT ON ers.ERS_USERS TO employee_role;

CREATE ROLE manager_role;
GRANT CONNECT TO manager_role; 
GRANT RESOURCE TO manager_role; 
GRANT CREATE SESSION TO manager_role; 
GRANT CREATE VIEW TO manager_role; 
GRANT SELECT ON ers.ERS_REIMBURSEMENTS TO manager_role; 
GRANT SELECT ON ers.ERS_USERS TO manager_role; 

/*** Stored procedures ***/
CREATE OR REPLACE PROCEDURE user_make_employee
    (in_username IN VARCHAR2) AS
sqlstr VARCHAR2(3000);
BEGIN
    sqlstr := 'GRANT employee_role TO '||in_username;
    EXECUTE IMMEDIATE sqlstr;
END;
/

CREATE OR REPLACE PROCEDURE user_make_manager
    (in_username IN VARCHAR2) AS
sqlstr VARCHAR2(3000);
BEGIN
    sqlstr := 'GRANT manager_role TO '||in_username;
    EXECUTE IMMEDIATE sqlstr;
END;
/

CREATE OR REPLACE PROCEDURE create_new_user
    (in_username IN VARCHAR2, in_password IN VARCHAR2, in_fname IN VARCHAR2, in_lname IN VARCHAR2, in_email IN VARCHAR2, in_role IN NUMBER) AS
sqlstr VARCHAR2(3000);
BEGIN
    /* Creates new user */
    sqlstr := 'CREATE USER '||in_username||' IDENTIFIED BY '||in_password||' DEFAULT TABLESPACE users QUOTA 10M ON users';
    EXECUTE IMMEDIATE sqlstr;
    
    /* Grants roles */
    IF (in_role = 2) THEN
        user_make_employee(in_username);
    ELSIF (in_role = 1) THEN
        user_make_manager(in_username);
    END IF;
    
    /* Updates ERS_USERS */
    INSERT INTO ers.ERS_USERS (U_USERNAME, U_PASSWORD, U_FIRSTNAME, U_LASTNAME, U_EMAIL, UR_ID)
        VALUES (in_username, in_password, in_fname, in_lname, in_email, in_role);
END;
/

/** Add users ***/
connect system/p4ssw0rd;
drop user ersnulluser;
drop user mscott;
drop user dschrute;
drop user vcommero;
drop user ehontz;
drop user jschmoe;
connect ers/ers;
CALL create_new_user('ersnulluser', 'nullpass', '', '', '', 2);
CALL create_new_user('mscott', 'pass123', 'Michael', 'Scott', 'mscott@dunder-mifflin.com', 1);
CALL create_new_user('dschrute', 'pass123', 'Dwight', 'Schrute', 'dschrute@dunder-mifflin.com', 2);
CALL create_new_user('vcommero', 'p4ssw0rd', 'Vince', 'Commero', 'vacommero@yahoo.com', 1);
CALL create_new_user('ehontz', 'ehontz', 'Eric', 'Hontz', 'ehontz@sharklazers.com', 1);
CALL create_new_user('jschmoe', '121212', 'Joe', 'Schmoe', 'jschmoe@gmail.com', 2);



/*
SELECT * FROM ers.ERS_USERS;

SELECT * FROM ers.ERS_REIMBURSEMENTS;
*/
/*
drop user ers cascade;
drop role employee_role;
drop role manager_role;
*/
