package org.springframework.samples.petclinic.tournament;






import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tournaments")
public class Tournament extends BaseEntity{

    
    @Column(nullable=false, length=100, name = "description")
	private String description;

    @Column(nullable=false, name = "password")
    private String password;

    
}
