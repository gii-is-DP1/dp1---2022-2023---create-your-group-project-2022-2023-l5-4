package org.springframework.samples.dobble.game;

public enum GameState {
    LOBBY{@Override
    public String toString() {
        return "LOBBY";
    }}, STARTED{@Override
    public String toString() {
        return "STARTED";
    }}, FINISHED{@Override
    public String toString() {
        return "FINISHED";
    }}
}
