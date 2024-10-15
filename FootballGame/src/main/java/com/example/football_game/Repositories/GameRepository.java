package com.example.football_game.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.football_game.Entities.Game;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Integer> {
    
    @Query(value = "SELECT * FROM game WHERE user_id = :userId AND status = 'active'", nativeQuery = true)
    Optional<Game> findGameByUserIdAndActive(@Param("userId")int userId);
}
