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


@Entity
@Table(name = "gamemodes")
public class GameMode extends NamedEntity{
    public GameMode(){}
    @ManyToMany(targetEntity=Tournament.class,fetch=FetchType.EAGER,mappedBy = "gamemodes", cascade = CascadeType.ALL)
	private List<Tournament> Tournaments;
}
