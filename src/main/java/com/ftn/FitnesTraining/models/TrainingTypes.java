package com.ftn.FitnesTraining.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



@Entity
@Table(name="training_types")
public class TrainingTypes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;

	//bi-directional many-to-many association to Trening
	@ManyToMany(mappedBy= "trainingTypes")
	private List<Training> trainings;

	public TrainingTypes() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Training> getTrainings() {
		return this.trainings;
	}

	public void setTrainings(List<Training> trainings) {
		this.trainings = trainings;
	}

}