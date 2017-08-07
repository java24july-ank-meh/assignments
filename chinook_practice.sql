
/*Section 2.1 SELECT statements*/
SELECT * FROM Employee;
SELECT * FROM employee WHERE LastName = 'King';
SELECT * FROM employee WHERE FirstName = 'Andrew' AND ReportsTo IS NULL;/*important to note that IS NULL is used for null values intead of = */

/***********************************************************************************/
/***********************************************************************************/
/*Section 2.2 Order By*/
SELECT title FROM album ORDER BY title DESC;
SELECT FirstName FROM customer ORDER BY city ASC;

/***********************************************************************************/
/***********************************************************************************/
/*Section 2.3 Insert Into*/
INSERT INTO genre VALUES(26, 'Techno');
INSERT INTO genre VALUES(27, 'TechnoMetal');

INSERT INTO employee(employeeid, firstname, lastname, city, state) VALUES (10, 'Hanna', 'Montana', 'Missoula', 'TX');
INSERT INTO employee(employeeid, firstname, lastname, city, state, phone) VALUES (11, 'Brook', 'Schneider', 'Reston', 'VA', '25523234');

/*email cannot be null*/
INSERT INTO customer(customerid, firstname, lastname, city, state, email) VALUES(60, 'John', 'Snow', 'Winterfell', 'WS', 'jonsnow_23@hotmail.com');
INSERT INTO customer(customerid, firstname, lastname, city, state, email) VALUES(61, 'Daenerys', 'Targaryen', 'Dragonstone', 'WS', 'dany_3drac@gmail.com');

/***********************************************************************************/
/***********************************************************************************/
/*Section 2.4 Update*/
UPDATE customer SET firstname = 'Robert', lastname = 'Walter' WHERE firstname = 'Aaron' AND lastname = 'Mitchell';
UPDATE artist SET name = 'CCR' WHERE name = 'Creedence Clearwater Revival';

/***********************************************************************************/
/***********************************************************************************/
/*Section 2.5 Like*/
SELECT * FROM invoice WHERE billingaddress LIKE 'T%';

/***********************************************************************************/
/***********************************************************************************/
/*Section 2.6 Between*/
SELECT * FROM invoice WHERE total BETWEEN 15 AND 50;
SELECT * FROM employee WHERE hiredate BETWEEN TO_DATE('06/01/2003', 'MM/DD/YYYY') AND TO_DATE('03/01/2004', 'MM/DD/YYYY');

/***********************************************************************************/
/***********************************************************************************/
/*Section 2.7 Delete*/
/*when deleting, an error may occur as you might try to erase something that
is a foreign key in another table, so you have to drop the constraint*/

ALTER TABLE invoice DROP CONSTRAINT fk_invoicecustomerid;
DELETE FROM customer WHERE firstname = 'Robert' AND lastname = 'Walter';
ALTER TABLE Invoice ADD CONSTRAINT FK_InvoiceCustomerId
    FOREIGN KEY (CustomerId) REFERENCES Customer (CustomerId)  ;

/***********************************************************************************/
/***********************************************************************************/
/***********************************************************************************/

/***** SQL Functions 3.0******/
/*3.1 System Defined Functions*/
BEGIN
    DBMS_OUTPUT.PUT_LINE(CURRENT_TIMESTAMP);
END;

SELECT LENGTH(name) "Lenght in characters" FROM mediatype;

/***********************************************************************************/
/***********************************************************************************/
/*3.2 System Aggregated Functions*/
CREATE OR REPLACE FUNCTION ret_avg
   RETURN number 
   IS avg_number NUMBER(10,2) := 0;
   
   BEGIN
    SELECT AVG(total)
    INTO avg_number
    FROM invoice;
    RETURN avg_number;
END;
/

DECLARE
    c NUMBER(10,2);
BEGIN
    c := ret_avg();
    DBMS_OUTPUT.PUT_LINE('Average of total: ' || c);
END;
/

CREATE OR REPLACE FUNCTION greater_price
    RETURN NUMBER
    IS biggest NUMBER(10,2) := 0;
    
    BEGIN
        SELECT MAX(unitprice)
        INTO biggest
        FROM track;
        RETURN biggest;
END;
/

SELECT name FROM track WHERE unitprice = greater_price();

/***********************************************************************************/
/***********************************************************************************/
/*3.3 User Defined Functions */
CREATE OR REPLACE FUNCTION ret_avg_price
   RETURN number 
   IS avg_price NUMBER(10,2) := 0;
   
   BEGIN
    SELECT AVG(unitprice)
    INTO avg_price
    FROM invoiceline;
    RETURN avg_price;
