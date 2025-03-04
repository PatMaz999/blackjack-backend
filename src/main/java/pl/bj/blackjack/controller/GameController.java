package pl.bj.blackjack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @GetMapping("/play")
    public void play(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @GetMapping("/draw")
    public int drawCard() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @GetMapping("/result")
    public boolean result(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
