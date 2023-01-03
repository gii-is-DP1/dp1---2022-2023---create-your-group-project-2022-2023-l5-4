package org.springframework.samples.dobble.card;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import org.springframework.samples.dobble.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class HandedEntity {

    @ManyToMany
    private List<Card> cards;

    public Card getCurrentCard() {
        return cards.get(cards.size()-1);
    }

    public Card nextCard() {
        cards.remove(cards.size()-1);
        return getCurrentCard();
    }

    
}
