create table users(
    id serial primary key,
    name varchar(255),
    users_id int references roles(id)
);

create table roles(
    id serial primary key,
	name varchar(255)
);

create table rules(
    id serial primary key,
    rules_text text
);

create table roles_rules(
    id serial primary key,
    roles_id int references roles(id),
    rules_id int references rules(id)
);

create table items(
    id serial primary key,
    name varchar(255),
	items_id_to_users int references users(id),
	items_id_to_categories int references categories(id),
	items_id_to_states int references states(id)
);

create table comments(
    id serial primary key,
	comments_text text,
    items_id int references items(id)
);

create table attachs(
    id serial primary key,
    attachs_text text,
    items_id int references items(id)
);

create table categories(
    id serial primary key,
    name varchar(255)
);

create table states(
    id serial primary key,
    name varchar(255)
);
