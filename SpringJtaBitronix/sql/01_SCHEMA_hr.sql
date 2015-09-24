/* Oracle resources can only be recovered when the configured user has these privileges: */
GRANT SELECT ON sys.dba_pending_transactions TO hr;
GRANT SELECT ON sys.pending_trans$ TO hr;
GRANT SELECT ON sys.dba_2pc_pending TO hr;
GRANT EXECUTE ON sys.DBMS_SYSTEM TO hr;

CREATE TABLE hr.jta_bitronix_test
(
    id                 NUMBER (20, 0) CONSTRAINT pk_jta_bitronix_test PRIMARY KEY,
    testo              VARCHAR2 (64 CHAR) CONSTRAINT nn_jta_bitronix_test_text NOT NULL,
    data_inserimento   TIMESTAMP (3) DEFAULT SYSTIMESTAMP CONSTRAINT nn_jta_bitronix_text_data_ins NOT NULL
);

CREATE SEQUENCE hr.seq_jta_bitronix_test;
