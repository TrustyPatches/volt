# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table volt_user (
  username                  varchar(255) not null,
  password                  varchar(255),
  role                      integer,
  chapter                   varchar(255),
  ring                      varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  email                     varchar(255),
  constraint ck_volt_user_role check (role in (0,1,2,3)),
  constraint pk_volt_user primary key (username))
;

create sequence volt_user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists volt_user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists volt_user_seq;

