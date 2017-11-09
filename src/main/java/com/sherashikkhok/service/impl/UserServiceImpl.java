package com.sherashikkhok.service.impl;

import com.sherashikkhok.model.Role;
import com.sherashikkhok.model.User;
import com.sherashikkhok.repository.RoleRepository;
import com.sherashikkhok.repository.UserRepository;
import com.sherashikkhok.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;



@Service("userService")
public class UserServiceImpl implements UserService {

	@Qualifier("userRepository")
	@Autowired
	private UserRepository userRepository;

	@Qualifier("roleRepository")
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {

		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("GENERAL");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

		userRepository.save(user);
	}

}
