package pl.bj.blackjack.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.bj.blackjack.model.Games;
import pl.bj.blackjack.model.Users;
import pl.bj.blackjack.service.GameService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

//    before login option exists it's not secure
    @PostMapping("/players/{playerId}/play/{bet}")
    public long play(@PathVariable long playerId,@PathVariable int bet){
        return gameService.startGame(playerId,bet);
    }

    @GetMapping("/games/{gameId}")
    public Games getGames(@PathVariable long gameId){
        return gameService.getGames(gameId);
    }

    @GetMapping("/players/{playerId}/games")
    public List<Games> getPlayerGames(@PathVariable long playerId){
        return gameService.getPlayerGames(playerId);
    }

    @GetMapping("/palyers/{id}")
    public Users getPlayers(@PathVariable long id) {
        return gameService.getUsers(id);
    }

    @PatchMapping("/players/{playerId}/drawCard")
    public int drawCard(@PathVariable long playerId) {
        return gameService.drawCard(playerId);
    }

    @PatchMapping("/players/{playerId}/result")
    public boolean result(@PathVariable long playerId){
        return gameService.getResult(playerId);
    }

}
