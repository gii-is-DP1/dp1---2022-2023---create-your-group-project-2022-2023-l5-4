package org.springframework.samples.dobble.game;

public enum GameState {
    LOBBY{@Override
    public String toString() {
        return "LOBBY";
    }}, ON_PLAY{@Override
    public String toString() {
        return "ON PLAY";
    }}, FINISHED{@Override
    public String toString() {
        return "FINISHED";
    }}
}
