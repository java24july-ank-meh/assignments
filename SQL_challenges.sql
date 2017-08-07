/*Section 2.1*/
Select * FROM EMPLOYEE;
Select * FROM EMPLOYEE WHERE LASTNAME = 'King';
Select * FROM EMPLOYEE WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;

/*Section 2.2*/
Select * FROM ALBUM ORDER BY TITLE DESC;
Select * FROM CUSTOMER ORDER BY CITY ASC;

/*Section 2.3*/
INSERT INTO GENRE VALUES (26, 'Punk Rock');
INSERT INTO GENRE VALUES (27, 'Power Metal');

/*Section 2.4*/
UPDATE CUSTOMER 
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';


/*Section 2.5*/
Select* FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';

/*Section 2.6*/
Select* FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;
Select* FROM EMPLOYEE WHERE HIREDATE BETWEEN '01-JUN-03' AND '01-MAR-04';

/*Section 2.7*/

/*Section 3.1 System Defined Function*/
Select CURRENT_TIMESTAMP, LOCALTIMESTAMP FROM DUAL;

Create or Replace FUNCTION mediatype_size (media_name IN VARCHAR2)
Return Number Is media_size Number;
Begin
    Select Length(media_name) 
    INTO media_size
    From MEDIATYPE
    Where NAME = media_name;
    Return(media_size);
END;
/
SELECT mediatype_size('AAC audio file') FROM DUAL;

/*Section 3.2 System Defined Aggregate Functions*/
SELECT AVG(TOTAL) From INVOICE;

SELECT MAX(UNITPRICE) From TRACK;

/*Section 3.3 User-defined Functions*/


/*Section 3.4 User Table defined Functions*/
    

/*Section 4.1 Stored Procedure*/
Create or Replace Procedure Names 
(F_name OUT VARCHAR2, L_name OUT VARCHAR2) AS
BEGIN
    Select FIRSTNAME INTO F_name FROM EMPLOYEE;
    Select LASTNAME INTO L_name FROM EMPLOYEE;
END;
/

Declare F_name Varchar2(20); L_name Varchar2(20);
Begin
    Names(F_name, L_name);
End;
/
