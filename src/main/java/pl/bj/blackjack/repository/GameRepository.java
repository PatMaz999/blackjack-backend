package pl.bj.blackjack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bj.blackjack.model.Games;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Games, Long> {
    List<Games> findAllByPlayerId(long playerId);
}
