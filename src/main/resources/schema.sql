create table Users
(
    id bigint auto_increment primary key,
    username varchar(32) not null,
    points int not null,
    gameInProgress boolean default false,
    currentGameId long default -1
);

create table Games(
    id bigint auto_increment primary key,
    playerId long not null,
    bet int,
    score int,
    finished boolean default false,
    constraint fk_playerId foreign key (playerId) references Users(id)
);