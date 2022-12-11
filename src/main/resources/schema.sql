drop table if exists books;
create table books (
    id      bigserial primary key,
    isbn    varchar(128),
    title   varchar(256)
);

drop table if exists auditlog;
create table auditlog(
    id           bigserial primary key,
    entity_type  varchar(128),
    event_type   varchar(128),
    payload      text,
    created      timestamp(3) default now() not null
);

drop table if exists authors;
create table authors(
    id           bigserial primary key,
    name         varchar(128),
    created      timestamp(3) default now() not null
);
alter table authors add constraint authors_name_chk check (char_length(name) <= 10);
