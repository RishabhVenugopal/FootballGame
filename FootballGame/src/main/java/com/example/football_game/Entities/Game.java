package com.example.football_game.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Game {
    @Id
    @GeneratedValue
    private int id;

    private int userId;

    private String status;

    private int userScore;

    private int cpuScore;

    private String attackStat;

    private String midfieldStat;

    private String defenceStat;

    public Game(){}

    public Game(int userId){
        this.userId = userId;
        this.status = "active";
        this.userScore = 0;
        this.cpuScore = 0;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getUserId() {
        return userId;
    }

    public int getUserScore() {
        return userScore;
    }

    public int getCpuScore() {
        return cpuScore;
    }

    public String getAttackStat() {
        return attackStat;
    }

    public String getDefenceStat() {
        return defenceStat;
    }

    public String getMidfieldStat() {
        return midfieldStat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCpuScore(int cpuScore) {
        this.cpuScore = cpuScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public void setAttackStat(String attackStat) {
        this.attackStat = attackStat;
    }

    public void setDefenceStat(String defenceStat) {
        this.defenceStat = defenceStat;
    }

    public void setMidfieldStat(String midfieldStat) {
        this.midfieldStat = midfieldStat;
    }
}
