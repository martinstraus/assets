create table kinds (
    id serial primary key,
    type smallint not null,
    symbol varchar(20) not null unique,
    constraint kinds_type_chk check (type >= 0 and type <= 3)
);

create table transactions (
    id serial primary key,
    creation_timestamp timestamp with time zone not null,
    date date not null,
    kind int not null,
    quantity numeric(10,2) not null,
    unitary_price_currency char(3) not null,
    unitary_price_value money not null,
    constraint transactions_asset_type_fk foreign key (kind) references kinds(id)
);

create or replace view assets as
    select kind, sum(quantity) as quantity from transactions group by kind;