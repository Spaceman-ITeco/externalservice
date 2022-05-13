create schema dict;

create sequence dict.currency_id_seq;
create table dict.currency(
    id integer unique not null default nextval('dict.currency_id_seq'),
    name varchar not null,
    PRIMARY KEY (id)
);

ALTER TABLE dict.currency
    ADD CONSTRAINT unique_name unique (name);

insert into dict.currency(name) values ('USD'), ('EUR'), ('RUB'), ('GBP');

create schema ad;

create sequence ad.users_id_seq;
create table ad.users(
    id integer unique not null default nextval('ad.users_id_seq'),
    name varchar not null,
    email varchar,
    PRIMARY KEY (id)

);

create schema bank;

create sequence bank.bank_book_id_seq;

ALTER TABLE bank.bank_book
    ADD CONSTRAINT fk_bank_book_currency_id FOREIGN KEY (currency) REFERENCES dict.currency(id);

ALTER TABLE bank.bank_book
    ALTER COLUMN currency type integer
        USING currency::integer;

ALTER TABLE bank.bank_book
    ALTER COLUMN currency set not null;


ALTER TABLE bank.bank_book
drop constraint  fk_bank_book_currency_id;


create table bank.bank_book(
    id integer unique not null default nextval('bank.bank_book_id_seq'),
    number varchar not null,
    amount  decimal not null,
    currency varchar,
    user_id integer,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES ad.users(id),
    FOREIGN KEY (currency) REFERENCES dict.currency(id)
)