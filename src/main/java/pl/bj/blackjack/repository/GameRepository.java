package pl.bj.blackjack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bj.blackjack.model.Games;

@Repository
public interface GameRepository extends JpaRepository<Games, Long> {
}
