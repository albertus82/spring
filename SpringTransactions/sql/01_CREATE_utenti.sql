CREATE TABLE utenti (
    username VARCHAR2 (50 CHAR) CONSTRAINT pk_utenti PRIMARY KEY,
    password VARCHAR2 (50 CHAR) CONSTRAINT nn_utenti_password NOT NULL,
    nome VARCHAR2 (50 CHAR) CONSTRAINT nn_utenti_nome NOT NULL,
    cognome VARCHAR2 (50 CHAR) CONSTRAINT nn_utenti_cognome NOT NULL,
    data_nascita DATE,
    data_inserimento TIMESTAMP (3)
            DEFAULT SYSTIMESTAMP
            CONSTRAINT nn_utenti_data_inserimento NOT NULL);