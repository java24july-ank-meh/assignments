-- TESTING
INSERT INTO ERS_USER (u_username,u_password,ur_id) VALUES ('tai','password',1);
INSERT INTO ERS_USER (u_username,u_password,ur_id) VALUES ('javier','password',1);
INSERT INTO ERS_USER (u_username,u_password,ur_id) VALUES ('mehrab','password',2);

INSERT INTO ERS_REIMBURSEMENT(r_amount,r_submitted,u_id_author,rt_type,rs_status) VALUES(500,CURRENT_TIMESTAMP,2,3,1);

SELECT * FROM ERS_REIMBURSEMENT WHERE u_id_author = 2 AND rs_status = 2;

SELECT R.r_amount, R.r_submitted, ERS_USER.u_username, rt_type, rs_status FROM ERS_REIMBURSEMENT R
    INNER JOIN ERS_USER ON R.u_id_author = ERS_USER.u_id;

SELECT R.r_amount, R.r_submitted, U.u_username, STATUS.rs_status, TYPE.rt_type FROM ERS_REIMBURSEMENT R
    INNER JOIN ERS_USER U ON R.u_id_author = U.u_id
    INNER JOIN ERS_REIMBURSEMENT_STATUS STATUS ON STATUS.rs_id = R.rs_status
    INNER JOIN ERS_REIMBURSEMENT_TYPE TYPE ON TYPE.rt_id = R.rt_type;
    
SELECT r_id,r_amount,r_description,r_receipt,r_submitted,r_resolved,U1.u_id,
    U1.u_username,U2.u_id,U2.u_username,rt_type,rs_status
    FROM ERS_REIMBURSEMENT
    INNER JOIN ERS_USER U1 ON u_id_author = U1.u_id
    INNER JOIN ERS_USER U2 ON u_id_resolver = U2.u_id
    WHERE rs_status = 2 OR rs_status = 3;
    
SELECT r_id,r_amount,r_description,r_receipt,r_submitted,u_id,
    u_username,rt_type,rs_status
    FROM ERS_REIMBURSEMENT
    INNER JOIN ERS_USER ON u_id_author = u_id
    WHERE rs_status = 1;