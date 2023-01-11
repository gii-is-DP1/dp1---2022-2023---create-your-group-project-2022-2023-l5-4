package org.springframework.samples.dobble.game;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.samples.dobble.card.HandedEntity;
import org.springframework.samples.dobble.tournament.Tournament;
import org.springframework.samples.dobble.user.User;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "games")
public class Game extends HandedEntity {

    public Game() {
    }
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private GameMode gamemode;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    @NotNull
    private User owner;

    @ManyToOne
    @JoinColumn(name = "winnerId")
    private User winner;

    @OneToMany(mappedBy = "game")
    @Size(min = 1, max = 6)
    private List<GameUser> gameUsers;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'LOBBY'")
    private GameState state;

    @Min(2)
    @Max(6)
    @ColumnDefault("6")
    private Integer maxPlayers;

    @ColumnDefault("null")
    private String accessCode;

    private LocalDateTime updatedAt;

    private LocalDateTime startedAt;

    public Boolean isPrivate() {
        return this.accessCode != null && this.accessCode != "";
    }

    public Boolean validAccessCode(String accessCode) {
        return this.accessCode == null || this.accessCode.equals(accessCode);
    }

    public void setAccessCode(String accessCode) {
        if (accessCode == "") this.accessCode = null;
        else this.accessCode = accessCode;
    }

    public Integer getNumUsers() {
        return this.getGameUsers().size();
    }
    public List<GameUser> getGameUsers() {
        if (this.gameUsers == null) gameUsers = new ArrayList<>();
        return this.gameUsers;
    }

    public boolean isFinished() {
        return this.state == GameState.FINISHED;
    }

    public boolean hasStarted() {
        return this.state != GameState.LOBBY;
    }

    public boolean isOnPlay() {
        return this.state == GameState.ON_PLAY;
    }
    public boolean isFull() {
        return this.getGameUsers().size()==this.maxPlayers;
    }

    public boolean isNew() {
		return this.id == null;
	}

}
