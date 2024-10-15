package com.example.football_game.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Turn {
    @Id
    private int id;

    private int playerId;

    private String position;

    private int userId;

    public Turn(int playerId, String position, int userId){
        this.playerId = playerId;
        this.position = position;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }
    public int getPlayerId() {
        return playerId;
    }
    public String getPosition() {
        return position;
    }
    public int getUserId() {
        return userId;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
