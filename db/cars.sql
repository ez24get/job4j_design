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
	name text, 
	body_id int references car_bodies(id), 
	engine_id int references car_engines(id), 
	transmission_id int references car_transmissions(id)
);

insert into car_bodies (name) values ('coupe'), ('cabrio'), ('sedan'), ('pick-up');
insert into car_engines (name) values ('v6'), ('v8'), ('w12'), ('i6');
insert into car_transmissions (name) values ('6'), ('8'), ('variator'), ('manual');
insert into cars (name, body_id, engine_id, transmission_id) 
values ('toyota rav4', 4, 4, 3), 
('bmw 320d', 3, 3, 3), 
('audi a5', 3, 1, 1), 
('bmw 640 coupe', 2, 3, 3);

select cars.id, cars.name as car_name, car_bodies.name as body_name, car_engines.name as engine_name, car_transmissions.name as transmission_name
from cars
full join car_bodies on cars.body_id = car_bodies.id
full join car_engines on cars.engine_id = car_engines.id
full join car_transmissions on cars.transmission_id = car_transmissions.id;

select * from car_bodies d 
left join cars o 
on o.body_id = d.id
where o.id is null;

select * from car_engines d 
left join cars o 
on o.engine_id = d.id
where o.id is null;

select * from car_transmissions d 
left join cars o 
on o.transmission_id = d.id
where o.id is null;