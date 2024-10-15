package com.example.football_game.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.example.football_game.Entities.Game;
import com.example.football_game.Entities.GamePlayer;
import com.example.football_game.Entities.GamePlayerId;

public interface GamePlayerRepository extends JpaRepository<GamePlayer, GamePlayerId>{
    List<GamePlayer> findByIdGame(Game game);
}
