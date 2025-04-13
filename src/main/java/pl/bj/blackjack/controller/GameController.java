package pl.bj.blackjack.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.bj.blackjack.controller.dto.UsersDto;
import pl.bj.blackjack.model.Card;
import pl.bj.blackjack.model.entity.CardsOfGame;
import pl.bj.blackjack.model.entity.Games;
import pl.bj.blackjack.model.entity.Users;
import pl.bj.blackjack.service.GameService;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    //    before login option exists it's not secure
    @PostMapping("/players/{playerId}/play/{bet}")
    public long play(@PathVariable long playerId, @PathVariable int bet) {
        return gameService.startGame(playerId, bet);
    }

    @GetMapping("/games/{gameId}")
    public Games getGames(@PathVariable long gameId) {
        return gameService.getGames(gameId);
    }

    @GetMapping("/players/{playerId}/games")
    public List<Games> getPlayerGames(@PathVariable long playerId) {
        return gameService.getPlayerGames(playerId);
    }

    @GetMapping("/players")
    public List<UsersDto> getPlayers() {
        return UsersDtoMapper.mapToUsersDtos(gameService.getPlayers());
    }

    @GetMapping("/players/{id}")
    public Users getPlayers(@PathVariable long id) {
        return gameService.getPlayers(id);
    }

    @PatchMapping("/players/{playerId}/drawCard")
    public CardsOfGame drawCard(@PathVariable long playerId) {
        return gameService.drawCard(playerId);
    }

    @PatchMapping("/players/{playerId}/result")
    public List<Card> result(@PathVariable long playerId) {
        return gameService.getResult(playerId);
    }

}
