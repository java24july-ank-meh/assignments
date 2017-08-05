/*
Written by Eleazar Rosales
These are my solutions for the 
*/
/*2.1 select*/
select * from EMPLOYEE


select * from EMPLOYEE
where LASTNAME = 'King';


select * from EMPLOYEE
where FIRSTNAME = 'Andrew' and REPORTSTO is null;


/*2.2 order by*/
select * from ALBUM
order by TITLE asc;


select FIRSTNAME from CUSTOMER
order by CITY asc;


/* 2.3 INSERT INTO */
﻿insert into GENRE values(26, 'Dance');
insert into GENRE values(27, 'Eletronic');

﻿insert into EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME) values(9, 'Eleazar', 'Rosales');
﻿insert into EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME) values(10, 'Alma', 'Hernandez');

insert into CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL)
values(52, 'John', 'Smith', 'Jsmith@mail.com');
insert into CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL)
values(53, 'Jane', 'Eyre', 'Jeyre@mail.com');


/*2.4 update*/
update CUSTOMER
set FIRSTNAME = 'Robert', LASTNAME = 'Walter'
where FIRSTNAME = 'Aaron' and LASTNAME = 'Mitchell';

update ARTIST
set NAME = 'CCR'
where NAME = 'Creedence Clearwater Revival';


/* 2.5 Like */
select * from INVOICE
where BILLINGADDRESS like 'T%';


/* 2.6 Between*/
select * from INVOICE
where TOTAL between 15 and 50;

select * from EMPLOYEE
where HIREDATE between '01-JUN-03' and '01-MAR-04';


/* 2.7 delete */
﻿alter table INVOICE
Drop constraint FK_INVOICECUSTOMERID;

alter table INVOICE
add constraint FK_INVOICECUSTOMERID
foreign key (CUSTOMERID) references CUSTOMER(CUSTOMERID);

﻿alter table INVOICELINE
drop constraint FK_INVOICELINEINVOICEID;

alter table INVOICELINE
add constraint FK_INVOICELINEINVOICEID
foreign key (INVOICEID) references INVOICE(INVOICEID) on delete cascade;

delete from CUSTOMER
where FIRSTNAME = 'Robert' and LASTNAME = 'Walter';


/* 3.1 system defined functions */
﻿create or replace function PRINT_TIME
return date as CURTIME date;
begin
CURTIME := current_timestamp;
return CURTIME;
end;

begin
DBMS_OUTPUT.PUT_LINE(PRINT_TIME);
end;

﻿create or replace function PRINT_MEDIATYPE_LENGTH
return number as THELENGTH number;
BEGIN
select count(*) 
into THELENGTH
from MEDIATYPE;
return THELENGTH;
end;


begin
DBMS_OUTPUT.PUT_LINE(PRINT_MEDIATYPE_LENGTH);
end;


/* 3.2 system defined aggregate functions */
﻿create or replace function GET_AVERAGE
return number as TotalAverage INVOICE.TOTAL%type;
begin
select avg(INVOICE.TOTAL)
into TotalAverage
from INVOICE;
return TotalAverage;
end;

begin
DBMS_OUTPUT.PUT_LINE(GET_AVERAGE);
end;


﻿create or replace function GET_MAX_PRICE
return varchar2 as TRACKNAME TRACK.NAME%type;
begin
select TRACK.NAME
into TRACKNAME
from TRACK
where TRACK.UNITPRICE = (select max(TRACK.UNITPRICE) from TRACK)
and rownum <2;
return TRACKNAME;
end;

begin
DBMS_OUTPUT.PUT_LINE(GET_MAX_PRICE);
end;

/* 3.3 user defined functions */
﻿create or replace function GET_AVERAGE
return number as TotalAverage INVOICELINE.UNITPRICE%type;
begin
select avg(INVOICELINE.UNITPRICE)
into TotalAverage
from INVOICELINE;
return TotalAverage;
end;

begin
DBMS_OUTPUT.PUT_LINE(GET_AVERAGE);
end;


/* 3.4 user defined table valued functions */
﻿select EMPLOYEE.FIRSTNAME, EMPLOYEE.LASTNAME
from EMPLOYEE
where BIRTHDATE > '31-DEC-68';

create or replace function GET_EMPlOYEES
return number as EMPNUMS number;
begin
return EMPNUMS;
end;﻿


create or replace type emp_type as object(
    fname varchar2(15),
    lname varchar2(15)
);
/
create or replace type emp_temp as table of emp_type;
/
create or replace function empNames
return emp_temp as curEmp emp_temp;
begin
select EMPLOYEE.FIRSTNAME, EMPLOYEE.LASTNAME
into curEmp
from EMPLOYEE
where BIRTHDATE > '31-DEC-68';
return curEmp;
end;


/* 4.1 basic stored procedure */
﻿create or replace procedure Get_All_Names(S out Sys_Refcursor) as
begin
    open s for
        select FIRSTNAME, LASTNAME from EMPLOYEE;
end;
/
declare
    S sys_refcursor;
    SOME_FIRST EMPLOYEE.FIRSTNAME%TYPE;
    SOME_LAST EMPLOYEE.LASTNAME%TYPE;
begin
    Get_All_Names(S);
    
    loop
    fetch S into SOME_FIRST, SOME_LAST;
    Exit when S%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE(SOME_FIRST||' '||SOME_LAST);
    end loop;
    close S;
end;


