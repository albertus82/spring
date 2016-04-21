/* Tabelle utilizzate da Spring Batch */
CREATE TABLE b_test_job_instance
(
    job_instance_id   NUMBER (19, 0) NOT NULL PRIMARY KEY,
    version           NUMBER (19, 0),
    job_name          VARCHAR2 (100) NOT NULL,
    job_key           VARCHAR2 (32) NOT NULL,
    CONSTRAINT test_job_inst_un UNIQUE (job_name, job_key)
);

CREATE TABLE b_test_job_execution
(
    job_execution_id             NUMBER (19, 0) NOT NULL PRIMARY KEY,
    version                      NUMBER (19, 0),
    job_instance_id              NUMBER (19, 0) NOT NULL,
    create_time                  TIMESTAMP NOT NULL,
    start_time                   TIMESTAMP DEFAULT NULL,
    end_time                     TIMESTAMP DEFAULT NULL,
    status                       VARCHAR2 (10),
    exit_code                    VARCHAR2 (2500),
    exit_message                 VARCHAR2 (2500),
    last_updated                 TIMESTAMP,
    job_configuration_location   VARCHAR (2500) NULL,
    CONSTRAINT test_job_inst_exec_fk FOREIGN KEY
        (job_instance_id)
         REFERENCES b_test_job_instance (job_instance_id)
);

CREATE TABLE b_test_job_execution_params
(
    job_execution_id   NUMBER (19, 0) NOT NULL,
    type_cd            VARCHAR2 (6) NOT NULL,
    key_name           VARCHAR2 (100) NOT NULL,
    string_val         VARCHAR2 (250),
    date_val           TIMESTAMP DEFAULT NULL,
    long_val           NUMBER (19, 0),
    double_val         NUMBER,
    identifying        CHAR (1) NOT NULL,
    CONSTRAINT test_job_exec_params_fk FOREIGN KEY
        (job_execution_id)
         REFERENCES b_test_job_execution (job_execution_id)
);

CREATE TABLE b_test_step_execution
(
    step_execution_id    NUMBER (19, 0) NOT NULL PRIMARY KEY,
    version              NUMBER (19, 0) NOT NULL,
    step_name            VARCHAR2 (100) NOT NULL,
    job_execution_id     NUMBER (19, 0) NOT NULL,
    start_time           TIMESTAMP NOT NULL,
    end_time             TIMESTAMP DEFAULT NULL,
    status               VARCHAR2 (10),
    commit_count         NUMBER (19, 0),
    read_count           NUMBER (19, 0),
    filter_count         NUMBER (19, 0),
    write_count          NUMBER (19, 0),
    read_skip_count      NUMBER (19, 0),
    write_skip_count     NUMBER (19, 0),
    process_skip_count   NUMBER (19, 0),
    rollback_count       NUMBER (19, 0),
    exit_code            VARCHAR2 (2500),
    exit_message         VARCHAR2 (2500),
    last_updated         TIMESTAMP,
    CONSTRAINT test_job_exec_step_fk FOREIGN KEY
        (job_execution_id)
         REFERENCES b_test_job_execution (job_execution_id)
);

CREATE TABLE b_test_step_execution_context
(
    step_execution_id    NUMBER (19, 0) NOT NULL PRIMARY KEY,
    short_context        VARCHAR2 (2500) NOT NULL,
    serialized_context   CLOB,
    CONSTRAINT test_step_exec_ctx_fk FOREIGN KEY
        (step_execution_id)
         REFERENCES b_test_step_execution (step_execution_id)
);

CREATE TABLE b_test_job_execution_context
(
    job_execution_id     NUMBER (19, 0) NOT NULL PRIMARY KEY,
    short_context        VARCHAR2 (2500) NOT NULL,
    serialized_context   CLOB,
    CONSTRAINT test_job_exec_ctx_fk FOREIGN KEY
        (job_execution_id)
         REFERENCES b_test_job_execution (job_execution_id)
);

CREATE SEQUENCE b_test_step_execution_seq START WITH 0
                                          MINVALUE 0
                                          MAXVALUE 9223372036854775807
                                          NOCYCLE;

CREATE SEQUENCE b_test_job_execution_seq START WITH 0
                                         MINVALUE 0
                                         MAXVALUE 9223372036854775807
                                         NOCYCLE;

CREATE SEQUENCE b_test_job_seq START WITH 0
                               MINVALUE 0
                               MAXVALUE 9223372036854775807
                               NOCYCLE;


/* Tabelle utilizzate dal batch di test */
CREATE SEQUENCE s_b_test_destination
/

CREATE SEQUENCE s_b_test_source
/

CREATE TABLE b_test_destination
(
    id            NUMBER (19, 0) NOT NULL,
    text_length   NUMBER (5, 0) NOT NULL,
    date_insert   DATE DEFAULT SYSDATE NOT NULL
)
/

CREATE TABLE b_test_source
(
    id            NUMBER (19, 0) NOT NULL,
    text_string   VARCHAR2 (2000 BYTE) NOT NULL,
    date_insert   DATE DEFAULT SYSDATE NOT NULL
)
/

INSERT INTO b_test_source (text, id)
     VALUES ('Lorem', s_b_test_source.NEXTVAL);

INSERT INTO b_test_source (text, id)
     VALUES ('ipsum', s_b_test_source.NEXTVAL);

INSERT INTO b_test_source (text, id)
     VALUES ('dolor', s_b_test_source.NEXTVAL);

INSERT INTO b_test_source (text, id)
     VALUES ('sit', s_b_test_source.NEXTVAL);

INSERT INTO b_test_source (text, id)
     VALUES ('amet', s_b_test_source.NEXTVAL);

INSERT INTO b_test_source (text, id)
     VALUES ('consectetur', s_b_test_source.NEXTVAL);

INSERT INTO b_test_source (text, id)
     VALUES ('adipiscing', s_b_test_source.NEXTVAL);

INSERT INTO b_test_source (text, id)
     VALUES ('elit', s_b_test_source.NEXTVAL);

COMMIT;