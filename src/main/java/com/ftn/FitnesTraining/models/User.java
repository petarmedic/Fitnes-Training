package com.ftn.FitnesTraining.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;



@Entity
@Table(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "adress")
	private String adress;

	@Column(name="phone_number")
	private String phoneNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="date_register")
	private Date dateRegister;

	@Temporal(TemporalType.DATE)
	@Column(name="date_birth")
	private Date dateBirth;
	@Column(name = "email")
	private String email;
	@Column(name = "name")
	private String name;

	@Column(name="username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "role")
	private String role;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy= "user")
	private List<Comment> comments;

	//bi-directional many-to-one association to ClanskaKartica
	@ManyToOne
	@JoinColumn(name="loyalty_card_id")
	private LoyaltyCard loyaltyCard;

	//bi-directional many-to-one association to Rezervacija
	@OneToMany(mappedBy= "user")
	private List<Reservation> reservations;

	//bi-directional many-to-one association to Zelja
	@OneToMany(mappedBy= "user")
	private List<Wish> wishes;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name = "active")
	private Boolean active;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String brojTelefona) {
		this.phoneNumber = brojTelefona;
	}

	public Date getDateRegister() {
		return this.dateRegister;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	public Date getDateBirth() {
		return this.dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setKorisnik(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setKorisnik(null);

		return comment;
	}

	public LoyaltyCard getLoyaltyCard() {
		return this.loyaltyCard;
	}

	public void setLoyaltyCard(LoyaltyCard loyaltyCard) {
		this.loyaltyCard = loyaltyCard;
	}

	public List<Reservation> getReservations() {
		return this.reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Reservation addReservation(Reservation reservation) {
		getReservations().add(reservation);
		reservation.setUser(this);

		return reservation;
	}

	public Reservation removeReservation(Reservation reservation) {
		getReservations().remove(reservation);
		reservation.setUser(null);

		return reservation;
	}

	public List<Wish> getWishs() {
		return this.wishes;
	}

	public void setWishs(List<Wish> wishes) {
		this.wishes = wishes;
	}

	public Wish addWish(Wish wish) {
		getWishs().add(wish);
		wish.setUser(this);

		return wish;
	}

	public Wish removeWish(Wish wish) {
		getWishs().remove(wish);
		wish.setUser(null);

		return wish;
	}


}