package com.vijayi.assignment.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vijayi.assignment.model.Comment;
import com.vijayi.assignment.service.CommentService;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/add-comment")
	public AddCommentResponse addComment(@RequestBody AddCommentRequest request) {
		AddCommentResponse response = new AddCommentResponse();
		try {
			commentService.addComment(request.getCommentFrom(), request.getCommentTo(), request.getMessage());
			response.setMessage("Comment added successfully");
		} catch (Exception e) {
			response.setMessage("Invalid Request");
		}
		return response;
	}

	@GetMapping("/get-comment")
	public GetCommentResponse getComment(@RequestParam String commentTo) {
		GetCommentResponse response = new GetCommentResponse();
		try {
			List<Comment> comments = commentService.getCommentsByRecipient(commentTo);
			response.setComments(comments);
		} catch (Exception e) {
			response.setComments(Collections.emptyList());
		}
		return response;
	}
}
