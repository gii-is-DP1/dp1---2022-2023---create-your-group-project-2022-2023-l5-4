package org.springframework.samples.dobble.tournament;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.samples.dobble.model.BaseEntity;
import org.springframework.samples.dobble.user.User;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tournaments")
public class Tournament extends BaseEntity{


    @ManyToMany(fetch= FetchType.LAZY) 
    @JoinTable(
        name = "usergames", 
        joinColumns = @JoinColumn(name = "tournamentId", nullable = false, table = "tournaments"),
        inverseJoinColumns = @JoinColumn(name = "userId", nullable = false, table = "users")
    )
    @Size(max = 6)
    private Set<User> users;

    @Min(2)
    @Max(10)
    @ColumnDefault("10")
    private Integer nGames;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    @NotNull
    private User owner;

    @NotNull
    private String password;

    @Min(2)
    @Max(6)
    private Integer maxPlayers;


    public void setAccessCode(String passw){
        if(!(passw==null || passw=="")) this.password = passw;
    }

    public Integer getNumUsers() {
        return this.users.size();
    }

    public Boolean validAccessCode(String passw){
        return this.password.equals(passw);
    }

    private Set<User> getUsersInternal(){
        if (this.getUsers() == null) setUsers(new HashSet<>());
        return this.getUsers();
    }

    public void addUser(User user) {
        this.getUsersInternal().add(user);
    }

    public void removeUser(User user){
        this.getUsersInternal().remove(user);
    }
    
}

