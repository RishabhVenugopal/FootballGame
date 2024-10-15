package com.example.football_game.Entities;

import java.io.Serializable;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class GamePlayerId implements Serializable{

    @ManyToOne
    private Game game;
    @ManyToOne
    private Player player;

    public GamePlayerId(){}
    public GamePlayerId(Game game, Player player){
        this.game = game;
        this.player = player;
    }

    public Game getGame() {
        return game;
    }
    public Player getPlayer() {
        return player;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePlayerId that = (GamePlayerId) o;
        return Objects.equals(game, that.game) && 
               Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, player);
    }
}