/* 4.2 Stored procedure input parameters */
﻿create or replace procedure ChangeEMPinfo(NFIRSTNAME in varchar2, NLASTNAME in varchar2,
NADDRESS in varchar2,NCITY in varchar2, NSTATE in varchar2, NCOUNTRY in varchar2,
NPOSTALCODE in varchar2, NPHONE in varchar2, NFAX in varchar2, NEMAIL in varchar2) as
begin
update EMPLOYEE set ADDRESS = NADDRESS, CITY = NCITY, STATE = NSTATE, COUNTRY = NCOUNTRY,
POSTALCODE = NPOSTALCODE, PHONE = NPHONE, FAX = NFAX, EMAIL = NEMAIL
where FIRSTNAME = NFIRSTNAME and LASTNAME = NLASTNAME;
commit;
exception when OTHERS then DBMS_OUTPUT.PUT_LINE('Failed');
rollback;
end;

﻿begin
ChangeEMPinfo('Rosales', 'Eleazar', '123 Road', 'MP', 'VA', 'USA', '60160', '123456789',
'0987643', 'emore@mail.com');
end;



/* 4.3 Stored procedure output parameters */
﻿create or replace procedure DisplayCusInfo(S out sys_refcursor) as
begin
	open s for
		select FIRSTNAME, LASTNAME, COMPANY from CUSTOMER;
end;
/
declare
	S sys_refcursor;
    SOME_FIRST CUSTOMER.FIRSTNAME%TYPE;
    SOME_LAST CUSTOMER.LASTNAME%TYPE;
    SOME_COMPANY CUSTOMER.COMPANY%TYPE;

begin
	DisplayCusInfo(S);
	
	loop
	fetch S into SOME_FIRST, SOME_LAST, SOME_COMPANY;
	exit when S%NOTFOUND;
	DBMS_OUTPUT.PUT_LINE(SOME_FIRST||' '||SOME_LAST||' '||SOME_COMPANY);
	end loop;
	close S;
end;

/* 5.0 Transactions */
/* two or more sql statements that either all complete or fail*/
set transaction name 'DeleteInvoice';
delete from INVOICE where INVOICE.INVOICEID = '20';
delete from INVOICE where INVOICE.INVOICEID = '30';
commit;


﻿create or replace procedure NewCusInfo(NCUSTOMERID in number, NFIRSTNAME in varchar2,
NLASTNAME in varchar2, NEMAIL in varchar2) as
begin
insert into CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL)
values(NCUSTOMERID, NFIRSTNAME, NLASTNAME, NEMAIL);
commit;
exception when OTHERS then DBMS_OUTPUT.PUT_LINE('Failed');
rollback;
end;

begin
NewCusInfo(62, 'Adalina', 'Ocon', 'ada@mail.com')
end;

/* 6.1 After/For */
create or replace trigger TR_INSERT_EMPLOYEE
after insert on EMPLOYEE
for each ROW
begin
	DBMS_OUTPUT.PUT_LINE('Row inserted');
end;

﻿create or replace trigger TR_UPDATE_ALBUM
after update on ALBUM
for each ROW
begin
	DBMS_OUTPUT.PUT_LINE('updated row');
end;
/
create or replace trigger TR_DELETE_CUSTOMER
after delete on CUSTOMER
for each ROW
begin
	DBMS_OUTPUT.PUT_LINE('deleted row');
end;

 

/* 7.1 Inner Joins */
select CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID 
From CUSTOMER inner join INVOICE
on CUSTOMER.CUSTOMERID = INVOICE.INVOICEID;

/* 7.2 Outer Joins */
﻿select CUSTOMER.CUSTOMERID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, 
INVOICE.INVOICEID, INVOICE.TOTAL 
From CUSTOMER FULL OUTER join INVOICE
on CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

/* 7.3 Right Joins */
﻿select ARTIST.NAME, ALBUM.TITLE
from ALBUM right join ARTIST
on ALBUM.ARTISTID= ARTIST.ARTISTID;

/* 7.4 Cross Joins */
﻿select * from ALBUM cross join ARTIST
order by ARTIST.NAME asc;

/* 7.5 self Joins */
select a.FIRSTNAME, a.LASTNAME, b.REPORTSTO
from EMPLOYEE a, EMPLOYEE b
where a.REPORTSTO > b.REPORTSTO;

/* 7.6 Complicated Join assignment */
﻿select CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID,
INVOICELINE.INVOICELINEID, TRACK.TRACKID, GENRE.GENREID,
MEDIATYPE.MEDIATYPEID, ALBUM.ALBUMID, PLAYLISTTRACK.PLAYLISTID,
PLAYLIST.NAME, ARTIST.NAME, EMPLOYEE.EMPLOYEEID
From CUSTOMER inner join INVOICE
on CUSTOMER.CUSTOMERID = INVOICE.INVOICEID
inner join INVOICELINE
on INVOICELINE.INVOICEID = INVOICE.INVOICEID
inner join TRACK
on TRACK.TRACKID = INVOICELINE.TRACKID
inner join GENRE
on GENRE.GENREID =TRACK.GENREID
inner join MEDIATYPE
on MEDIATYPE.MEDIATYPEID = TRACK.MEDIATYPEID
inner join ALBUM
on ALBUM.ALBUMID = TRACK.ALBUMID
inner join PLAYLISTTRACK
on PLAYLISTTRACK.TRACKID = TRACK.TRACKID
inner join PLAYLIST
on PLAYLIST.PLAYLISTID = PLAYLISTTRACK.PLAYLISTID
inner join ARTIST
on ARTIST.ARTISTID = ALBUM.ARTISTID
inner join EMPlOYEE
on EMPlOYEE.EMPLOYEEID = CUSTOMER.SUPPORTREPID;


/* 9.0 Administration Joins */



