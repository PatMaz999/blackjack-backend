package pl.bj.blackjack.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UsersDto {
    private long id;
    private String username;
    private int points;
    private boolean gameInProgress = false; //don't write to database
    private long currentGameId = -1;
}
