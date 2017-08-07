/*
Banking App 8/4 - 8/7 project
baAdmin
baPWD123
superuser
suPWD!$$
*/

/*Drop database if it exists*/
Drop User baAdmin Cascade;

Create user baAdmin
Identified by baPWD123
Default tablespace users
Temporary tablespace temp
Quota 10m on users;

Grant connect to baAdmin;
Grant resource to baAdmin;
Grant create session to baAdmin;
Grant create table to baAdmin;
Grant create view to baAdmin;

/*Connect as new user, otherwise will use current user*/
Connect baAdmin/baPWD123;

/*Drop tables*/
drop table BADeletedUser;
drop table BATransaction;
drop table BAACcount;
drop table BAUSerInfo;
drop table BALogin;

/*Create tables*/
Create table BALogin (
    User_ID Number,
    User_Name Varchar2(10) not null,
	Pass_Word Varchar2(15) not null,
	F_Name Varchar2(20) not null,
	L_Name Varchar2(20) not null,
	Total_Balance Number Default 0,
	Constraint PK_BALogin Primary key (User_ID)
);

Insert into BALogin Values(0, 'superuser', 'suPWD!$$', 'super', 'user', 0);

Create table BAUserInfo (
    MyUser_ID Number Primary key,
	M_Name Varchar2(20) Default '',
	Address Varchar2(50),
	City Varchar2(20),
	State1 Varchar2(20),
	PostalCode Varchar2(20),
	Constraint FK_UserInfo_UserID
		Foreign key (MyUser_ID) references BALogin (User_ID) on Delete Cascade
);

Create table BAAccount (
	Account_ID Number not null,
    MyUser_ID Number not null,
	Account_Name Varchar2(20) Default 'My Account',
	Balance Number(9,2) Default 0,/*100,000,000.00*/
	Constraint PK_BAAcount Primary key (Account_ID),
	Constraint FK_Account_UserID
		Foreign key (MyUser_ID) references BALogin (User_ID) on Delete Cascade
);

Create table BATransaction (
	Transaction_ID Number,
    MyAccount_ID Number not null,
	MyUser_ID Number not null,
	Transaction_Type Varchar2(20) not null,
	Transaction_Amount Number(9,2) Default 0,/*100,000,000.00*/
	Transaction_Time TimeStamp,
	Constraint PK_BATransaction Primary key (Transaction_ID),
	Constraint FK_Transaction_AccountID
		Foreign key (MyAccount_ID) references BAAccount (Account_ID) on Delete Cascade,
	Constraint FK_Transaction_UserID
		Foreign key (MyUser_ID) references BALogin (User_ID) on Delete Cascade
);

Create table BADeletedUser (
    MyOld_ID Number,
	Delete_Time TimeStamp,
    User_Name Varchar2(10) not null,
	Pass_Word Varchar2(15) not null,
	F_Name Varchar2(20) not null,
	M_Name Varchar2(20) Default '',
	L_Name Varchar2(20) not null,
	Address Varchar2(50),
	City Varchar2(20),
	State1 Varchar2(20),
	Postal_Code Varchar2(20),
    Total_Balance Number(11,2),
	Constraint PK_BAUser Primary key (MyOld_id)
);

/*Create Squence*/
Create Sequence SQ_PK_BALogin
Start with 100000	/*100,000 start of ids, better than starting at 1,
but if only it could be 011010 like number or 001001, just numbers with 0's in front*/
Increment by 1;
/

Create Sequence SQ_PK_BAAccount
Start with 100000
Increment by 1;
/

Create Sequence SQ_PK_BATransaction
Start with 100000
Increment by 1;
/

/*Create Trigger*/
/*Before insert*/
Create or Replace Trigger TR_BInsert_Login_Id
Before Insert on BALogin
For each row
Begin
    Select SQ_PK_BALogin.NEXTVAL into :NEW.User_ID from DUAL;
End;
/
Create or Replace Trigger TR_AInsert_Login_Account
After Insert on BALogin
For each row
Begin
    insert into BAAccount (MyUser_ID, Account_Name, Balance)
    Values(getRecentUserId, 'New Account', 0);
End;
/
Create or Replace Trigger TR_BInsert_Account_Id
Before Insert on BAAccount
For each row
Begin
    Select SQ_PK_BAAccount.NEXTVAL into :NEW.Account_ID from DUAL;
End;
/
Create or Replace Trigger TR_BInsert_Transaction_Id
Before Insert on BATransaction
For each row
Begin
    Select SQ_PK_BATransaction.NEXTVAL into :NEW.Transaction_ID from DUAL;
End;
/
Create or Replace Trigger TR_BInsert_Transaction_Time
Before Insert on BATransaction
For each row
Begin
    Select LOCALTIMESTAMP into :NEW.Transaction_Time from DUAL;
End;
/
Create or Replace Trigger TR_BInsert_DeletedUser_Time
Before Insert on BADeletedUser
For each row
Begin
    Select LOCALTIMESTAMP into :NEW.Delete_Time from DUAL;
End;
/
/* Extra just for future use*/
/*Before Delete*/
Create or Replace Trigger TR_Delete_User
After Delete on BALogin
For each row
Begin
	Insert into BADeletedUser (MyOld_ID, Delete_Time, User_name, Pass_Word, F_Name, L_Name, Total_Balance)
        Values(:Old.User_ID, LocalTimeStamp, :Old.User_Name, :Old.Pass_Word, :Old.F_Name,:Old.L_Name, :Old.Total_Balance);
