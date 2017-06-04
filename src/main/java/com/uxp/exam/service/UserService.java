package com.uxp.exam.service;

import java.util.List;

import com.uxp.exam.domain.User;

public interface UserService {
	public List<User> getAllUsers();
	public User getUser(Long userId) throws Exception;
	public User addUser(User user) throws Exception;
	public User updateUser(User user) throws Exception;
	public void deleteUser(Long userId) throws Exception;	
}
