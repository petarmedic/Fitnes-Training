package com.ftn.FitnesTraining.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;



@Entity
@Table(name="training_schedule")
public class TrainingSchedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "date_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;

	//bi-directional many-to-one association to Rezervacija
	@OneToMany(mappedBy= "trainingSchedule")
	private List<Reservation> reservations;

	//bi-directional many-to-one association to Sala
	@ManyToOne
	private WorkoutRoom workoutRoom;

	//bi-directional many-to-one association to Trening
	@ManyToOne
	private Training training;

	public TrainingSchedule() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public Reservation addRezervacija(Reservation reservation) {
		getReservations().add(reservation);
		reservation.setTrainingSchedule(this);

		return reservation;
	}

	public Reservation removeRezervacija(Reservation reservation) {
		getReservations().remove(reservation);
		reservation.setTrainingSchedule(null);

		return reservation;
	}

	public WorkoutRoom getWorkoutRoom() {
		return this.workoutRoom;
	}

	public void setWorkoutRoom(WorkoutRoom workoutRoom) {
		this.workoutRoom = workoutRoom;
	}

	public Training getTrening() {
		return this.training;
	}

	public void setTrening(Training training) {
		this.training = training;
	}

	@Override
	public String toString() {
		return "TrainingSchedule{" +
				"id=" + id +
				", dateTime=" + dateTime +
				", reservations=" + reservations +
				", workoutRoom=" + workoutRoom +
				", training=" + training +
				'}';
	}
}