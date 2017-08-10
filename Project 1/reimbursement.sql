
/*
aws.amazon.com
master 
username: dbsystem
password: oracleSE217

Drop User admin Cascade;

Create user admin
Identified by sysADMIN
Default tablespace users
Temporary tablespace temp
Quota 10m on users;

Grant connect to admin;
Grant resource to admin;
Grant create session to admin;
Grant create table to admin;
Grant create view to admin;

Connect admin/sysADMIN;
*/
Create table Web_Users (
    User_ID Number,
    User_Name Varchar2(10),
	Pass_Word Varchar2(10),
	First_Name Varchar2(10),
	Last_Name Varchar2(10),
	Email Varchar2(25),
	Ur_ID Number,	/*FK to User Roles table Ur_ID*/
	Constraint PK_Users Primary key (User_ID),
	Constraint FK_Users_Role Foreign key (Ur_ID) references UserRoles(Ur_ID)
);

Create table UserRoles (
    Ur_ID Number,
    User_Role Varchar2(10),
	Constraint PK_BALogin Primary key (Ur_ID)
);
	
Create table Reimburstments (
    R_ID Number Primary key,
	R_Amount Number(22,2),
    R_Description Varchar2(30),
	R_Receipt Blob,
	R_Submitted Timestamp,
	R_Resolved Timestamp,
	U_ID_Author Number,	/*FK to Web_Users table User_ID*/
	U_ID_Resolver Number,	/*FK to Web_Users table User_ID*/
	RT_Type Number, /*FK to ReType table Rt_ID */
	RT_Status Number,	/*FK to ReStatus table Rs_ID */
	Constraint FK_Reimburstment_Author Foreign key (U_ID_Author) references Web_Users(User_ID),
	Constraint FK_Reimburstment_Resolver Foreign key (U_ID_Resolver) references Web_Users(User_ID),
	Constraint FK_Reimburstment_Type Foreign key (RT_Type) references ReType(Rt_ID),
	Constraint FK_Reimburstment_Status Foreign key (RT_Status) references ReStatus(Rs_ID)
);
	
Create table ReStatus (
    Rs_ID Number Primary key,
    Rs_Status Varchar2(8)
);

Create table ReType (
    Rt_ID Number Primary key,
    Rt_Type Varchar2(8)
);



/*Inserts*/

Insert into UserRoles values(0, 'Admin');
Insert into UserRoles values(1, 'Manager');
Insert into UserRoles values(2, 'Employee');


Insert into Web_Users (User_Name, Pass_Word, First_Name, Last_Name, Email, Ur_ID) values ( 'kayM', '', 'Kay', 'Moore', '', 1);
Insert into Web_Users (User_Name, Pass_Word, First_Name, Last_Name, Email, Ur_ID) values ( 'ameliaC', '', 'Amelia', 'Chavez', '', 2);
Insert into Web_Users (User_Name, Pass_Word, First_Name, Last_Name, Email, Ur_ID) values ( 'ernestoB', '', 'Ernesto	', 'Bass', '', 2);
Insert into Web_Users (User_Name, Pass_Word, First_Name, Last_Name, Email, Ur_ID) values ( 'jessicaP', '', 'Jessica', 'Pearson', '', 1);
Insert into Web_Users (User_Name, Pass_Word, First_Name, Last_Name, Email, Ur_ID) values ( 'tyroneH', '', 'Tyrone', 'Hawkins', '', 2);
Insert into Web_Users (User_Name, Pass_Word, First_Name, Last_Name, Email, Ur_ID) values ( 'AnneM', '', 'Anne', 'Mason', '', 2);


Insert into ReStatus values(1, 'pending');
Insert into ReStatus values(0, 'denied');
Insert into ReStatus values(2, 'approved');


/*https://www.zanebenefits.com/blog/3-types-of-employee-reimbursement*/
Insert into ReType values(1, 'Business');
Insert into ReType values(2, 'Travel');
Insert into ReType values(3, 'Medical');



/*sequences*/

create sequence SQ_PK_Users
start with 100000/*100,000*/
increment by 1;
/

create sequence SQ_PK_Reimbursements
start with 100000
increment by 1;
/


/*triggers*/

Create or Replace Trigger TR_BInsert_Users_Id
Before Insert on Web_Users
For each row
Begin
    Select SQ_PK_Users.NEXTVAL into :NEW.User_ID from DUAL;
End;
/

Create or Replace Trigger TR_BInsert_Reimbursements_Id
Before Insert on Reimburstments
For each row
Begin
    Select SQ_PK_Reimbursements.NEXTVAL into :NEW.R_ID from DUAL;
End;
/


