DROP TABLE IF EXISTS "app_user";

CREATE SEQUENCE hibernate_sequence START 1;

CREATE TYPE access_role AS ENUM ('ADMIN', 'CONSUMER', 'PRODUCTOR', 'DELIVERER');

CREATE TABLE "app_user" (
  id SERIAL PRIMARY KEY,
  address varchar(255),
  email varchar(255) default NULL,
  first_name varchar(255) default NULL,
  name varchar(255) default NULL,
  password varchar(255),
  role roles
);

--drop table if exists address;
--drop table if exists app_user;
--drop table if exists city;
--drop table if exists country;
--create sequence hibernate_sequence start with 1 increment by 1;
--create sequence access_sequence start with 1 increment by 1;
--
--
--CREATE TYPE roleaccess AS ENUM('ADMIN','CONSUMER','DELIVERER');
--CREATE TABLE access(access_id serial, access_enum roleaccess[], primary key (access_id));
--
--create table address (id integer not null, city varchar(100), number double precision not null, street varchar(255), zipcode varchar(255), primary key (id));
--create table app_user (id integer not null, address varchar(255), email varchar(255) not null, first_name varchar(255), name varchar(255), password varchar(255) not null, role varchar(100));


-- <editor-fold desc="Description">
--acc roleaccess, primary key (id), CONSTRAINT fk_role_id FOREIGN KEY (acc) REFERENCES public."access" (access_id)-->
-- </editor-fold>