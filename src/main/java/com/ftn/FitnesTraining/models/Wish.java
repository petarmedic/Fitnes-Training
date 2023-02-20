package com.ftn.FitnesTraining.models;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name="wish")
public class Wish implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to Trening
	@ManyToOne
	private Training training;

	public Wish() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Training getTraining() {
		return this.training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

}