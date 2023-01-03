package org.springframework.samples.dobble.card;

import java.util.ArrayList;
import java.util.List;

public class Hand extends ArrayList<Card>{

    public static Hand of(List<Card> cards){
        Hand hand = new Hand();
        hand.addAll(cards);
        return hand;
    }

    public Card getCurrentCard() {
        return this.get(this.size()-1);
    }

    public Card nextCard() {
        this.remove(this.size()-1);
        return getCurrentCard();
    }

    
}
