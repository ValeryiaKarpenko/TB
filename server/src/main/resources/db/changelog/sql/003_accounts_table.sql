--liquibase formatted sql
--changeset karpenko:003
--preconditions onFail:CONTINUE
--precondition-sql-check expectedResult:0 SELECT count(1) FROM information_schema.tables WHERE table_schema = 'public' AND table_name = 'account';

CREATE SEQUENCE account_id_seq INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 NO CYCLE;

CREATE TABLE accounts (
  account_id INT8 DEFAULT NEXTVAL('account_id_seq'::reclass) PRIMARY KEY,
  login VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  user_id INT8 NOT NULL,
  CONSTRAINT account_user_id_fk FOREIGN KEY (user_id) REFERENCES users (user_id),
 );
 
--rollback DROP TABLE IF EXISTS accounts;
--roolback DROP SEQUENCE IF EXISTS accounts_id_seq;
 
  