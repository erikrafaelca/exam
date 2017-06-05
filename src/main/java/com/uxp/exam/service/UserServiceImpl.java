package com.uxp.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uxp.exam.domain.User;
import com.uxp.exam.domain.User.UserStatus;
import com.uxp.exam.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	static final String ADMIN = "admin";
	
	@Autowired
	MessageByLocaleService messageByLocaleService;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public List<User> getAllUsers() {
		return getUserRepository().findAll();
	}

	//@Cacheable("users")
	@Transactional(readOnly=true)
	public User getUser(Long userId) throws Exception {
		User user = getUserRepository().findOne(userId);
		if (user == null) {
			String msg = getMessageByLocaleService().getMessage("com.upx.exam.errors.0001", userId);
			throw new DataRetrievalFailureException(msg);
		}
		return user;
	}

	@Transactional()
	public User addUser(User user) throws Exception {
		try {
			user.setStatus(UserStatus.ACTIVATED);
			user.setPassword(getPasswordEncoder().encode(user.getPassword()));
			user = getUserRepository().save(user);
		} catch (DataIntegrityViolationException e) {
			StringBuffer msg = new StringBuffer(getMessageByLocaleService().getMessage("com.upx.exam.errors.0002", user.getName()));
			msg.append(e.getMessage());
			throw new RuntimeException(msg.toString()); 
		}
		return user;
	}

	@Transactional()
	public User updateUser(User user) throws Exception {
		try {
			user.setPassword(getPasswordEncoder().encode(user.getPassword()));
			user = getUserRepository().save(user);
		} catch (DataAccessException e) {
			StringBuffer msg = new StringBuffer(getMessageByLocaleService().getMessage("com.upx.exam.errors.0003", user.getName()));
			msg.append(e.getMessage());
			new RuntimeException(msg.toString());
		}
		return user;
	}

	@Transactional()
	public void deleteUser(Long userId) throws Exception {
		getUserRepository().delete(userId);
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User user;
		if (name.equals(ADMIN))
			user = new User(ADMIN, ADMIN);
		else
			user = getUserRepository().findByName(name);
		if (user == null) {
			String msg = getMessageByLocaleService().getMessage("com.upx.exam.errors.0001", name);
			throw new UsernameNotFoundException(msg);
		}
		return new UserDetailsImpl(user);
	}

	public MessageByLocaleService getMessageByLocaleService() {
		return messageByLocaleService;
	}

	public void setMessageByLocaleService(MessageByLocaleService messageByLocaleService) {
		this.messageByLocaleService = messageByLocaleService;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

}