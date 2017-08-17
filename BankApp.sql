/*Drop database if it exists*/
ALTER TABLE user_account DROP CONSTRAINT fk_user_id ;
DROP TABLE user_table;
DROP TABLE user_account;
DROP SEQUENCE sq_pk_userid;
DROP SEQUENCE sq_fk_userid;
DROP SEQUENCE sq_pk_acctid;
DROP TRIGGER tr_insert_user;
DROP TRIGGER tr_insert_account;
DROP PROCEDURE sp_add_bal;

/*Create the database user*/
CREATE USER adminone
IDENTIFIED BY adminpass
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect to adminone;
GRANT resource to adminone;
GRANT create session to adminone;
GRANT CREATE TABLE TO adminone;
GRANT CREATE view To adminone;

/*Connect as new user, otherwise will use current user*/
CONNECT adminone/adminpass;

/*Create tables*/
CREATE TABLE user_table (
    user_id INTEGER PRIMARY KEY,
    user_fname VARCHAR2(100),
    user_lname VARCHAR2(100),
    user_email VARCHAR2(100),
    user_address VARCHAR2(100),
    user_city VARCHAR2(100),
    user_state VARCHAR2(100),
    user_phone NUMBER (10)
);

CREATE TABLE user_account (
    acct_id INTEGER PRIMARY KEY,
    balance NUMBER(10,2),
    user_id INTEGER
);

/*Create foreign keys*/
ALTER TABLE user_account ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
    REFERENCES user_table (user_id) ON DELETE CASCADE;

/*Create sequences*/
CREATE SEQUENCE sq_pk_userid
START WITH 1
INCREMENT BY 1;
/

CREATE SEQUENCE sq_fk_userid
START WITH 1
INCREMENT BY 1;
/

CREATE SEQUENCE sq_pk_acctid
START WITH 1
INCREMENT BY 1;
/

/*Create triggers*/
CREATE OR REPLACE TRIGGER tr_insert_user
BEFORE INSERT ON user_table
FOR EACH ROW
BEGIN
    SELECT sq_pk_userid.NEXTVAL INTO :NEW.user_id FROM DUAL;
    
END;
/

CREATE OR REPLACE TRIGGER tr_insert_userfk
BEFORE INSERT ON user_account
FOR EACH ROW
BEGIN
    SELECT sq_fk_userid.NEXTVAL INTO :NEW.user_id FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER tr_insert_account
BEFORE INSERT ON user_account
FOR EACH ROW
BEGIN
    SELECT sq_pk_acctid.NEXTVAL INTO :NEW.acct_id FROM DUAL;
END;
/

/*Stored Procedure*/
CREATE OR REPLACE PROCEDURE sp_add_bal 
    (userid IN NUMBER, acctid IN NUMBER, amount IN NUMBER) AS
BEGIN
    UPDATE user_account SET balance = balance + amount
        WHERE acct_id = acctid;
    COMMIT;
END;
/
COMMIT;

CREATE OR REPLACE PROCEDURE sp_sub_bal 
    (userid IN NUMBER, acctid IN NUMBER, amount IN NUMBER) AS
BEGIN
    UPDATE user_account SET balance = balance - amount
        WHERE acct_id = acctid;
    COMMIT;
END;
/
COMMIT;
SAVEPOINT point_zero;


INSERT INTO user_table(user_fname, user_lname, user_email, user_address, user_city, user_state, user_phone)
VALUES('Dummy', 'McDummy', 'dummy@dummy.com', '2341 Dummy St', 'Dummy City', 'Dummy State', 0000000000);

INSERT INTO user_account(balance) VALUES (0.00);

SELECT * FROM user_account;
SELECT * FROM user_table;

