create table Users
(
    id bigint auto_increment primary key,
    username varchar(32) not null,
    points int not null,
    game_in_progress boolean not null default false,
    current_game_id bigint not null default -1
);

create table Games(
    id bigint auto_increment primary key,
    player_id bigint not null,
    bet int not null,
    score int not null,
    finished boolean not null default false,
    constraint fk_player_id foreign key (player_id) references Users(id)
);