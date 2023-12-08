create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('samsung', 2000.5), ('apple', 5000.5), ('xiaomi', 1500.3);
insert into people(name) values('Igor'), ('Maria'), ('Dmitry'), ('Olga');
insert into devices_people(device_id, people_id) values(1, 1), (2, 2), (1, 3);
insert into devices_people(device_id, people_id) values(2, 1), (2, 2), (3, 3);
insert into devices_people(device_id, people_id) values(3, 1), (2, 2), (3, 4);

select avg(price) from devices;

select people.name, avg(devices.price) 
from devices_people as dp 
join devices 
on dp.device_id = devices.id 
join people 
on dp.people_id = people.id 
group by people.name;

select people.name, avg(devices.price) 
from devices_people as dp 
join devices 
on dp.device_id = devices.id 
join people 
on dp.people_id = people.id 
group by people.name 
having avg(devices.price) > 5000;