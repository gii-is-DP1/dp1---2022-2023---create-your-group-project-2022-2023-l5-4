package org.springframework.samples.dobble.comment;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.dobble.forum.Forum;
import org.springframework.samples.dobble.model.BaseEntity;
import org.springframework.samples.dobble.user.User;


import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the ORGANO database table.
 * 
 */
@Entity
@Getter
@Setter
public class Comment extends BaseEntity{

    public Comment(String text2, String now, User user, Forum forum2) {
        this.text=text2;
        this.date=now;
        this.user=user;
        this.forum=forum2;
	}

    public Comment(){
        this.text="";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = LocalDateTime.now().format(formatter);
        this.date=formatDateTime;
        this.user=null;
        this.forum=null;
    }

	@NotEmpty
    @Size(max=500)
    private String text;

    @NotNull
    private String date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "forum_id", nullable = false)
    private Forum forum;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;
	
}
