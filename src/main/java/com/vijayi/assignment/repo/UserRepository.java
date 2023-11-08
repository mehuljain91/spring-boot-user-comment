package com.vijayi.assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijayi.assignment.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByCommentTo(String commentTo);
}
