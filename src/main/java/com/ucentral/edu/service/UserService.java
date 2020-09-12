package com.ucentral.edu.service;

import com.ucentral.edu.model.User;
import com.ucentral.edu.model.Usuario;

public interface UserService {

	User findByUserName(String userName);
	void saveUser(User user);
	User crearUser(User user);
}
