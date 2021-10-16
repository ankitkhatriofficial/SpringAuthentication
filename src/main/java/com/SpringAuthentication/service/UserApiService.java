package com.SpringAuthentication.service;

import com.SpringAuthentication.model.dto.UserRegDto;
import com.SpringAuthentication.model.entity.User;

/**
 * @author Ankit Khatri
 */
public interface UserApiService {

	/**
	 * @param userDto
	 */
	public void save(UserRegDto user);

	/**
	 * @param userId
	 * @return @User
	 */
	public User fetch(String id);

	/**
	 * @param email
	 * @return @User
	 */
	public User fetchByEmail(String email);

	/**
	 * @param email
	 * @param signupVerificationKey
	 * @return true if user account is verified by registration Link
	 */
	public boolean verifyUser(String email, String signupVerificationKey);

	/**
	 * @param email
	 */
	public void sendForgotPasswordEmail(String email);

	/**
	 * @param email
	 * @param resetPasswordVerificationKey
	 * @return true if user password is reseted by reset password Link
	 */
	public boolean verifyResetPasswordLink(String email, String resetPasswordVerificationKey);

	/**
	 * @param email
	 * @param resetPasswordVerificationKey
	 * @param newPassword
	 * @return true if password is successfully reseted
	 */
	public boolean resetPassword(String email, String resetPasswordVerificationKey, String newPassword);

}
