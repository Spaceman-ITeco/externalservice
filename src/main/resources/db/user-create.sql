create schema dict;

create sequence dict.currency_id_seq;
create table dict.currency(
    id integer unique not null default nextval('dict.currency_id_seq'),
    name varchar not null
);

insert into dict.currency(name) values ('EUR');

create schema ad;

create sequence ad.users_id_seq;
create table ad.users(
    id integer unique not null default nextval('ad.users_id_seq'),
    name varchar not null,
    email varchar
);

create schema bank;

create sequence bank.bank_book_id_seq;


create table bank.bank_book(
    id integer unique not null default nextval('bank.bank_book_id_seq'),
    number varchar not null,
    amount  decimal not null,
    currency varchar,
    user_id integer
)