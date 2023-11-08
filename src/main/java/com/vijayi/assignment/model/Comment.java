package com.vijayi.assignment.model;

import java.util.Date;
import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long commentId;

	@Column(name = "message", nullable = false)
	private String message;

	@Column(name = "comment_date_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date commentDateTime;

	@ManyToOne
	@JoinColumn(name = "posted_by_user_id")
	private User postedByUser;

	public Comment() {

	}

	public Comment(String message, Date commentDateTime, User postedByUser) {
		this.message = message;
		this.commentDateTime = commentDateTime;
		this.postedByUser = postedByUser;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCommentDateTime() {
		return commentDateTime;
	}

	public void setCommentDateTime(Date commentDateTime) {
		this.commentDateTime = commentDateTime;
	}

	public User getPostedByUser() {
		return postedByUser;
	}

	public void setPostedByUser(User postedByUser) {
		this.postedByUser = postedByUser;
	}

	@Override
	public String toString() {
		return "Comment [message=" + message + ", commentDateTime=" + commentDateTime + ", postedByUser=" + postedByUser
				+ "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Comment comment = (Comment) o;
		return Objects.equals(commentId, comment.commentId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(commentId);
	}

}
