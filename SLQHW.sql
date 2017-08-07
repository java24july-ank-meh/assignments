/*
2.1 SELECT
Task – Select all records from the Employee table.
Task – Select all records from the Employee table where last name is King.
Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL*/

SELECT * FROM EMPLOYEE;

SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King';

SELECT * FROM EMPLOYEE WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;

/*2.2
Task – Select all albums in Album table and sort result set in descending order by title.
Task – Select first name from Customer and sort result set in ascending order by city
*/
SELECT * FROM ALBUM ORDER BY TITLE DESC;
SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY ASC;

/*2.3
Task – Insert two new records into Genre table
Task – Insert two new records into Employee table
Task – Insert two new records into Customer table
*/
desc employee;
SELECT * FROM EMPLOYEE;
INSERT INTO GENRE VALUES(26, 'Pure Soul');
INSERT INTO GENRE VALUES(27, 'Smooth Jazz');

INSERT INTO EMPLOYEE VALUES(9,'Jackson','Miles', 6, '06/24/2006', TIMESTAMP, '1568 Tree Stomp', 'Richmond', 'VA','USA', '45895', '+1 (408) 569-2451', '+1 (408) 569-6551','mile@chinin.com');

/*2.4
Task – Update Aaron Mitchell in Customer table to Robert Walter
Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”*/
UPDATE CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE CUSTOMERID = 32;

UPDATE ARTIST
SET NAME = 'CCR'
WHERE ARTISTID = 76;

/*2.5 LIKE
Task – Select all invoices with a billing address like “T%”*/
SELECT * FROM INVOICE
WHERE BILLINGADDRESS LIKE 'T%';

/*2.6 BETWEEN
Task – Select all invoices that have a total between 15 and 50
Task – Select all employees hired between 1st of June 2003 and 1st of March 2004*/
SELECT * FROM INVOICE 
WHERE TOTAL BETWEEN 15 AND 50;

SELECT * FROM EMPLOYEE
WHERE HIREDATE BETWEEN '01-JUN-03' AND '01-MAR-04';
/*Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).*/
alter table INVOICE
ENABLE constraint FK_INVOICECUSTOMERID;
DELETE CUSTOMER 
WHERE CUSTOMERID = 32 ;

/*
3.1 System Defined Functions
Task – Create a function that returns the current time.*/

CREATE OR REPLACE FUNCTION GET_CURRENT_TIME RETURN VARCHAR2
IS Z VARCHAR2(4000);
BEGIN
    SELECT TO_CHAR(CURRENT_TIMESTAMP, 'DD-MON-RR HH.MI.SSXFF PM') TODAY INTO Z
       FROM DUAL;
   RETURN  Z;
END;

BEGIN
DBMS_OUTPUT.PUT_LINE(GET_CURRENT_TIME);
END;

/*Task – create a function that returns the length of a mediatype from the mediatype table
*/
CREATE OR REPLACE FUNCTION MEDIA_LENGTH 
RETURN NUMBER
IS Z NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO Z
    FROM MEDIATYPE;
    RETURN Z;
END;
BEGIN
DBMS_OUTPUT.PUT_LINE(MEDIA_LENGTH);
END;
    
/*
3.2 System Defined Aggregate Functions
Task – Create a function that returns the average total of all invoices*/
CREATE OR REPLACE FUNCTION AVG_INVOICE_TOTAL 
RETURN NUMBER
IS Z NUMBER;
BEGIN
      SELECT AVG(TOTAL)
   INTO Z
   FROM INVOICE;
   RETURN Z;
END;
BEGIN
DBMS_OUTPUT.PUT_LINE(AVG_INVOICE_TOTAL);
END;
/*
Task – Create a function that returns the most expensive track

*/
CREATE OR REPLACE FUNCTION MOST_EXPENSIVE_TRACK 
RETURN NUMBER 
IS Z NUMBER;
BEGIN
SELECT MAX(UNITPRICE)
INTO Z FROM TRACK;
RETURN Z;
END;
BEGIN
DBMS_OUTPUT.PUT_LINE(MOST_EXPENSIVE_TRACK);
END;

