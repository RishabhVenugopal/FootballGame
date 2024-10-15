package com.example.football_game.Entities;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class UserPlayer {

    @EmbeddedId
    UserPlayerId id;

    public UserPlayer(){}

    public UserPlayer(UserPlayerId Id) {
        this.id = Id;
    }
    public UserPlayerId getId() {
        return id;
    }
    public void setId(UserPlayerId id) {
        this.id = id;
    }
}
