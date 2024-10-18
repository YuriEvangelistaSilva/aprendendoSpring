create table if not exists cities(
    id serial not null,
    name varchar(50) not null,
    state char(1) not null,
    constraint pk_cities primary key(id)
);


