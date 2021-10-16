package com.SpringAuthentication.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SpringAuthentication.model.entity.User;
import com.SpringAuthentication.service.UserApiService;

/**
 * @author Ankit Khatri
 */
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserApiService userApiService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		if (email == null || email.trim().isEmpty())
			throw new UsernameNotFoundException("user not found");
		/* getting the user from the database */
		User user = userApiService.fetchByEmail(email);
		if (user == null || !user.isStatus())
			throw new UsernameNotFoundException("user not found or temporarily inactive");

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}

}
