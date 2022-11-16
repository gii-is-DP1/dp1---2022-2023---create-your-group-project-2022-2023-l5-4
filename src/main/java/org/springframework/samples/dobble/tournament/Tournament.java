package org.springframework.samples.dobble.tournament;




import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.samples.dobble.model.Person;
import org.springframework.samples.dobble.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tournaments")
public class Tournament {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=10)
	private long tournamentId;
    
    
    @NotBlank
    @Column(name = "password")
    private String password;

    @ManyToMany
    @Size(min = 2, max = 6)
    @JoinTable(name = "usernames")
	private List<User> user;

    
}
