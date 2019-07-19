create table kinds (
    id serial primary key,
    type smallint not null,
    symbol varchar(20) not null unique,
    description varchar(200),
    constraint kinds_type_chk check (type >= 0 and type <= 3)
);

create table transactions (
    id serial primary key,
    creation_timestamp timestamp with time zone not null,
    date date not null,
    kind int not null,
    quantity numeric(12,6) not null,
    unitary_price_currency char(3) not null,
    unitary_price_value numeric(12,6) not null,
    constraint transactions_asset_type_fk foreign key (kind) references kinds(id)
);

create table valuations (
    kind int,
    timestamp timestamp with time zone not null,
    unitary_price_currency char(3) not null,
    unitary_price_value numeric(12,6) not null,
    primary key (kind, timestamp),
    constraint valuations_kind_fk foreign key (kind) references kinds(id)
);

create or replace view valuations_latest as
    SELECT distinct on (kind) *
    from valuations
    order by kind, timestamp desc;

create or replace view assets as
    select 
	t.kind, 
	sum(t.quantity) as quantity, 
	v.unitary_price_currency as kind_price_currency, 
	v.unitary_price_value as unitary_price_value,
	(v.unitary_price_value * sum(t.quantity))::numeric(12,6) as market_value
    from 
        transactions t
        left join valuations_latest v on v.kind = t.kind
    group by t.kind, v.unitary_price_currency, v.unitary_price_value;