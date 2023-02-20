package com.ftn.FitnesTraining.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



@Entity
@Table(name="workout_room")
public class WorkoutRoom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "capacity")
	private int capacity;
	@Column(name = "name")
	private String name;

	//bi-directional many-to-one association to TerminOdrzavanjaTreninga
	@OneToMany(mappedBy= "workoutRoom")
	private List<TrainingSchedule> trainingSchedules;

	public WorkoutRoom() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TrainingSchedule> getTrainingSchedules() {
		return this.trainingSchedules;
	}

	public void setTrainingSchedules(List<TrainingSchedule> trainingSchedules) {
		this.trainingSchedules = trainingSchedules;
	}

	public TrainingSchedule addTerminOdrzavanjaTreninga(TrainingSchedule trainingSchedule) {
		getTrainingSchedules().add(trainingSchedule);
		trainingSchedule.setWorkoutRoom(this);

		return trainingSchedule;
	}

	public TrainingSchedule removeTerminOdrzavanjaTreninga(TrainingSchedule trainingSchedule) {
		getTrainingSchedules().remove(trainingSchedule);
		trainingSchedule.setWorkoutRoom(null);

		return trainingSchedule;
	}

}