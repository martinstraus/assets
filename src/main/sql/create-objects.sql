create table asset_types (
    id serial primary key,
    symbol varchar(20) not null unique
);

create table transactions (
    id serial primary key,
    creation_timestamp timestamp with time zone,
    asset_type int not null,
    quantity numeric(10,2) not null,
    unitary_price money not null,
    constraint transacttions_asset_type_fk foreign key (asset_type) references asset_types(id)
);
