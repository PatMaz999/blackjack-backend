create table users
(
    id BIGINT auto_increment primary key,
    username varchar(32) not null,
    points int not null
);