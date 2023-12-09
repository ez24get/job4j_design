create table product(
	id serial primary key,
	name text,
	type_id int references type(id),
	expired_date date,
	price float
);

create table type(
	id serial primary key,
	name text
);

select p.id, p.name, p.expired_date, p.price, pp.name 
from product as p 
join type as pp on p.type_id = pp.id
where pp.name like '%СЫР%';

select name, expired_date, price from product where name like '%мороженое%';

select name, expired_date, price from product where expired_date < current_date;

select name, max(price) from product;

select count(p.name) 
from product as p 
join type as pp on p.type_id = pp.id
group by pp.name;

select p.id, p.name, p.expired_date, p.price, pp.name 
from product as p 
join type as pp on p.type_id = pp.id
where pp.name like '%СЫР%' or '%МОЛОКО%';

select count(p.name)
from product as p 
join type as pp on p.type_id = pp.id
where count(p.name) < 10 
group by pp.name;