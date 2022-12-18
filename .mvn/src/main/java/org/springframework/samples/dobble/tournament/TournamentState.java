package org.springframework.samples.dobble.tournament;

public enum TournamentState {
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
