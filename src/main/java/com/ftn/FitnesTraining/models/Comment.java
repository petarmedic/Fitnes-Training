package com.ftn.FitnesTraining.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;



@Entity
@Table(name="comment")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name = "anonymous")
	private byte anonymous;
	@Column(name = "rate")
	private int rate;
	@Column(name = "text")
	private String text;
	@Column(name = "date_post")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datePost;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to StatusKomentara
	@ManyToOne
	@JoinColumn(name="status_comment_id")
	private StatusComment statusComment;

	//bi-directional many-to-one association to Trening
	@ManyToOne
	private Training training;

	public Comment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getAnonymous() {
		return this.anonymous;
	}

	public void setAnonymous(byte anonymous) {
		this.anonymous = anonymous;
	}

	public int getRate() {
		return this.rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDateTime() {
		return this.datePost;
	}

	public void setDateTime(Date dateTime) {
		this.datePost = dateTime;
	}

	public User getKorisnik() {
		return this.user;
	}

	public void setKorisnik(User user) {
		this.user = user;
	}

	public StatusComment getStatusKomentara() {
		return this.statusComment;
	}

	public void setStatusKomentara(StatusComment statusComment) {
		this.statusComment = statusComment;
	}

	public Training getTrening() {
		return this.training;
	}

	public void setTrening(Training training) {
		this.training = training;
	}

}