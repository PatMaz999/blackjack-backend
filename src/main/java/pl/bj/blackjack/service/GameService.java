package pl.bj.blackjack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bj.blackjack.model.Games;
import pl.bj.blackjack.model.Users;
import pl.bj.blackjack.repository.GameRepository;
import pl.bj.blackjack.repository.PlayerRepository;

@Service
@RequiredArgsConstructor
public class GameService {
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    //returns a game ID, which can be used to connect to the game
    public long startGame(long playerId, int bet) {
        Users users = playerRepository.findById(playerId).orElseThrow();
        if (users.isGameInProgress())
            throw new IllegalArgumentException("Games is already in progress");
        int points = users.getPoints();
        if (points >= bet) {
            users.setPoints(points - bet);

            Games games = new Games(playerId, bet);

            long gameId = gameRepository.save(games).getId();
            users.setGameInProgress(true);
            users.setCurrentGameId(gameId);
            playerRepository.save(users);
            return gameId;
        } else {
            throw new IllegalArgumentException("You don't have enough points to bet");
        }
    }

    public Games getGames(long id) {
        return gameRepository.findById(id).orElseThrow();
    }

    public Users getUsers(long id) {
        return playerRepository.findById(id).orElseThrow();
    }

//    public int drawCard(long playerId){
//
//    }
}
