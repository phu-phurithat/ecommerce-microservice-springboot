create table if not exists category
(
    id bigint not null auto_increment primary key,
    name varchar(255) not null,
    description varchar(255)
);

create table if not exists product
(
    id bigint not null auto_increment primary key,
    name varchar(255) not null,
    description varchar(255),
    available_quantity double not null,
    price numeric(38,2),
    category_id bigint,
    foreign key (category_id) references category(id)
);