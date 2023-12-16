create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255)
	departments_id int references departments(id)
);

insert into departments(name) values ('Dep 1');
insert into departments(name) values ('Dep 2');
insert into departments(name) values ('Dep 3');

insert into devices(name, departments_id) values ('Employee 1', 1);
insert into devices(name, departments_id) values ('Employee 2', 2);
insert into devices(name, departments_id) values ('Employee 3', 3);
insert into devices(name, departments_id) values ('Employee 4', null);
insert into devices(name, departments_id) values ('Employee 5', null);
insert into devices(name, departments_id) values ('Employee 6', 1);

select * from employees d left join departments o on d.departments_id = o.id;

select * from departments o right join employees d on d.departments_id = o.id;

select * from departments o left join employees d on o.id = d.departments_id;

select * from employees d right join departments o on d.departments_id = o.id;

select * from employees d full join departments o on d.departments_id = o.id;

select * from employees d cross join departments o;

select * from employees d 
left join departments o 
on d.departments_id = o.id 
where d.name is null;

create table teens(
    id serial primary key,
    name varchar(255), 
	gender int
);

insert into teens(name, gender) values ('teen 1', 1);
insert into teens(name, gender) values ('teen 2', 2);
insert into teens(name, gender) values ('teen 3', 1);
insert into teens(name, gender) values ('teen 4', 2);
insert into teens(name, gender) values ('teen 5', 1);
insert into teens(name, gender) values ('teen 6', 2);

select p1.name, p2.name 
from teens p1 
cross join teens p2 
where p1.gender > p2.gender;