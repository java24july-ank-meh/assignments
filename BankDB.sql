/*DROP database if it exists*/
DROP user Banking Cascade;

/*Create the database user*/
Create user bank
Identified by password
default TABLESPACE temp
QUOTA 10M on users;

grant connect to bank;
grant resource to bank;
grant create session to bank;
grant create table to bank;
grant create view to bank;

/*Connect as new user, otherwise will use current user*/
connect bank/password;

/*Create tables*/
create table PERSON(
	SSN integer NOT NULL,
	FIRSTNAME varchar2(20) NOT NULL,
	LASTNAME varchar2(20) NOT NULL,
	PHONENUM varchar2(15) NOT NULL,
	P_ID varchar2(10) NOT NULL,
	P_PW varchar(10) NOT NULL,
	EMAIL varchar(50) NOT NULL,
	USER_ID integer NOT NULL,
	Constraint PK_PERSON primary key (SSN)
);

create table ACCOUNT(
	USER_ID integer NOT NULL,
	ACCOUNTNUM integer NOT NULL,
	balance integer NOT NULL,
	constraint PK_ACCOUNT primary key (USER_ID, ACCOUNTNUM)
);

/*create foreign keys*/
Alter table ACCOUNT add constraint FK_USER_ID foreign key (USER_ID)
	references PERSON (USER_ID) on delete cascade;
	
/*Create sequences*/
create sequence SQ_USER_ID_PERSON
start with 1
increment by 1;

create sequence SQ_BANK_ACCOUNT_ID
start with 1
increment by 1;

