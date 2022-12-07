

package org.springframework.samples.dobble.player;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.Getter;
import lombok.Setter;

@Entity	
@Getter
@Setter
@Table(name="players")
public class Player {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=10)
	private long id;
	
	
	@Column(nullable=false, unique=true)
	private String name;
		
}
