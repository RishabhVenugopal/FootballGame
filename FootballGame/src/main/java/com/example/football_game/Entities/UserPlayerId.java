package com.example.football_game.Entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class UserPlayerId implements Serializable{

    @ManyToOne
    private Player player;
    @ManyToOne
    private User user;

    public UserPlayerId(){}

    public UserPlayerId(User user, Player player){
        this.player = player;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Player getPlayer() {
        return player;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPlayerId that = (UserPlayerId) o;
        return Objects.equals(user, that.user) && 
               Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, player);
    }
}
