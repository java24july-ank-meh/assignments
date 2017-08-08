--CREATE USER bank
--IDENTIFIED BY bank
--DEFAULT TABLESPACE users
--TEMPORARY TABLESPACE temp
--QUOTA 10M ON users;
--
--GRANT connect to bank;
--GRANT resource to bank;
--GRANT create session to bank;
--GRANT CREATE TABLE TO bank;
--GRANT CREATE view To bank;
--
--/*Connect as new user, otherwise will use current user*/
--CONNECT bank/bank;


CREATE TABLE BANK_USER(
    U_ID INTEGER PRIMARY KEY,
    U_NAME VARCHAR2(100) UNIQUE,
    U_PASSWORD VARCHAR2(100),
    IS_ADMIN INTEGER
    );


CREATE TABLE ACCOUNTS(
    AC_ID INTEGER PRIMARY KEY,
    AC_NAME VARCHAR2(100),
    AC_U_ID INTEGER,
    AC_BALANCE NUMERIC DEFAULT  0,
    FOREIGN KEY (AC_U_ID) REFERENCES BANK_USER(U_ID)
    );

CREATE SEQUENCE SQ_U_ID
START WITH 1
INCREMENT BY 1;
/
CREATE SEQUENCE SQ_AC_ID
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER TR_INSERT_USER
BEFORE INSERT ON BANK_USER
FOR EACH ROW
BEGIN
    SELECT SQ_U_ID.NEXTVAL INTO :NEW.U_ID FROM DUAL;
END;
/ 
CREATE OR REPLACE TRIGGER TR_INSERT_ACCOUNT
BEFORE INSERT ON ACCOUNTS
FOR EACH ROW
BEGIN
    SELECT SQ_AC_ID.NEXTVAL INTO :NEW.AC_ID FROM DUAL;
END;
/
CREATE OR REPLACE PROCEDURE CREATE_USER(USER_NAME IN VARCHAR2, USER_PASSWORD IN VARCHAR2, ISADMIN IN INT) AS
BEGIN
    INSERT INTO BANK_USER(U_NAME, U_PASSWORD, IS_ADMIN) VALUES (USER_NAME, USER_PASSWORD, ISADMIN);
END;
/
CREATE OR REPLACE PROCEDURE CREATE_ACCOUNT(AC_NAM IN VARCHAR2, USER_ID IN INT) AS
BEGIN
    INSERT INTO ACCOUNTS(AC_NAME, AC_U_ID) VALUES(AC_NAM, USER_ID);
END;
/

CREATE OR REPLACE PROCEDURE DELETE_ACCOUNT(ACCNT_ID IN INTEGER) AS
BEGIN
    DELETE FROM ACCOUNTS WHERE ACCOUNTS.AC_U_ID = ACCNT_ID;
END;
/
CREATE OR REPLACE PROCEDURE DELETE_USER(USER_ID IN INTEGER) AS
BEGIN
    DELETE FROM ACCOUNTS WHERE ACCOUNTS.AC_U_ID = USER_ID;
    DELETE FROM BANK_USER WHERE BANK_USER.U_ID = USER_ID ;
END;
/

CREATE OR REPLACE PROCEDURE DEPOSIT(ACCNT_ID IN INTEGER, DEPOSIT_AMT IN NUMERIC) AS
BEGIN
    UPDATE ACCOUNTS 
    SET AC_BALANCE = AC_BALANCE + DEPOSIT_AMT
    WHERE AC_ID = ACCNT_ID;
END;
/

CREATE OR REPLACE PROCEDURE WITHDRAWAL(ACCNT_ID IN INTEGER, WITHDRAW_AMT IN NUMERIC) AS
BEGIN
    UPDATE ACCOUNTS 
    SET AC_BALANCE = AC_BALANCE - WITHDRAW_AMT
    WHERE AC_ID = ACCNT_ID;
END;

/

CREATE OR REPLACE PROCEDURE GET_USER (USERNAME IN VARCHAR2, S OUT SYS_REFCURSOR) AS
BEGIN
    OPEN S FOR
        SELECT * FROM BANK_USER WHERE U_NAME = USERNAME;
END;
/
BEGIN CREATE_USER('CALEB', 'HEY', 1); END;
/
CALL CREATE_USER('JACKSON', 'PASS', 0);
/
CALL CREATE_ACCOUNT('COOLGUY', 2);
/
CALL CREATE_ACCOUNT('CLASSIC_MATTHEW', 3);
/
CALL DEPOSIT(1, 200);
/
CALL WITHDRAWAL(1,100);
/