create table course (
    id serial primary key,
    name integer
);

create table branch (
    id serial primary key,
    name integer
);

create table students (
    id serial primary key,
    name varchar(50),
    course_id integer references course(id),
    branch_id integer references branch(id)
);

create table authors (
    id serial primary key,
    name varchar(50)
);

create table uni_library (
    id serial primary key,
    name varchar(50)
);

create table books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id),
    library_id integer references uni_library(id)
);

create table orders (
    id serial primary key,
    active boolean default true,
    book_id integer references books(id),
    student_id integer references students(id)
);

insert into course (name) values (1);
insert into course (name) values (2);

insert into branch (name) values (1);
insert into branch (name) values (2);

insert into students (name, course_id, branch_id) values ('Иван Иванов', 1, 1);
insert into students (name, course_id, branch_id) values ('Петр Петров', 2, 2);

insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');

insert into uni_library (name) values ('One');
insert into uni_library (name) values ('Two');
insert into uni_library (name) values ('Three');

insert into books (name, author_id, library_id) values ('Евгений Онегин', 1, 2);
insert into books (name, author_id, library_id) values ('Капитанская дочка', 1, 2);
insert into books (name, author_id, library_id) values ('Дубровский', 1, 1);
insert into books (name, author_id, library_id) values ('Мертвые души', 2, 3);
insert into books (name, author_id, library_id) values ('Вий', 2, 3);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (2, 2);

create view show_students
    as select s.name as student, c.name as course, n.name as branch, count(a.name),
     a.name as author from students as s
         join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
         join uni_library l on b.library_id = b.id
         join course c on s.course_id = c.id
         join branch n on s.branch_id = n.id
         group by (s.name, c.name, n.name, a.name) having count(a.name) >= 2;

select * from show_students;