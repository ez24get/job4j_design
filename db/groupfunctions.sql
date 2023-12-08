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

insert into devices(name, price) values('samsung', 200.5), ('apple', 250.5), ('xiaomi', 150.3);
insert into people(name) values('Igor'), ('Maria'), ('Dmitry'), ('Olga');
insert into devices_people(device_id, people_id) values(1, 1), (1, 2), (1, 3);
insert into devices_people(device_id, people_id) values(2, 1), (2, 2), (3, 3);
insert into devices_people(device_id, people_id) values(3, 1), (4, 2), (4, 3);

select avg(price) from devices;

select people.name, avg(devices.price) 
join people.name 
on devices_people.people_id = people.id 
join devices.name 
on devices_people.device_id = devices.id 
group by people.name;