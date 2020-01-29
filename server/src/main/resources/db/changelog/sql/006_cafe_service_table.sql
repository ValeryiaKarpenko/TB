--liquibase formatted sql
--changeset karpenko:005
--preconditions onFail:CONTINUE
--precondition-sql-check expectedResult:0 SELECT count(1) FROM information_schema.tables WHERE table_schema = 'public' AND table_name = 'cafe_services';

CREATE SEQUENCE cafe_service_id_seq INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 NO CYCLE;

CREATE TABLE cafe_services (
  cafe_service_id INT8 DEFAULT NEXTVAL('cafe_service_id_seq'::reclass) PRIMARY KEY,
  service_name VARCHAR(255) NOT NULL,
  cost INT8,
  cafe_id INT8 NOT NULL,
  CONSTRAINT cafe_service_id_fk FOREIGN KEY (cafe_service_id) REFERENCES cafes (cafe_id),
 );
 
--rollback DROP TABLE IF EXISTS cafe_services;
--roolback DROP SEQUENCE IF EXISTS cafe_service_id_seq;