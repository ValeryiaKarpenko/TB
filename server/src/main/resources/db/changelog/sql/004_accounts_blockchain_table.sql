--liquibase formatted sql
--changeset karpenko:004
--preconditions onFail:CONTINUE
--precondition-sql-check expectedResult:0 SELECT count(1) FROM information_schema.tables WHERE table_schema = 'public' AND table_name = 'accounts_blockchain';

CREATE SEQUENCE account_blockchain_id_seq INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 NO CYCLE;

CREATE TABLE accounts_blockchain (
  account_blockchain_id INT8 DEFAULT NEXTVAL('account_blockchain_id_seq'::reclass) PRIMARY KEY,
  address VARCHAR(255) UNIQUE NOT NULL,
  mnemonic VARCHAR(255) NOT NULL,
  balance INT8,
  user_id INT8 NOT NULL,
  CONSTRAINT account_blockchain_user_id_fk FOREIGN KEY (user_id) REFERENCES users (user_id),
 );
 
--rollback DROP TABLE IF EXISTS accounts_blockchain;
--roolback DROP SEQUENCE IF EXISTS accounts_blockchain_id_seq;