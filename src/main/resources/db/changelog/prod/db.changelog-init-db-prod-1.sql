--liquibase formatted sql

--changeset muslimov_vlad:1
create sequence if not exists user_seq start with 3 increment by 1;

create table if not exists users (
                                    id bigint not null,
                                    email varchar(255),
                                    username varchar(255),
                                    password varchar(255),
                                    role varchar(255),
                                    primary key (id)
);

--changeset muslimov_vlad:2
create sequence if not exists bucket_seq start with 3 increment by 1;

create table if not exists buckets (
                                    id bigint not null,
                                    user_id bigint unique,
                                    primary key (id)
);

--changeset muslimov_vlad:3
alter table if exists buckets
    add constraint buckets_fk_user
    foreign key (user_id) references users;

-- PRODUCTS
create sequence if not exists product_seq start with 3 increment by 1;

create table if not exists products (
                                     price numeric(38,2),
                                     id bigint not null,
                                     title varchar(255),
                                     primary key (id)
);

--changeset muslimov_vlad:4
create table if not exists bucket_products (
                                      bucket_id bigint not null,
                                      product_id bigint not null
);

alter table if exists bucket_products
    add constraint bucket_products_fk_product
    foreign key (product_id) references products;

alter table if exists bucket_products
    add constraint bucket_products_fk_bucket
    foreign key (bucket_id) references buckets;

--changeset muslimov_vlad:5
create sequence if not exists order_seq start with 3 increment by 1;

create table if not exists orders (
                                      sum numeric(38,2),
                                      changed timestamp(6),
                                      created timestamp(6),
                                      id bigint not null,
                                      user_id bigint,
                                      address varchar(255),
                                      status varchar(255),
                                      primary key (id)
);

alter table if exists orders
    add constraint orders_fk_user
    foreign key (user_id) references users;

--changeset muslimov_vlad:6
create sequence if not exists order_details_seq start with 3 increment by 1;

create table if not exists orders_details (
                                              amount numeric(38,2),
                                              price numeric(38,2),
                                              id bigint not null,
                                              order_id bigint,
                                              product_id bigint,
                                              primary key (id)
);

alter table if exists orders_details
    add constraint orders_details_fk_order
    foreign key (order_id) references orders;

alter table if exists orders_details
    add constraint orders_details_fk_product
    foreign key (product_id) references products;