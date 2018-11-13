package de.rooftop.radio.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Role {

	public Role(String name) {

		this.name = name;
	}
	@Id
	@GeneratedValue
	private Long id;
	private String name;
}
