package com.example.football_game.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.football_game.Entities.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {
    @Query(value = "SELECT * FROM male_players WHERE long_name LIKE CONCAT('%', :longName, '%') limit 30;", nativeQuery = true)
    List<Player>findByLongNameLike(@Param("longName") String longName);

    @SuppressWarnings("null")
    Iterable<Player>findAllById(Iterable<Integer> playerIds);

    @Query(value = "SELECT * FROM male_players WHERE overall > 75 AND pace != 'NULL' ORDER BY RAND() LIMIT 1" , nativeQuery = true)
    Player getRandomPlayer();
}
