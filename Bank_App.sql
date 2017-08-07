/*Drop database if exists*/

DROP USER Wall_Street CASCADE;

/*Create the database*/
Create USER Wall_Street 
IDENTIFIED BY MadeOff
Default TABLESPACE users 
TEMPORARY TABLESPACE temp
QUOTA 10M on users;

/*Create Grants*/
GRANT connect to Wall_Street;
GRANT resource to Wall_Street;
GRANT create session to Wall_Street;
GRANT CREATE TABLE TO Wall_Street;
GRANT CREATE view To Wall_Street;

/*Connect as new user, otherwise will use current user*/
CONNECT Wall_Street/MadeOff;

/*Create Tables*/
Create Table Clients(
    C_ID NUMBER,
    C_FirstName VARCHAR2 (50),
    C_LastName VARCHAR2 (50),
    C_Password VARCHAR2 (50),
    Constraint PK_Clients PRIMARY KEY (C_ID)
);

Create Table Client_Accounts(
    Acc_ID NUMBER,
    Acc_Value NUMBER,
    C_FirstName VARCHAR2 (50) NOT NULL,
    C_ID NUMBER NOT NULL,
    Constraint PK_Acc PRIMARY KEY (Acc_ID),
    Constraint Over_Check Check (Acc_Value >= 0)
);

/*Drop Table Client_Accounts;*/

/*Create foreign keys*/
ALTER TABLE Client_Accounts ADD CONSTRAINT FK_Acc FOREIGN KEY (C_Firstname, C_ID)
    REFERENCES Clients(C_Firstname, C_ID) ON DELETE CASCADE;
 
   
/*Create Sequences*/
Create Sequence SQ_PK_Clients
START WITH 1
INCREMENT BY 1;
/
Create Sequence SQ_PK_Acc
START WITH 1
INCREMENT BY 1;
/

/*Create triggers*/
CREATE Or REPLACE TRIGGER TR_INSERT_clients
BEFORE INSERT ON Clients
FOR EACH ROW 
BEGIN
    SELECT SQ_PK_Clients.NEXTVAL INTO :NEW.C_ID FROM DUAL;
END;
/


CREATE Or REPLACE TRIGGER TR_INSERT_Acc
BEFORE INSERT ON Client_Accounts
FOR EACH ROW 
BEGIN
    SELECT SQ_PK_Acc.NEXTVAL INTO :NEW.Acc_ID FROM DUAL;
END;
/


/*Stored Procedures*/
CREATE OR REPLACE PROCEDURE Withdraw_Money
    (ACC_ID IN NUMBER, U_NAME In VARCHAR2, AMT IN NUMBER, Balance OUT Number) AS
BEGIN
    UPDATE Client_Accounts SET Acc_Value = Acc_Value - AMT
        WHERE Acc_ID = ACC_ID AND C_Firstname = U_NAME;
    COMMIT;
    SELECT Acc_Value INTO Balance FROM Client_Accounts Where Client_Accounts.Acc_ID = ACC_ID;
END;
/

CREATE OR REPLACE PROCEDURE Deposit_Money
    (ACC_ID IN NUMBER, U_NAME In VARCHAR2, AMT IN NUMBER, Balance OUT Number) AS
BEGIN
    UPDATE Client_Accounts SET Acc_Value = Acc_Value + AMT
        WHERE Acc_ID = ACC_ID AND C_Firstname = U_NAME;
    COMMIT;
    SELECT Acc_Value INTO Balance FROM Client_Accounts Where Client_Accounts.Acc_ID = ACC_ID;
END;
/


