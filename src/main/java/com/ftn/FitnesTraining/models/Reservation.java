package com.ftn.FitnesTraining.models;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name="reservation")
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "confirmation")
	private byte confirmation;
	@Column(name = "point")
	private int point;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to TerminOdrzavanjaTreninga
	@ManyToOne
	@JoinColumn(name="training_schedule_id")
	private TrainingSchedule trainingSchedule;

	public Reservation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getConfirmation() {
		return this.confirmation;
	}

	public void setConfirmation(byte confirmation) {
		this.confirmation = confirmation;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TrainingSchedule getTrainingSchedule() {
		return this.trainingSchedule;
	}

	public void setTrainingSchedule(TrainingSchedule trainingSchedule) {
		this.trainingSchedule = trainingSchedule;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
}