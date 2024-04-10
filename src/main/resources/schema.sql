create schema if not exists forum;

create table forum."user"
(
    user_id identity primary key,
    username varchar not null check(length(trim(username)) >= 3) unique,
    password varchar not null check(length(trim(password)) >= 8),
    role varchar not null
);

create table forum.topic
(
    topic_id      identity primary key,
    title   varchar(50) not null check (length(trim(title)) >= 3)
);


create table forum.message
(
    message_id identity primary key,
    text varchar not null check (length(trim(text)) > 0),
    date timestamp not null,
    user_id bigint not null references forum."user" (user_id)  on delete cascade,
    topic_id bigint  not null references forum.topic (topic_id) on delete cascade
);

INSERT INTO forum.topic(title) VALUES
('school'),
('new year'),
('java');

INSERT INTO forum."user"(username, password, role ) VALUES
('nick', '123456789', 'ROLE_ADMIN'),
('bob', '123456789', 'ROLE_ADMIN'),
('robert', '123456789', 'ROLE_USER'),
('alex', '123456789', 'ROLE_USER');

INSERT INTO forum.message(text, date, user_id, topic_id) VALUES
('I wanna go back to school', '2022-02-23 12:00:00', 3, 1),
('Attention! change in the schedule', '2023-01-10 15:00:00', 1, 1),
('Exam results', '2023-06-21 13:00:00', 2, 1),
('I love this holiday so much', '2022-02-23 12:00:00', 3, 2),
('One day in New Year', '2022-01-01 00:00:00', 4, 2),
('Java 21: Release notes', '2021-02-23 12:00:00', 2, 3),
('How to become a Java Developer', '2022-02-23 22:00:00', 2, 3),
('Why Java is the best choice', '2020-02-23 09:00:00', 4, 3);







