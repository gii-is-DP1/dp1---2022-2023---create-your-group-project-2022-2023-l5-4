package org.springframework.samples.dobble.forum;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.dobble.comment.Comment;
import org.springframework.samples.dobble.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity	
@Getter
@Setter
public class Forum extends BaseEntity{



	public static final int MIN_SIZE = 3;
    public static final int MAX_SIZE = 50;

    @Size(min = MIN_SIZE, max = MAX_SIZE)
    @NotEmpty
    @Column(unique = true)
    private String name;

    @NotNull
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public Boolean hasComment(){
        return this.comments.size()>0;
    }

    public Forum(String name, LocalDateTime date){
        this.name=name;
        this.creationDate=date;
    }

    public Forum(){
        this.name="";
        this.creationDate=LocalDateTime.now();
    }
		
}
