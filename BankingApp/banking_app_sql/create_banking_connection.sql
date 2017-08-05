DROP USER bank CASCADE;

CREATE USER bank
IDENTIFIED BY bank
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect to bank;
GRANT resource to bank;
GRANT create session to bank;
GRANT CREATE TABLE TO bank;
GRANT CREATE view To bank;

CONNECT bank/bank;