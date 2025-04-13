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
    opponent_score int default 0,
    win boolean default null,     --could be null?
    constraint fk_users_games foreign key (player_id) references Users(id)
);

create table Cards_of_game(
    id bigint auto_increment primary key,
    game_id bigint,
    card_rank varchar(8),
    card_suit varchar(8),
    card_owner varchar(8),
    card_value int,
    constraint fk_games_cards foreign key (game_id) references Games(id)
);