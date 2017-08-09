
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
Create table Users (
    User_ID Number,
    User_Name Varchar2(10),
	Pass_Word Varchar2(10),
	First_Name Varchar2(10),
	Last_Name Varchar2(10),
	Email Varchar2(25),
	Ur_ID Number,	/*FK to User Roles table Ur_ID*/
	Constraint PK_Users Primary key (User_ID),
	Constraint FK_Users_Role Foreign (Ur_ID) references UserRoles(Ur_ID)
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
	U_ID_Author Number,	/*FK to User table Ur_ID*/
	U_ID_Resolver Number,	/*FK to User table Ur_ID*/
	RT_Type Number, /*FK to ReStatustable Rt_Status */
	RT_Status Number	/*FK to ReStatustable Rt_Type */
	Constraint FK_Users_Role Foreign (RT_Type) references UserRoles(Rt_ID),
	Constraint FK_Users_Role Foreign (RT_Status) references UserRoles(Rs_ID)
);
	
Create table ReStatus (
    Rs_ID Number Primary key,
    Rs_Status Varchar2(8)
);
	
Create table ReType (
    Rt_ID Number Primary key,
    Rt_Type Varchar2(8)
);





