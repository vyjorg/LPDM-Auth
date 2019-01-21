alter table if exists user_roles
  drop constraint if exists FKiw6x1n449b296erpsvdhqqh1;

alter table if exists user_roles
  drop constraint if exists FKq0a2ff2ouxxlo7sd6iimtusi4;

drop table if exists app_user cascade;

drop table if exists app_role cascade;

drop table if exists user_roles cascade;

create table app_user (
                        id  serial not null,
                        active boolean,
                        address_id int4,
                        birthday date,
                        email varchar(255) not null,
                        first_name varchar(255),
                        name varchar(255),
                        password varchar(255) not null,
                        registration_date timestamp,
                        tel varchar(255),
                        primary key (id)
);

create table app_role (
                        id  serial not null,
                        role_name varchar(255),
                        primary key (id)
);

create table user_roles (
                          id  serial not null,
                          app_role_id int4,
                          app_user_id int4,
                          primary key (id)
);

alter table if exists user_roles
  add constraint FKiw6x1n449b296erpsvdhqqh1
    foreign key (app_role_id)
      references app_role;

alter table if exists user_roles
  add constraint FKq0a2ff2ouxxlo7sd6iimtusi4
    foreign key (app_user_id)
      references app_user;