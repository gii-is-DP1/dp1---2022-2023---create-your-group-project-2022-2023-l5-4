package org.springframework.samples.dobble.user;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.samples.dobble.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authorities extends BaseEntity{
	
	public Authorities(){}

	public Authorities(User user, String authority) {
		this.authority = authority;
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "username")
	User user;
	
	@Size(min = 3, max = 50)
	String authority;
	
	public boolean equals(Object o) {
		if (!(o instanceof Authorities)) return false;
		Authorities other = (Authorities) o;
		return this.authority.equals(other.getAuthority()) && this.user.equals(other.getUser());
	}

	public String toString() {
		return this.authority;
	}
	
}
