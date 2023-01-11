package org.springframework.samples.dobble.card;

import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class HandedEntity {

    @ManyToMany
    private List<Card> cards;

    public Card getCurrentCard() {
        try{
            return cards.get(cards.size()-1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Card nextCard() {
        try {
            cards.remove(cards.size()-1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        return getCurrentCard();
    }

    
}
