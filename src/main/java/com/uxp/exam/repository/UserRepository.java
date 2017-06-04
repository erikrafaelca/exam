package com.uxp.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uxp.exam.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByName(String name);
}
