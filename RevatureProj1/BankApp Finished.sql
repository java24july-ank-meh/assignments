DROP USER Superuser CASCADE; /*Drop superuser if exists*/

CREATE USER Superuser
IDENTIFIED BY p4ssw0rd
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10m on users;

DROP TABLE bankusers;
CREATE TABLE bankusers(
    B_ID INTEGER PRIMARY KEY,
    B_NAME VARCHAR2(100),
    B_PASS VARCHAR2(100),
    B_BALANCE INTEGER
);

GRANT connect to Superuser;
GRANT resource to Superuser;
GRANT create session to Superuser;
GRANT CREATE TABLE TO Superuser;
GRANT CREATE view To Superuser;
GRANT ALTER ANY TABLE to Superuser;
GRANT CREATE USER to Superuser;
GRANT DROP USER to Superuser;


CREATE SEQUENCE SQ_PK_bankusers
START WITH 1
INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER TR_INSERT_bankusers
BEFORE INSERT ON bankusers
FOR EACH ROW
BEGIN
    SELECT SQ_PK_bankusers.NEXTVAL INTO :NEW.B_ID FROM DUAL;
END;
/

CREATE OR REPLACE PROCEDURE PR_CREATE_USER(x in bankusers.B_NAME%Type, y in bankusers.B_PASS%Type) is
Begin
        DECLARE 
            User_Exists EXCEPTION;
            varTmp NUMBER:=0;
        Begin
            SELECT nvl((SELECT 1 FROM bankusers WHERE B_NAME = x), 0) INTO varTmp FROM dual;
            IF (varTmp = 0) THEN
                INSERT INTO bankusers(B_NAME, B_PASS, B_BALANCE)
                VALUES (x, y, 0);
            ELSE RAISE User_Exists;
            End If;
        EXCEPTION
            WHEN User_Exists THEN DBMS_OUTPUT.PUT_LINE('User already exists.');
        End;    
End;
/

CREATE OR REPLACE PROCEDURE PR_DEPOSIT(x in bankusers.B_NAME%Type, y in bankusers.B_PASS%Type, z in Integer) is
Begin
    UPDATE bankusers set B_BALANCE = z where B_NAME = x AND B_PASS = y;
END;
/

CREATE OR REPLACE FUNCTION FN_DEPOSIT(x in bankusers.B_NAME%Type, y in bankusers.B_PASS%Type, N in bankusers.B_BALANCE%Type) RETURN NUMBER
Z IS NUMBER;
BEGIN

/* TEST STATEMENTS */
/*BEGIN PR_CREATE_USER('Dummy', 'dummy'); END;
BEGIN PR_DEPOSIT('Dummy', 'dummy', 15); END;
SELECT * FROM bankusers; */

/*NUCLEAR OPTION*/
DELETE FROM bankusers;
    