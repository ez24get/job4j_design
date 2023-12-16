create table car_bodies(
	id serial primary key, 
	name text
);

create table car_engines(
	id serial primary key, 
	name text
);

create table car_transmissions(
	id serial primary key, 
	name text
);

create table cars(
	id serial primary key, 
	name text
	body_id references car_bodies(id), 
	engine_id references car_engines(id),
	transmission_id references car_transmissions(id)
);

insert into car_bodies name values('coupe', 'cabrio', 'sedan', 'pick-up');
insert into car_engines name values('v6', 'v8', 'w12', 'i4');
insert into car_transmissions name values('6', '8', 'variator', 'manual');
insert into cars name, body_id, engine_id, transmission_id 
values ('toyota rav4', 'bmw 320d', 'audi a5', 'bmw 640 coupe'), 
(1, 4, 4, 3), 
(2, 3, 3, 3), 
(4, 3, 1, 1);

select d.id as id, 
d.name as car_name, 
o.name as body_name, 
e.name as engine_name  
from cars d 
full join car_bodies o, car_engines e, car_transmissions f 
on d.body_id = o.id, d.engine_id = e.id, d.transmission_id = f.id;

select * from cars d 
left join car_bodies o 
on d.body_id = o.id
where o.id is null;