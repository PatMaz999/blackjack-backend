insert into Users(username, points) values('anonymous',2000);
insert into Users(username, points) values('Player1',2000);
insert into Users(username, points) values('Player2',3000);

insert into Games(player_id, bet, score, finished, win, opponent_score) values(1,20,19,1, true, 23);

insert into Cards_of_game(game_id, card_rank, card_suit, card_owner, card_value) values(1,'JACK','HEARTS','PLAYER',10);
insert into Cards_of_game(game_id, card_rank, card_suit, card_owner, card_value) values(1,'NINE','HEARTS','PLAYER',9);
insert into Cards_of_game(game_id, card_rank, card_suit, card_owner, card_value) values(1,'QUEEN','DIAMONDS','COMPUTER',10);
insert into Cards_of_game(game_id, card_rank, card_suit, card_owner, card_value) values(1,'FIVE','DIAMONDS','COMPUTER',5);
insert into Cards_of_game(game_id, card_rank, card_suit, card_owner, card_value) values(1,'EIGHT','SPADES','COMPUTER',8);