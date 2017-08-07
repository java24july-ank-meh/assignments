/*Drop database if it exists*/
DROP USER superuser CASCADE;

/*Create the database user*/
CREATE USER superuser
IDENTIFIED BY superman
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect to superuser;
GRANT resource to superuser;
GRANT create session to superuser;
GRANT CREATE TABLE TO superuser;
GRANT CREATE view To superuser;

/*Connect as new user, otherwise will use current user*/
CONNECT superuser/superman;

/*Create tables*/
CREATE TABLE BANK_USER(
    user_id INTEGER PRIMARY KEY,
    user_name VARCHAR2(255) NOT NULL UNIQUE,
    user_password VARCHAR2(255) NOT NULL
);

CREATE TABLE BANK_ACCOUNT(
    account_id INTEGER PRIMARY KEY,
    account_name VARCHAR2(255) NOT NULL,
    account_balance NUMBER NOT NULL,
    user_id INTEGER NOT NULL
);

/*Constraints for foreign keys*/
ALTER TABLE BANK_ACCOUNT ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
    REFERENCES BANK_USER (user_id) ON DELETE CASCADE;

/*Create sequences*/
CREATE SEQUENCE SQ_PK_USER
START WITH 1
INCREMENT BY 1;
/

CREATE SEQUENCE SQ_PK_ACCOUNT
START WITH 1
INCREMENT BY 1;
/

/*Create triggers*/
CREATE OR REPLACE TRIGGER TR_INSERT_USER
BEFORE INSERT ON BANK_USER
FOR EACH ROW
BEGIN
    SELECT SQ_PK_USER.NEXTVAL INTO :NEW.user_id FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER TR_INSERT_ACCOUNT
BEFORE INSERT ON BANK_ACCOUNT
FOR EACH ROW
BEGIN
    SELECT SQ_PK_ACCOUNT.NEXTVAL INTO :NEW.account_id FROM DUAL;
END;
/

create or replace PROCEDURE WITHDRAW 
    (acc_name IN VARCHAR2, u_id IN INTEGER, amount IN NUMBER) AS
BEGIN
    UPDATE BANK_ACCOUNT SET account_balance = account_balance - amount
        WHERE account_name = acc_name AND user_id = u_id;
    COMMIT;
END;
/

create or replace PROCEDURE DEPOSIT 
    (acc_name IN VARCHAR2, u_id IN INTEGER, amount IN NUMBER) AS
BEGIN
    UPDATE BANK_ACCOUNT SET account_balance = account_balance + amount
        WHERE account_name = acc_name AND user_id = u_id;
    COMMIT;
END;
/