End;
/
Create or Replace Trigger TR_Delete_Userinfo
AFter Delete on BAUserInfo
For each row
Begin
	Update BADeletedUser Set M_Name=:Old.M_Name, Address=:Old.Address, City=:Old.City, State1=:Old.State1, Postal_Code=:Old.PostalCode
    where MyOld_ID = :Old.MyUser_ID;
End;
/

/*Stored Procedures*/
create or replace
procedure makeDeletedUser as
begin
    insert into BATransaction(MyAccount_ID, MyUser_ID, Transaction_Type, Transaction_Time)
    Values(0, 0, 'delete user', localtimestamp);
end;
/
Create or replace
procedure SP_BAeDUser_RestoreL( u in Number,
    un in Varchar2,
	pw in Varchar2,
	fn in Varchar2,
	ln in Varchar2,
	b in Number) as
begin
    insert into BALogin values(u, un, pw, fn, ln, b);
    SP_BADeUser_RestoreGA(u, b);
end;
/
Create or replace
procedure SP_BADeUser_RestoreU (u in Number,
	mn in Varchar2,
	ad in Varchar2,
	ci in Varchar2,
	st in Varchar2,
	pc Varchar2) as
begin
    insert into BAUserInfo values(u, mn, ad, ci, st, pc);
end;
/
Create or replace
procedure SP_BADeUser_RestoreGA(u in number, b in number) as
begin
    insert into BAAccount (MyUser_ID, Account_Name, Balance) values(u, 'Restored Account', b);
end;
/
/*Functions*/
create or replace
function calc_TotalBalance(ud in number) return number
as tb number;
begin
    select Sum(Balance) into tb from BAAccount where MyUser_ID = ud;
    return tb;
end;
/
create or replace
function getRecentUserId return number
as r1 number;
begin
    select Max(User_ID) into r1 from BALogin;
    return r1;
end;
/
/*Populate tables*/
Insert into BALogin (User_Name, Pass_Word, F_Name, L_Name) Values('idollord', 'dragon8@!!', 'Micheal', 'Foster');
	/*User id should be 100000*/
Insert into BALogin (User_Name, Pass_Word, F_Name, L_Name) Values('eclairebun', '0gracious9', 'Claire', 'Cox');
	/*User id should be 100001*/

Insert into BAUserInfo (MyUser_ID, M_Name, Address, City, State1, PostalCode) 
	values(100000,'Icon','909 Circle Dr','San Diego','California','99999');

Insert into BAUserInfo (MyUser_Id, M_Name, Address, City, State1, PostalCode) 
	values(100001,'Dollar', '1029 Avenue K','San Antonio','Texas','77777');

Insert into BAAccount (MyUser_ID, Account_Name, Balance) 
	values(100000, 'Spending Money', 1501.90);
	/*Account id should be 100000*/
Insert into BAAccount (MyUser_ID, Account_Name, Balance) 
	values(100001,'Shopping Money', 10000.05);
	/*Account id should be 100001*/
Insert into BAAccount (MyUser_ID, Account_Name, Balance) 
	values(100001,'Child`s College Fund', 25063.10);
	/*Account id should be 100002*/

Insert into BATransaction (MyAccount_ID, MyUser_ID, Transaction_Type, Transaction_Amount) 
	values(100001, 100001,'Withdraw', 3350);
	/*Transaction id should be 100000,Claire took out money to add into childs college fund and 
some walking around money*/
Insert into BATransaction (MyAccount_ID, MyUser_ID, Transaction_Type, Transaction_Amount) 
	values(100000, 100000,'Withdraw',751.98);
	/*Transaction id should be 100001, Michael has to pay rent 750~*/
Insert into BATransaction (MyAccount_ID, MyUser_ID, Transaction_Type, Transaction_Amount) 
	values(100000, 100000,'Deposit',1823.90);
	/*Transaction id should be 100002, Michael god paid 1800~*/
Insert into BATransaction (MyAccount_ID, MyUser_ID, Transaction_Type, Transaction_Amount) 
	values(100002, 100001, 'Deposit', 3000);
	/*Transaction id should be 100003, clarie deposited 3000 into childs college fund*/
Insert into BATransaction (MyAccount_ID, MyUser_ID, Transaction_Type) 
	values(100002, 100001, 'Viewing');
	/*Transaction id should be 100004, Claire had a bad dream and had to check her account and 
her child's college fund*/
Insert into BATransaction (MyAccount_ID, MyUser_ID, Transaction_Type) 
	values(100001, 100001, 'Viewing');
	/*Transaction id should be 100005, Claire had a bad dream and had to check her account and 
her child's college fund*/
	
    
Insert into BADeletedUser Values(99999, LocalTimeStamp, 'kitedess', 'kd3ba92', 'Katherine', 'Alex', 'Frost', '8910 24th Street', 'San Antonio', 'Texas', '74893', 10.3 );
Insert into BADeletedUser Values(99998, LocalTimeStamp, 'darthmaul2', 'password123', 'Lance', '', 'Builder', '758 C Street', 'San Diego', 'Califonria', '4533', 0 );
Insert into BADeletedUser Values(99997, LocalTimeStamp, 'stevenuni3', '9drowssap0', 'Steven', 'Gem', 'Uni', '1200 President Circle', 'Washington D.C.', 'Virginia', '23r-34', .4 );



/*Show them after populating*/
Select * from BAUser;	
Select * from BALogin;	
Select * from BAAccount;	
Select * from BATransaction;	