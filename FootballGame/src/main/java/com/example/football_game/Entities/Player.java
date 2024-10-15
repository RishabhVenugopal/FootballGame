package com.example.football_game.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "male_players")
public class Player {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "ea_id")
    private int eaId;

    @Column(name = "physical")
    private Integer physical;

    @Column(name = "defending")
    private Integer defending;

    @Column(name = "pace")
    private Integer pace;

    @Column(name = "passing")
    private Integer passing;

    @Column(name = "shooting")
    private Integer shooting;

    @Column(name = "dribbling")
    private Integer dribbling;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "long_name")
    private String longName;

    @Column(name = "fifa_version")
    private int fifaVersion;

    @Column(name = "value_eur")
    private float value;

    public int getId(){
        return this.id;
    }

    public int getEaId() {
        return eaId;
    }

    public Integer getPace(){
        return this.pace;
    }

    public Integer getPassing(){
        return this.passing;
    }

    public Integer getDefending(){
        return this.defending;
    }

    public Integer getDribbling(){
        return this.dribbling;
    }

    public Integer getPhysical(){
        return this.physical;
    }

    public Integer getShooting(){
        return this.shooting;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public double getValue() {
        float displayed = ((value*2)/1000000);
        return Math.round((displayed/2.0)*10)/10.0;
    }

    public String pageToImage(){
        String id = String.valueOf(this.eaId);
        while (id.length() < 6){
            id = "0" + id;
        }
        String URL = "https://cdn.sofifa.net/players/" + id.substring(0, 3) + "/" + id.substring(3) + "/" + String.valueOf(this.fifaVersion) + "_120.png";
        return URL;
    }

    public String getBestStat(){
        int [] stats = {pace, passing, defending, dribbling, physical, shooting};
        String [] statNames = {"Pace", "Passing", "Defending", "Dribbling", "Physical", "Shooting"};
        int index = 0;
        for (int i = 0; i <= 5; i++){
            if (stats[index] < stats[i]){
                index = i;
            }
        }
        return statNames[index];
    }

    public int getBestStatValue(){
        int [] stats = {pace, passing, defending, dribbling, physical, shooting};
        int best = 0;
        for (int i = 0; i <= 5; i++){
            if (best < stats[i]){
                best = stats[i];
            }
        }
        return best;
    }

    public int getUsedStat(String statName){
        switch (statName) {
            case "Pace":
                return getPace();
            case "Passing":
                return getPassing();
            case "Physical":
                return getPhysical();
            case "Shooting":
                return getShooting();
            case "Dribbling":
                return getDribbling();
            case "Defending":
                return getDefending();
            default:
                return 0;
        }
    }
}
