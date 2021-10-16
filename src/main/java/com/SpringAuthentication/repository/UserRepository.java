package com.SpringAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringAuthentication.model.entity.User;

/**
 * @author Ankit Khatri
 */
public interface UserRepository extends JpaRepository<User, String> {

	/**
	 * @param email
	 * @return @User
	 */
	public User findByEmail(String email);

	/**
	 * @param email
	 * @param signupVerificationKey
	 * @return @User
	 */
	public User findByEmailAndSignupVerificationKey(String email, String signupVerificationKey);

	/**
	 * @param email
	 * @param resetPasswordVerificationKey
	 * @return @User
	 */
	public User findByEmailAndResetPasswordVerificationKey(String email, String resetPasswordVerificationKey);

}
