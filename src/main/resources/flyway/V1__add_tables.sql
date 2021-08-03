create table permission
(
    id         int8 generated by default as identity,
    created_at timestamp not null,
    value      varchar(255) not null,
    role       int8 ,
    primary key (id)
);
create table role
(
    id         int8 generated by default as identity not null,
    created_at timestamp not null,
    name       varchar(255) not null,
    primary key (id)
);
create table users
(
    id         int8 generated by default as identity,
    created_at timestamp not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    login      varchar(255) not null,
    password   varchar(255) not null,
    role_id    int8,
    primary key (id)
);