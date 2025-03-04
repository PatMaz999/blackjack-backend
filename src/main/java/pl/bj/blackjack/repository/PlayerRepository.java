package pl.bj.blackjack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bj.blackjack.model.Users;

@Repository
public interface PlayerRepository extends JpaRepository<Users, Long> {
}
