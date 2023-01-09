package org.springframework.samples.dobble.game;

public enum GameMode {
  
    THE_TOWER("The Tower"),
    THE_WELL("The Well"),
    THE_POISONED_GIFT("The Poisoned Gift");

    private String name; 
    private GameMode(String name){
        this.name = name;
    };

    public String toString() {
        return name;
    }
}
