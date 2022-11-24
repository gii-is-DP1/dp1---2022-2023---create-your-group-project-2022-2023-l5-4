package org.springframework.samples.dobble.game;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.dobble.model.NamedEntity;



@Entity
@Table(name = "gamemodes")
public class GameMode extends NamedEntity{}
