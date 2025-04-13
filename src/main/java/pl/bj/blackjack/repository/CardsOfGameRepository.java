package pl.bj.blackjack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bj.blackjack.model.entity.CardsOfGame;

import java.util.List;

@Repository
public interface CardsOfGameRepository extends JpaRepository<CardsOfGame, Long> {
    List<CardsOfGame> findAllByGameId(long gameId);
}
