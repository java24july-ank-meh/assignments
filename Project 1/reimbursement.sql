Drop User dbsystem Cascade;

Create table UserRoles (
    Ur_ID Number,
    User_Role Varchar2(10),
	Constraint PK_BALogin Primary key (Ur_ID)
);
Create table Web_Users (
    User_ID Number,
    User_Name Varchar2(21),
	Pass_Word Varchar2(10) default 'password',
	First_Name Varchar2(10),
	M_Intial Varchar2(1) default '_',
	Last_Name Varchar2(10),
	Email Varchar2(25),
	Ur_ID Number,	/*FK to User Roles table Ur_ID*/
	Constraint PK_Users Primary key (User_ID),
	Constraint FK_Users_Role Foreign key (Ur_ID) references UserRoles(Ur_ID)
);

Create table ReStatus (
    Rs_ID Number Primary key,
    Rs_Status Varchar2(8)
);

Create table ReType (
    Rt_ID Number Primary key,
    Rt_Type Varchar2(8)
);

Create table Reimbursements (
    R_ID Number Primary key,
	R_Amount Number(22,2),
    R_Description Varchar2(30) default null,
	R_Receipt Blob default null,
	R_Submitted Timestamp,
	R_Resolved Timestamp null,
	U_ID_Author Number,	/*FK to Web_Users table User_ID*/
	U_ID_Resolver Number,	/*FK to Web_Users table User_ID*/
	RT_Type Number default 0, /*FK to ReType table Rt_ID */
	RT_Status Number default 1,	/*FK to ReStatus table Rs_ID */
	Constraint FK_Reimbursement_Author Foreign key (U_ID_Author) references Web_Users(User_ID),
	Constraint FK_Reimbursement_Resolver Foreign key (U_ID_Resolver) references Web_Users(User_ID),
	Constraint FK_Reimbursement_Type Foreign key (RT_Type) references ReType(Rt_ID),
	Constraint FK_Reimbursement_Status Foreign key (RT_Status) references ReStatus(Rs_ID)
);

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
Before Insert on Reimbursements
For each row
Begin
    Select SQ_PK_Reimbursements.NEXTVAL into :NEW.R_ID from DUAL;
End;
/


/*functions*/

create or replace
function createUserNames(fname Varchar2, mi Varchar2, lname Varchar2) return Varchar2
as un_var Varchar2(25);
begin
    un_var := concat(concat(fname,mi),lname);
    return un_var;
end;
/

Create or Replace Trigger TR_BInsert_Users_Usernames
before Insert on Web_Users
For each row
declare
    fn Varchar2(10);
    mi Varchar2(1);
    ln Varchar2(10);
Begin
    fn := :new.first_Name;
    mi := :new.m_Intial;
    ln := :new.last_name;
    :new.User_Name := createUserNames(fn,mi,ln);
End;
/


/*tested function*/
declare
    var2 varchar2(25);
begin
    var2 := createUserNames('Kelvin','I','Lane');
    dbms_output.put_line(var2);
end;

/*Inserts*/

Insert into UserRoles values(0, 'Admin');
Insert into UserRoles values(1, 'Manager');
Insert into UserRoles values(2, 'Employee');

Insert into Web_Users (First_Name, M_Intial, Last_Name, Email, Ur_ID) values ( 'Kay', 'L', 'Moore', 'klm@mail.com', 1);
/*100000*/
Insert into Web_Users (First_Name, M_Intial, Last_Name, Email, Ur_ID) values ( 'Amelia', 'S', 'Chavez', 'asc@mail.com', 2);
/*100001*/
Insert into Web_Users (First_Name, M_Intial, Last_Name, Email, Ur_ID) values ( 'Ernesto', 'C', 'Bass', 'ecb@mail.com', 2);
/*100002*/
Insert into Web_Users (First_Name, M_Intial, Last_Name, Email, Ur_ID) values ( 'Jessica', 'X', 'Pearson', 'jep@mail.com', 1);
/*100003*/
Insert into Web_Users (First_Name, M_Intial, Last_Name, Email, Ur_ID) values ( 'Tyrone', 'E', 'Hawkins', 'teh@mail.com', 2);
/*100004*/
Insert into Web_Users (First_Name, M_Intial, Last_Name, Email, Ur_ID) values ( 'Anne', 'Y', 'Mason', 'aym@mail.com', 2);
/*100005*/

Insert into ReStatus values(1, 'pending');
Insert into ReStatus values(0, 'denied');
Insert into ReStatus values(2, 'approved');

/*https://www.zanebenefits.com/blog/3-types-of-employee-reimbursement*/
Insert into ReType values(1, 'Business');
Insert into ReType values(2, 'Travel');
Insert into ReType values(3, 'Medical');

select * from Web_Users;
select * from Reimbursements;

Insert into Reimbursements (R_Amount, R_Description, R_Submitted, U_ID_Author, RT_Type, RT_Status) Values(90.50, 'Lunch Outing', current_timestamp, 100001, 1, 1);
Insert into Reimbursements (R_Amount, R_Description, R_Submitted, U_ID_Author, RT_Type, RT_Status) Values(60.50, 'More supplies', current_timestamp, 100004, 1, 1);
Insert into Reimbursements (R_Amount, R_Description, R_Submitted, U_ID_Author, RT_Type, RT_Status) Values(240.50, 'Florida Visit', current_timestamp, 100002, 2, 1);
Insert into Reimbursements (R_Amount, R_Description, R_Submitted, U_ID_Author, RT_Type, RT_Status) Values(120.50, 'Testing', current_timestamp, 100005, 3, 1);
