package com.example.football_game.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.football_game.Entities.UserPlayer;
import com.example.football_game.Entities.UserPlayerId;

@Repository
public interface UserPlayerRepository extends JpaRepository<UserPlayer, UserPlayerId> {
    @Query(value = "SELECT player_id FROM user_player WHERE user_id = :userId", nativeQuery = true)
    List<Integer> findPlayerIdsByUserId(@Param("userId")int userId);
}
