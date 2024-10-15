package com.example.football_game.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String userName;
    private String password;
    private double cash;
    public User(){}
    public User(String userName, String password, int cash) {
        this.userName = userName;
        this.password = password;
        this.cash = cash;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public double getCash() {
        return cash;
    }
    public void setCash(double cash) {
        this.cash = cash;
    }
    public String displayCash(){
        return String.valueOf(cash) + "M";
    }
}