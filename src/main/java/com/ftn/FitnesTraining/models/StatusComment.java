package com.ftn.FitnesTraining.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



@Entity
@Table(name="status_comment")
public class StatusComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy= "statusComment")
	private List<Comment> comments;

	public StatusComment() {
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

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setStatusKomentara(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setStatusKomentara(null);

		return comment;
	}

}