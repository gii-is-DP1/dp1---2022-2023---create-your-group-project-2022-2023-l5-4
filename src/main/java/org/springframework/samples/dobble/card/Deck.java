package org.springframework.samples.dobble.card;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.samples.dobble.game.GameMode;
import org.springframework.samples.dobble.game.GameUser;


public class Deck extends ArrayList<Card> {

    Deck leftForCenter;

    public static Deck of(List<Card> cards){
        Deck deck = new Deck();
        deck.addAll(cards);
        return deck;
    }

    private static Deck of(Card card) {
        Deck deck = new Deck();
        deck.add(card);
        return deck;
    }
    public void shuffle(){
       Collections.shuffle(this);
    }

    public Map<GameUser,Deck> deal(List<GameUser> users, GameMode gameMode) {
        Map<GameUser,Deck> deal = new HashMap<>();
        this.shuffle();
        switch (gameMode.getName()) {
            case "The Well": 
                dealForTheWell(deal, users);
                break;
            case "The Tower": 
                dealForTheTower(deal, users);
                break;
            case "The Poisoned Gift":
                dealForThePoisonedGift(deal, users);
                break;
            default: dealForTheWell(deal, users);
        }
       
        return deal;
    }

    private void dealForThePoisonedGift(Map<GameUser, Deck> deal, List<GameUser> users) {
        //Same as the Tower
        dealForTheTower(deal, users);
    }
    
    private void dealForTheTower(Map<GameUser, Deck> deal, List<GameUser> users) {
        for(GameUser user : users){ 
            int i = users.indexOf(user);
            deal.put(user, Deck.of(this.get(i)));
        }
        setLeftForCenter(Deck.of(this.subList(users.size(), this.size())));
    }
    
 
    private void dealForTheWell(Map<GameUser,Deck> deal, List<GameUser> users) {
        Integer cardsPerUser = (this.size()-1)/users.size();
        int i = 0;
        for(GameUser user : users){ 
            deal.put(user, Deck.of(this.subList(i, i+cardsPerUser)));
            i += cardsPerUser;
        }
        setLeftForCenter(Deck.of(this.subList(i, this.size())));
    }

    public Deck getLeftForCenter() {
        return leftForCenter;
    }

    public void setLeftForCenter(Deck leftForCenter) {
        this.leftForCenter = leftForCenter;
    }
}
