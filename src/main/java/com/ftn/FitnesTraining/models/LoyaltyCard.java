package com.ftn.FitnesTraining.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name="loyalty_card")
public class LoyaltyCard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="point")
	private int point;
	@Column(name="discount")
	private int discount;

	//bi-directional many-to-one association to Korisnik
	@OneToMany(mappedBy= "loyaltyCard")
	private List<User> users;

	public LoyaltyCard() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPoint() {
		return this.point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getDiscount() {
		return this.discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public List<User> getKorisniks() {
		return this.users;
	}

	public void setKorisniks(List<User> users) {
		this.users = users;
	}

	public User addKorisnik(User user) {
		getKorisniks().add(user);
		user.setLoyaltyCard(this);

		return user;
	}

	public User removeKorisnik(User user) {
		getKorisniks().remove(user);
		user.setLoyaltyCard(null);

		return user;
	}

}