/*3.3 User Defined Functions
Task – Create a function that returns the average price of invoiceline items in the invoiceline table*/
CREATE OR REPLACE FUNCTION AVERAGE_PRICE
RETURN NUMBER
IS Z NUMBER;
BEGIN 
    SELECT AVG(UNITPRICE)
    INTO Z FROM
    INVOICELINE;
    RETURN Z;
END;
BEGIN
DBMS_OUTPUT.PUT_LINE(AVERAGE_PRICE);
END;


/*4.1 Basic Stored Procedure
Task – Create a stored procedure that selects the first and last names of all the employees.
*/
CREATE OR REPLACE PROCEDURE GET_FIRSTNAME_LASTNAME(S OUT SYS_REFCURSOR) 
AS
BEGIN
OPEN S FOR SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;
END;
/
DECLARE
S SYS_REFCURSOR;
GET_FIRSTNAME EMPLOYEE.FIRSTNAME%TYPE;
GET_LASTNAME EMPLOYEE.LASTNAME%TYPE;
BEGIN
GET_FIRSTNAME_LASTNAME(S);
LOOP
FETCH S INTO GET_FIRSTNAME, GET_LASTNAME;
EXIT WHEN S%NOTFOUND;
DBMS_OUTPUT.PUT_LINE('FIRST NAME: '||GET_FIRSTNAME||', LAST NAME: '||GET_LASTNAME);
END LOOP;
CLOSE S;
END;
/

/*Task – Create a stored procedure that updates the personal information of an employee.*/

/*7.1 INNER
Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.*/
SELECT *FROM CUSTOMER
INNER JOIN INVOICE ON INVOICE.CUSTOMERID = CUSTOMER.CUSTOMERID
ORDER BY INVOICE.CUSTOMERID;

/*
Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
*/
SELECT CUSTOMER.CUSTOMERID,CUSTOMER.FIRSTNAME ,CUSTOMER.LASTNAME,INVOICE.INVOICEID,INVOICE.TOTAL FROM CUSTOMER
FULL OUTER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.INVOICEID;


/*7.3 RIGHT
Task – Create a right join that joins album and artist specifying artist name and title.*/
SELECT TITLE, NAME FROM ALBUM
RIGHT JOIN ARTIST ON ALBUM.ARTISTID = ARTIST.ARTISTID;

/*7.4 CROSS
Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.*/
SELECT * FROM ARTIST CROSS JOIN ALBUM
ORDER BY ARTIST.NAME ASC;

/*
Task – Perform a self-join on the employee table, joining on the reportsto column.
*/
SELECT A.EMPLOYEEID , B.REPORTSTO
FROM EMPLOYEE A, EMPLOYEE B;





/*

3.4 User Defined Table Valued Functions
Task – Create a function that returns all employees who are born after 1968.

*/

SELECT * FROM CUSTOMER 
INNER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.INVOICEID
INNER JOIN INVOICELINE ON INVOICELINE.INVOICEID = INVOICE.INVOICEID
INNER JOIN TRACK ON TRACK.TRACKID = INVOICELINE.TRACKID
INNER JOIN GENRE ON GENRE.GENREID = TRACK.GENREID
INNER JOIN MEDIATYPE ON MEDIATYPE.MEDIATYPEID = TRACK.MEDIATYPEID
INNER JOIN ALBUM ON ALBUM.ALBUMID = TRACK.ALBUMID
INNER JOIN PLAYLISTTRACK ON PLAYLISTTRACK.TRACKID = TRACK.TRACKID
INNER JOIN PLAYLIST ON PLAYLIST.PLAYLISTID = PLAYLISTTRACK.PLAYLISTID
INNER JOIN ARTIST ON ARTIST.ARTISTID = ALBUM.ARTISTID
INNER JOIN EMPLOYEE ON EMPLOYEE.EMPLOYEEID =CUSTOMER.SUPPORTREID;