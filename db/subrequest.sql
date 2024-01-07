CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

insert into customers (first_name, last_name, age, country) values ('one', 'a', 1, 'france');
insert into customers (first_name, last_name, age, country) values ('two', 'b', 1, 'russia');
insert into customers (first_name, last_name, age, country) values ('three', 'c', 1, 'germany');
insert into customers (first_name, last_name, age, country) values ('four', 'd', 2, 'usa');
insert into customers (first_name, last_name, age, country) values ('five', 'e', 2, 'japan');
insert into customers (first_name, last_name, age, country) values ('six', 'g', 2, 'marocco');
insert into customers (first_name, last_name, age, country) values ('seven', 'h', 2, 'mexico');

select first_name, last_name, age from customers
where age = (select min(age) from customers);

CREATE TABLE orders_sub
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into orders_sub (amount, customer_id) values (2, 1);
insert into orders_sub (amount, customer_id) values (3, 2);
insert into orders_sub (amount, customer_id) values (4, 3);
insert into orders_sub (amount, customer_id) values (1, 4);
insert into orders_sub (amount, customer_id) values (8, 5);
insert into orders_sub (amount, customer_id) values (0, 6);
insert into orders_sub (customer_id) values (7);

select first_name, last_name, age from customers
where orders_sub.amount not in (select * FROM orders_sub);