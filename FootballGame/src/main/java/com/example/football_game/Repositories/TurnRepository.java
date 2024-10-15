package com.example.football_game.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.football_game.Entities.Turn;

public interface TurnRepository extends JpaRepository<Turn, Integer> {
    
}
