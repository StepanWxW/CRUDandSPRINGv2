create table crudspring.book (
    id serial not null,
    author varchar(255),
    name varchar(255),
    year integer,
    person_id int references crudspring.person(id) on delete set null ,
    primary key (id)
);
create table crudspring.person (
    id serial not null,
    age integer,
    name varchar(255),
    primary key (id)
)