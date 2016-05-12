# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cuca (
  cuca_id                   bigint not null,
  cuca_tipo                 varchar(255),
  cuca_origem               varchar(255),
  constraint pk_cuca primary key (cuca_id))
;

create sequence cuca_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists cuca;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists cuca_seq;

