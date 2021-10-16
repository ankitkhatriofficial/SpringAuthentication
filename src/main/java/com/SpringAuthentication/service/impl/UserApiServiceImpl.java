package com.SpringAuthentication.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SpringAuthentication.model.dto.UserRegDto;
import com.SpringAuthentication.model.entity.User;
import com.SpringAuthentication.repository.UserRepository;
import com.SpringAuthentication.service.UserApiService;
import com.SpringAuthentication.utils.Utils;

/**
 * @author Ankit Khatri
 */

/** Implementation class of UserApiService @interface */
@Service
public class UserApiServiceImpl implements UserApiService {

	@Autowired
	private Utils utils;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/* Function to retrieve user by userId */
	@Override
	public User fetch(String id) {
		Optional<User> optional = userRepo.findById(id);
		if (optional.isEmpty())
			return null;
		return optional.get();
	}

	/* Function to save the user details into the database */
	@Override
	public void save(UserRegDto userRegDto) {
		User user = new User();
		BeanUtils.copyProperties(userRegDto, user);
		/* Generating encodedPassword & signupVerification Key */
		String encodedPassword = passwordEncoder.encode(userRegDto.getPassword());
		String signupVerificationKey = utils.getRandomToken() + utils.getRandomToken();

		/**
		 * Setting Default properties after a user registers SignupDateTime =>
		 * Current_Server_DateTime Password => Encoded Password SignupVerificationKey =>
		 * RandomToken used to send on user's mail to verify account Status => False
		 * (User is temporarily not verified)
		 */
		user.setSignupDateTime(new Date());
		user.setPassword(encodedPassword);
		user.setSignupVerificationKey(signupVerificationKey);
		user.setStatus(false);
		/* Saving the user details to the database */
		userRepo.save(user);
		/* Sending verification mail to the user */
		if (!utils.sendSignupVerificationEmail(user.getEmail(), user.getSignupVerificationKey())) {
			userRepo.delete(user);
			throw new RuntimeException("Error from UserServiceImpl.class while Sending signup verification mail");
		}
	}

	/* Function to retrieve user by email id */
	@Override
	public User fetchByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	/* Function to verify the user account & setting status to true */
	@Override
	public boolean verifyUser(String email, String signupVerificationKey) {
		User userInDB = userRepo.findByEmailAndSignupVerificationKey(email, signupVerificationKey);
		if (userInDB == null)
			return false;
		userInDB.setSignupVerificationKey(null);
		userInDB.setStatus(true);
		userRepo.save(userInDB);
		return true;
	}

	/* Function to send Rest Password link to user's email */
	@Override
	public void sendForgotPasswordEmail(String email) {
		User userInDB = userRepo.findByEmail(email);
		if (userInDB == null)
			return;

		String fpvkey = utils.getRandomToken() + utils.getRandomToken();
		userInDB.setResetPasswordVerificationKey(fpvkey);
		userInDB.setResetPasswordReqDateTime(new Date());

		if (utils.sendForgotPasswordEmail(email, fpvkey))
			userRepo.save(userInDB);
	}

	/* Function to verify user's account reset Password Link */
	@Override
	public boolean verifyResetPasswordLink(String email, String resetPasswordVerificationKey) {
		User userInDB = userRepo.findByEmailAndResetPasswordVerificationKey(email, resetPasswordVerificationKey);
		if (userInDB == null)
			return false;
		return true;
	}

	/* Function to reset Password of a user account */
	@Override
	public boolean resetPassword(String email, String resetPasswordVerificationKey, String newPassword) {
		User userInDB = userRepo.findByEmailAndResetPasswordVerificationKey(email, resetPasswordVerificationKey);
		/* If user is not found or ResetPasswordLink exceeds 24 hours */
		if (userInDB == null)
			return false;
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		if (!fmt.format(userInDB.getResetPasswordReqDateTime()).equals(fmt.format(new Date())))
			return false;

		String newEncodedPassword = passwordEncoder.encode(newPassword);
		userInDB.setPassword(newEncodedPassword);
		userInDB.setResetPasswordVerificationKey(null);
		userInDB.setResetPasswordReqDateTime(null);

		userRepo.save(userInDB);
		return true;
	}

}
