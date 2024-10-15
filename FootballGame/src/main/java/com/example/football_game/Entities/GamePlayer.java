package com.example.football_game.Entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class GamePlayer {

    @EmbeddedId
    GamePlayerId Id;

    private String position;

    private boolean userFlag;

    public GamePlayer(){}
    public GamePlayer(GamePlayerId Id, String position, boolean userFlag){
        this.Id = Id;
        this.position = position;
        this.userFlag = userFlag;
    }
    public boolean getUserFlag(){
        return this.userFlag;
    }
    public GamePlayerId getId() {
        return Id;
    }
    public String getPosition() {
        return position;
    }
    public void setId(GamePlayerId id) {
        Id = id;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public void setUserFlag(boolean userFlag) {
        this.userFlag = userFlag;
    }
}
