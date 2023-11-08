package com.vijayi.assignment.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vijayi.assignment.exception.InvalidRequestException;
import com.vijayi.assignment.model.Comment;
import com.vijayi.assignment.model.User;
import com.vijayi.assignment.repo.CommentRepository;
import com.vijayi.assignment.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void addComment(String commentFrom, String commentTo, String message) {

		if (!isValidUsername(commentFrom) || !isValidUsername(commentTo) || message.trim().isEmpty()) {
			throw new InvalidRequestException("Invalid Request");
		}

		User recipient = userRepository.findByCommentTo(commentTo);

		Comment newComment = new Comment();
		newComment.setMessage(message);
		newComment.setCommentDateTime(new Date());

		if (recipient != null) {
			newComment.setPostedByUser(recipient);
		} else {
			User newUser = new User();
			newUser.setCommentFrom(commentFrom);
			newUser.setCommentTo(commentTo);
			userRepository.save(newUser);
			newComment.setPostedByUser(newUser);
		}

		commentRepository.save(newComment);
	}

	public List<Comment> getCommentsByRecipient(String commentTo) {

		if (!isValidUsername(commentTo)) {
			throw new InvalidRequestException("Invalid Request");
		}
		return commentRepository.findByPostedByUserCommentTo(commentTo);
	}

	private boolean isValidUsername(String username) {
		return username != null && username.matches("^[a-zA-Z]+");
	}
}
