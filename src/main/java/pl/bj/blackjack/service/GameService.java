package pl.bj.blackjack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bj.blackjack.model.Card;
import pl.bj.blackjack.model.Cards;
import pl.bj.blackjack.model.enmus.CardOwner;
import pl.bj.blackjack.model.entity.CardsOfGame;
import pl.bj.blackjack.model.entity.Games;
import pl.bj.blackjack.model.entity.Users;
import pl.bj.blackjack.repository.CardsOfGameRepository;
import pl.bj.blackjack.repository.GameRepository;
import pl.bj.blackjack.repository.PlayerRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GameService {
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final CardsOfGameRepository cardsOfGameRepository;

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

    public Users getPlayers(long id) {
        return playerRepository.findById(id).orElseThrow();
    }

    public List<Users> getPlayers() {
        return playerRepository.findAll();
    }

    //TODO: N + 1
    public List<Games> getPlayerGames(long id){
        return gameRepository.findAllByPlayerId(id);
    }

    public CardsOfGame drawCard(long playerId) {
        Users users = playerRepository.findById(playerId).orElseThrow();
        if (!users.isGameInProgress())
            throw new IllegalArgumentException("no game in progress");
        if (users.getCurrentGameId() < 1)
            throw new IllegalArgumentException("Can't find any game");
        //check if currentGameId is valid
        Games games = gameRepository.findById(users.getCurrentGameId()).orElseThrow();

        if(games.getScore() > 21)
            throw new IllegalArgumentException("Game isn't in progress");

//        Generating a unique card and saving it to the database
        Set<Card> CurrentCards = CardsOfGameMapper.mapToCardsSet(games.getCards());
        CardsOfGame card = new CardsOfGame(games.getId(), CardOwner.PLAYER, Cards.getUnique(CurrentCards));
        cardsOfGameRepository.save(card);

        games.setScore(games.getScore() + card.getCardValue());
        gameRepository.save(games);

        if(games.getScore() > 21){
            getResult(playerId); //if score is above 21 call getResult() function to end current game
        }

        return card;
    }

    public List<Card> getResult(long playerId) {
        Users users = playerRepository.findById(playerId).orElseThrow();
        if(users.getCurrentGameId() == -1)
            throw new IllegalArgumentException("no game in progress");
        Games games = gameRepository.findById(users.getCurrentGameId()).orElseThrow();
        users.setCurrentGameId(-1);
        users.setGameInProgress(false);
        games.setFinished(true);

        if(games.getScore() > 21){
            saveGameResult(games, users,0, false);
            return new ArrayList<>();
        }

        int opponentScore = 0;
        Set<Card> cards = CardsOfGameMapper.mapToCardsSet(cardsOfGameRepository.findAllByGameId(games.getId()));
        List<Card> opponentCards = new ArrayList<>();
        while(opponentScore <= games.getScore()) {
            Card card = Cards.getUnique(cards);
            opponentScore += card.getCardValue();
            cards.add(card);
            opponentCards.add(card);
            cardsOfGameRepository.save(new CardsOfGame(games.getId(), CardOwner.COMPUTER, card));

            if(opponentScore > 21){
                users.setPoints(users.getPoints() + games.getBet() * 2);
                saveGameResult(games, users, opponentScore, true);
                return opponentCards;
            }
        }
        saveGameResult(games, users,opponentScore, false);
        return opponentCards;
//        TODO: add draw option
    }

    private boolean saveGameResult(Games games, Users users, int opponentScore, boolean result) {
        games.setWin(result);
        games.setOpponentScore(opponentScore);
        gameRepository.save(games);
        playerRepository.save(users);
        return result;
    }


}
