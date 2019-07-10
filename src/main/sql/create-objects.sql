create table kinds (
    id serial primary key,
    symbol varchar(20) not null unique
);

create table transactions (
    id serial primary key,
    creation_timestamp timestamp with time zone,
    kind int not null,
    quantity numeric(10,2) not null,
    unitary_price_currency char(3) not null,
    unitary_price_value money not null,
    constraint transactions_asset_type_fk foreign key (kind) references kinds(id)
);

create or replace view assets as
    select kind, sum(quantity) as quantity from transactions group by kind;