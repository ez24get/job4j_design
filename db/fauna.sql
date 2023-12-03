create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('AA', 1,'2018-09-03');
insert into fauna(name, avg_age, discovery_date) values ('AB', 2,'2011-09-03');
insert into fauna(name, avg_age, discovery_date) values ('AC', 3,'2010-09-03');
insert into fauna(name, avg_age, discovery_date) values ('AD', 4,'2008-09-03');
insert into fauna(name, avg_age, discovery_date) values ('AE', 5,'2000-09-03');
insert into fauna(name, avg_age, discovery_date) values ('AF', 6,'2012-09-03');

select name, avg_age, discovery_date from fauna where name like '%fish%';
select name, avg_age, discovery_date from fauna where 10000 < avg_age and avg_age < 21000;
select name, avg_age, discovery_date from fauna where discovery_date is null;
select name, avg_age, discovery_date from fauna where discovery_date < '01.01.1950';