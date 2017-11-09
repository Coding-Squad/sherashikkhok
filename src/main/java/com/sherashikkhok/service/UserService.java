package com.sherashikkhok.service;

import com.sherashikkhok.model.User;

public interface UserService {
	
	public User findUserByEmail(String email);
	
	public void saveUser(User user);

}
