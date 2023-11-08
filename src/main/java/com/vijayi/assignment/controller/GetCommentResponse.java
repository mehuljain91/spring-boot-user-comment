package com.vijayi.assignment.controller;

import java.util.List;

import com.vijayi.assignment.model.Comment;

public class GetCommentResponse {

	private List<Comment> comments;
	
	public GetCommentResponse() {
		
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
}