END;
/

DECLARE
    c NUMBER(10,2);
BEGIN
    c := ret_avg_price();
    DBMS_OUTPUT.PUT_LINE('Average of unitprice: ' || c);
END;
/

/***********************************************************************************/
/***********************************************************************************/
/*3.4 User Defined Table Valued Functions*/
CREATE TYPE emp_birth AS OBJECT
    (firstname VARCHAR2(50), lastname VARCHAR2(50));
/

CREATE TYPE employees IS TABLE OF emp_birth;
/

CREATE OR REPLACE FUNCTION ret_emp_68
    RETURN employees PIPELINED IS
    BEGIN
        FOR i IN (SELECT firstname, lastname 
            FROM chinook.employee 
            WHERE birthdate > '1968-01-01') LOOP/* i need to format correctly this date*/
            PIPE ROW(emp_birth(i.firstname, i.lastname));
            END LOOP;
            
        RETURN;
END;
/

SELECT * FROM TABLE(ret_emp_68);

/***********************************************************************************/
/***********************************************************************************/
/***********************************************************************************/

/***** 4.0 Stored Procedures ******/
/*4.1 Basic Stored Procedure*/
CREATE OR REPLACE PROCEDURE select_with_cur AS/*used cursor to get the info from the table*/
    fname employee.firstname%TYPE;
    lname employee.lastname%TYPE;
    CURSOR emp_name IS
        SELECT firstname, lastname FROM employee;
BEGIN
    OPEN emp_name;
    LOOP
        FETCH emp_name into fname, lname;
        EXIT WHEN emp_name%NOTFOUND;
        
    END LOOP;
    CLOSE emp_name;
END;
/

DECLARE
  FNAME VARCHAR2(40) := 'Robert';
  LNAME VARCHAR2(20) := 'DeNiro';
  emp_manager employee.reportsto%TYPE;
BEGIN
    retrieveManagers(fname, lname, emp_manager);
    dbms_output.put_line('Manager: ' || emp_manager);
END;
/

/***********************************************************************************/
/***********************************************************************************/
/*4.2 Store procedure input parameters*/
CREATE OR REPLACE PROCEDURE updateTable(
    fname IN employee.firstname%TYPE, 
    lname IN employee.lastname%TYPE, 
    user_id IN employee.employeeid%TYPE) IS
BEGIN
    UPDATE employee 
    SET firstname = fname, lastname = lname
    WHERE employee.employeeid = user_id;
END;
/

EXECUTE updateTable('Robert', 'DeNiro', 4);

SELECT * FROM employee WHERE employeeid = 4;


CREATE OR REPLACE PROCEDURE retrieveManagers (
    fname IN employee.firstname%TYPE,
    lname IN employee.lastname%TYPE,
    emp_manager OUT employee.reportsto%TYPE) IS
BEGIN
    SELECT reportsto
    INTO emp_manager
    FROM employee
    WHERE firstname = fname 
    AND lastname = lname;
END;
/

DECLARE
  FNAME VARCHAR2(40) := 'Robert';
  LNAME VARCHAR2(20) := 'DeNiro';
  emp_manager employee.reportsto%TYPE;
BEGIN
    retrieveManagers(fname, lname, emp_manager);
    dbms_output.put_line('Manager: ' || emp_manager);
END;
/
/***********************************************************************************/
/***********************************************************************************/
/*4.3 Store procedure output parameters*/

CREATE OR REPLACE PROCEDURE returns_name(
    cust_id IN customer.customerid%TYPE,
    fname OUT customer.firstname%TYPE,
    lname OUT customer.lastname%TYPE,
    comp_name OUT customer.company%TYPE) IS

BEGIN
    SELECT firstname, lastname, company
    INTO fname, lname, comp_name
    FROM customer
    WHERE customer.customerid = cust_id;
END;
/

DECLARE
  CUST_ID NUMBER := 5;
  FNAME VARCHAR2(40);
  LNAME VARCHAR2(20);
  COMP_NAME VARCHAR2(80);
BEGIN
    returns_name(cust_id, fname, lname, comp_name);
    dbms_output.put_line('Company: ' || comp_name);
    dbms_output.put_line('Customer: ' || fname || ' ' || lname);
END;
/


/***********************************************************************************/
/***********************************************************************************/
/***********************************************************************************/
/***** 5.0 Transactions ******/

/*ALTER TABLE InvoiceLine ADD CONSTRAINT FK_InvoiceLineInvoiceId
    FOREIGN KEY (InvoiceId) REFERENCES Invoice (InvoiceId)  ;*/
