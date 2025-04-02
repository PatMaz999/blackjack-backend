package pl.bj.blackjack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bj.blackjack.model.Games;
import pl.bj.blackjack.model.Users;
import pl.bj.blackjack.repository.GameRepository;
import pl.bj.blackjack.repository.PlayerRepository;

import java.util.Random;

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

    public int drawCard(long playerId) {
        Users users = playerRepository.findById(playerId).orElseThrow();
        if (!users.isGameInProgress())
            throw new IllegalArgumentException("no game in progress");
        if (users.getCurrentGameId() < 1)
            throw new IllegalArgumentException("Can't find any game");
        //check if currentGameId is valid

        Games games = gameRepository.findById(users.getCurrentGameId()).orElseThrow();

        if(games.getScore() > 21)
            return games.getScore();

        Random rand = new Random();
        games.setScore(games.getScore() + rand.nextInt(1, 11));

        gameRepository.save(games);

        if(games.getScore() > 21){
            getResult(playerId); //if score is above 21 call getResult() function to end current game
        }

        return games.getScore();
//        INFO return score, result(false = lose, null = not finished, true = win), opponentScore (-1 = not finished)
    }

    public boolean getResult(long playerId) {
        Users users = playerRepository.findById(playerId).orElseThrow();
        Games games = gameRepository.findById(users.getCurrentGameId()).orElseThrow();
        users.setCurrentGameId(-1);
        users.setGameInProgress(false);
        games.setFinished(true);

        if(games.getScore() > 21){
            games.setWin(false);
        }
        else{
        int opponentScore = 0;
        Random rand = new Random();
        while(opponentScore < games.getScore()) {
            opponentScore += rand.nextInt(1, 11);
            if(opponentScore > 21){
                games.setWin(true);
                break;
            }
            games.setOpponentScore(opponentScore);
        }
        if(games.getScore() > opponentScore)
            games.setWin(true);
        if(games.getWin() == null)
            games.setWin(false);

//        TODO: add draw option

        }

        if(games.getWin())
            users.setPoints(users.getPoints() + games.getBet() * 2);

        playerRepository.save(users);
        gameRepository.save(games);
        return games.getWin();

//        INFO return score, result(false = lose, null = error, true = win), opponentScore (-1 = not finished)
    }


}
