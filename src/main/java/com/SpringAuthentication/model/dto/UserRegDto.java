package com.SpringAuthentication.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Ankit Khatri
 */
/* User DTO (Data Transfer Object) */
public class UserRegDto {

	@NotBlank(message = "First Name cannot be empty..!")
	private String firstName;

	@NotBlank(message = "Last Name cannot be empty..!")
	private String lastName;

	@Email(message = "Invalid email..!")
	@NotBlank(message = "Email cannot be empty..!")
	private String email;

	@Pattern(regexp = "[6-9][0-9]{9}", message = "Invalid contact number..!")
	@NotBlank(message = "ContactNo cannot be empty..!")
	private String contactNo;

	@NotBlank(message = "Password cannot be empty..!")
	private String password;

	@NotBlank(message = "Confirm Password cannot be empty..!")
	private String confirmPassword;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@Override
	public String toString() {
		return "UserRegDto [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", contactNo="
				+ contactNo + ", password=" + password + ", confirmPassword=" + confirmPassword + "]";
	}

}
