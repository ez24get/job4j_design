create table vin(
    id serial primary key,
    number int
);

create table reg(
    id serial primary key,
	owner text,
    licence_plate varchar(255),
    vin_id int references vin(id) unique
);

insert into vin(number) values (123456);
insert into vin(number) values (123457);
insert into vin(number) values (123458);

insert into reg(owner, licence_plate, vin_id) values ('Ivan', 123, 1);
insert into reg(owner, licence_plate, vin_id) values ('Boris', 223, 2);
insert into reg(owner, licence_plate, vin_id) values ('Petr', 333, 3);
insert into reg(owner) values ('Vasya');
insert into reg(owner) values ('Anya');

select pp.owner, pp.licence_plate, p.number 
from reg as pp join vin as p on pp.vin_id = p.id;

select pp.owner as "Владелец", pp.licence_plate as "Регистрационный знак", p.number as VIN 
from reg as pp join vin as p on pp.vin_id = p.id;

select pp.owner as "Имя владельца", pp.licence_plate Номер, p.number ВИН 
from reg pp join vin p on pp.vin_id = p.id;