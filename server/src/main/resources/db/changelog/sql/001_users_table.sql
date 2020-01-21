--liquibase formatted sql
--changeset yatsevich:001
--preconditions onFail:CONTINUE
--precondition-sql-check expectedResult:0 SELECT count(1) FROM information_schema.tables WHERE table_schema = 'public' AND table_name = 'users';

CREATE SEQUENCE users_id_seq INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 NO CYCLE;

CREATE TABLE users (
  user_id         INT8 DEFAULT NEXTVAL('users_id_seq'::regclass) PRIMARY KEY,
  email           VARCHAR(255) UNIQUE NOT NULL,
  lastname        VARCHAR(255) NOT NULL,
  firstname       VARCHAR(255) NOT NULL,
  password        VARCHAR(255) NOT NULL
);

--rollback DROP TABLE users;
--roolback DROP SEQUENCE IF EXISTS users_id_seq;

