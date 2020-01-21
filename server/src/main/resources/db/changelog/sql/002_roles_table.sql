--liquibase formatted sql
--changeset yatsevich:002
--preconditions onFail:CONTINUE
--precondition-sql-check expectedResult:0 SELECT count(1) FROM information_schema.tables WHERE table_schema = 'public' AND table_name = 'roles';

CREATE SEQUENCE roles_id_seq INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 NO CYCLE;

CREATE TABLE roles (
  role_id        INT8 DEFAULT NEXTVAL('roles_id_seq'::regclass) PRIMARY KEY,
  role_name       VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE users_roles (
  user_id       INT8 NOT NULL,
  role_id       INT8 NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT users_roles_user_id_fk FOREIGN KEY (user_id) REFERENCES users (user_id),
  CONSTRAINT users_roles_role_id_fk FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

--rollback DROP TABLE IF EXISTS users_roles;
--rollback DROP TABLE IF EXISTS roles;
--roolback DROP SEQUENCE IF EXISTS roles_id_seq;