SAVEPOINT SP_trans_del;
CREATE OR REPLACE PROCEDURE trans_del(
    table_name IN VARCHAR2,
    to_remove IN VARCHAR2,
    inv_id IN invoice.invoiceid%TYPE) IS
BEGIN
    BEGIN
        
        /*EXECUTE IMMEDIATE 'ALTER TABLE ' || table_name || ' DROP CONSTRAINT ' || to_remove;*/
        
        DELETE FROM invoice
        WHERE invoice.invoiceid = inv_id;
        
        
        /*EXECUTE IMMEDIATE 'ALTER TABLE ' 
            || table_name || ' ADD CONSTRAINT ' || 
            to_remove || ' FOREIGN KEY(invoiceid) REFERENCES invoice(invoiceid)';*/
        
        EXCEPTION 
            WHEN OTHERS THEN
            ROLLBACK TO SP_trans_del;
    END;
    COMMIT;
END;
/

SELECT * FROM invoice WHERE invoiceid = 4;
BEGIN 
    trans_del('invoice', 'fk_invoicelineinvoiceid', 4);
END;
/
SELECT * FROM invoice WHERE invoiceid = 4;


CREATE OR REPLACE PROCEDURE insert_cust(
    custid IN customer.customerid%TYPE,
    fname IN customer.firstname%TYPE,
    lname IN customer.lastname%TYPE,
    email IN customer.email%TYPE) IS
BEGIN
    BEGIN
        EXECUTE IMMEDIATE 'INSERT INTO customer(customerid, firstname, lastname, email) VALUES(' || custid || ','' ' || fname || ''', ''' || lname || ''', ''' || email ||''')';
    END;
    COMMIT;
END;
/

BEGIN
    insert_cust(62, 'John', 'Cena', 'youcantseeme');
END;
/

SELECT * FROM customer WHERE firstname = 'John';


/***********************************************************************************/
/***********************************************************************************/
/***********************************************************************************/
/***** 6.0 Triggers ******/

CREATE OR REPLACE TRIGGER after_insert_emp
    AFTER INSERT ON employee
    FOR EACH ROW
    WHEN(NEW.employeeid > 0)
DECLARE
    fname employee.firstname%TYPE;
BEGIN
    fname := :NEW.firstname;
    dbms_output.put_line('New employee added' || fname);
END;
/

INSERT INTO employee(employeeid, firstname, lastname)
VALUES(104, 'Lauro', 'Villar');


CREATE OR REPLACE TRIGGER after_update_alb
    AFTER UPDATE ON album
    FOR EACH ROW
BEGIN
    dbms_output.put_line('title has been changed');
END;
/

UPDATE album
SET title = 'new title'
WHERE albumid = 4;


CREATE OR REPLACE TRIGGER after_del_emp
    AFTER DELETE ON customer
    FOR EACH ROW
BEGIN
    dbms_output.put_line('someone was deleted');
END;
/

DELETE FROM customer WHERE customerid = 61;


/***********************************************************************************/
/***********************************************************************************/
/***********************************************************************************/
/***** 7.0 Joins ******/

/*7.1 Inner Join*/
/*Inner join joins data that both tables have in common.*/
SELECT customer.firstname, customer.lastname, invoice.invoiceid 
FROM customer
INNER JOIN invoice
ON invoice.invoiceid = customer.customerid;


/*7.2 Outer Join*/
/*returns all rows from at least one table*/
SELECT customer.customerid, customer.firstname, 
customer.lastname, invoice.invoiceid, invoice.total
FROM customer
FULL OUTER JOIN invoice 
ON customer.customerid = invoice.invoiceid;

/*7.3 Right Join*/
/*returns all records from the righ table(table 2) and matched records from the left table(table 1)*/
SELECT artist.name, album.title
FROM album
RIGHT JOIN artist
ON artist.artistid = album.artistid;

/*7.4 Cross*/
/*produces a result set which the number of rows in the first table multiplied by the number of rows in the second table*/
SELECT *
FROM artist
CROSS JOIN album
ORDER BY artist.name ASC;

/*7.5 Self Join*/
/*is used to join a table to its self as it were two tables*/
SELECT a.firstname, b.lastname
FROM employee a, employee b
WHERE a.reportsto = b.reportsto;

/*7.6 Join all Tables*/

SELECT * 
FROM customer
INNER JOIN employee
ON customer.customerid = employee.employeeid
INNER JOIN invoice 
ON customer.customerid = invoiceid;