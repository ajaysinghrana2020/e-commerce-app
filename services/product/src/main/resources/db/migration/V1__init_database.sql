create table if not exists category(
                                       id integer not null primary key default nextval('category_seq'),
    description varchar(255),
    name varchar(225)
    );

create table if not exists product(
                                      id integer not null primary key default nextval('product_seq'),
    description varchar(255),
    name varchar(255),
    available_quantity double precision not null,
    price numeric(38,2),
    category_id integer
    constraint fk_category references category(id)
    );

create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;