package com.ftn.FitnesTraining.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



@Entity
@Table(name="training")
public class Training implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="prices")
	private int prices;

	@Column(name="name")
	private String name;

	@Column(name="level_training")
	private String levelTraining;

	@Column(name="description")
	private String description;
	@Column(name="photo")
	private String photo;

	@Column(name="training_duration")
	private int trainingDuration;
	@Column(name="trainer")
	private String trainer;

	@Column(name="training_kind")
	private String trainingKind;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy= "training")
	private List<Comment> comments;

	//bi-directional many-to-one association to TerminOdrzavanjaTreninga
	@OneToMany(mappedBy= "training")
	private List<TrainingSchedule> trainingSchedules;

	//bi-directional many-to-many association to TipTreninga
	@ManyToMany
	@JoinTable(
		name="training_types_has_training"
		, joinColumns={
			@JoinColumn(name="training_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="training_types_id")
			}
		)
	private List<TrainingTypes> trainingTypes;

	//bi-directional many-to-one association to Zelja
	@OneToMany(mappedBy= "training")
	private List<Wish> wishes;

	public Training() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrices() {
		return this.prices;
	}

	public void setPrices(int prices) {
		this.prices = prices;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevelTraining() {
		return this.levelTraining;
	}

	public void setLevelTraining(String levelTraining) {
		this.levelTraining = levelTraining;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getTrainingDuration() {
		return this.trainingDuration;
	}

	public void setTrainingDuration(int trainingDuration) {
		this.trainingDuration = trainingDuration;
	}

	public String getTrainer() {
		return this.trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public String getTrainingKind() {
		return this.trainingKind;
	}

	public void setTrainingKind(String trainingKind) {
		this.trainingKind = trainingKind;
	}

	public List<Comment> getKomentars() {
		return this.comments;
	}

	public void setKomentars(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addKomentar(Comment comment) {
		getKomentars().add(comment);
		comment.setTrening(this);

		return comment;
	}

	public Comment removeKomentar(Comment comment) {
		getKomentars().remove(comment);
		comment.setTrening(null);

		return comment;
	}

	public List<TrainingSchedule> getTrainingSchedules() {
		return trainingSchedules;
	}

	public void setTerminOdrzavanjaTreningas(List<TrainingSchedule> terminOdrzavanjaTreningases) {
		this.trainingSchedules = terminOdrzavanjaTreningases;
	}

	public TrainingSchedule addTerminOdrzavanjaTreninga(TrainingSchedule trainingSchedule) {
		getTrainingSchedules().add(trainingSchedule);
		trainingSchedule.setTrening(this);

		return trainingSchedule;
	}

	public TrainingSchedule removeTerminOdrzavanjaTreninga(TrainingSchedule trainingSchedule) {
		getTrainingSchedules().remove(trainingSchedule);
		trainingSchedule.setTrening(null);

		return trainingSchedule;
	}

	public List<TrainingTypes> getTipTreningas() {
		return this.trainingTypes;
	}

	public void setTipTreningas(List<TrainingTypes> tipTreningases) {
		this.trainingTypes = tipTreningases;
	}

	public List<Wish> getZeljas() {
		return this.wishes;
	}

	public void setZeljas(List<Wish> wishes) {
		this.wishes = wishes;
	}

	public Wish addZelja(Wish wish) {
		getZeljas().add(wish);
		wish.setTraining(this);

		return wish;
	}

	public Wish removeZelja(Wish wish) {
		getZeljas().remove(wish);
		wish.setTraining(null);

		return wish;
	}

}