/* Oracle resources can only be recovered when the configured user has these privileges: */
GRANT SELECT ON sys.dba_pending_transactions TO alb_admin;
GRANT SELECT ON sys.pending_trans$ TO alb_admin;
GRANT SELECT ON sys.dba_2pc_pending TO alb_admin;
GRANT EXECUTE ON sys.DBMS_SYSTEM TO alb_admin;

CREATE TABLE alb_admin.utenti (
    username VARCHAR2 (50 CHAR) CONSTRAINT pk_utenti PRIMARY KEY,
    password VARCHAR2 (50 CHAR) CONSTRAINT nn_utenti_password NOT NULL,
    nome VARCHAR2 (50 CHAR) CONSTRAINT nn_utenti_nome NOT NULL,
    cognome VARCHAR2 (50 CHAR) CONSTRAINT nn_utenti_cognome NOT NULL,
    data_nascita DATE,
    data_inserimento TIMESTAMP (3)
            DEFAULT SYSTIMESTAMP
            CONSTRAINT nn_utenti_data_inserimento NOT NULL);
