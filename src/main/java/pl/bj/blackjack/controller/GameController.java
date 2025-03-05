package pl.bj.blackjack.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bj.blackjack.model.Games;
import pl.bj.blackjack.service.GameService;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

//    before login option exists it's not secure
    @PostMapping("/player/{playerId}/play/{bet}")
    public long play(@PathVariable long playerId,@PathVariable int bet){
        return gameService.startGame(playerId,bet);
    }

    @GetMapping("/player/{playerId}/game/{gameId}")
    public Games getGame(@PathVariable long gameId){
        return gameService.getGames(gameId);
    }

    @GetMapping("/player/{playerId}/game/{gameId}/draw")
    public int drawCard(@PathVariable long playerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @GetMapping("/player/{playerId}/games/{gameId}/result")
    public boolean result(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
