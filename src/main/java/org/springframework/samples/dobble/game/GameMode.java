package org.springframework.samples.dobble.game;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.samples.dobble.model.NamedEntity;
import org.springframework.samples.dobble.tournament.Tournament;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


public enum GameMode {
  
    THE_TOWER("The Tower"),
    THE_WELL("The Well"),
    THE_POISONED_GIFT("The Poisoned Gift");

    private String gamemodeName; 
    private GameMode(String gamemodeName){
        this.gamemodeName = gamemodeName;
    };

    public String toString() {
        return gamemodeName;
    }


}
