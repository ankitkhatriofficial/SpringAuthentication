package com.SpringAuthentication.model.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author Ankit Khatri
 */
public class ResetPasswordDto {

	@NotBlank(message = "Password can not be empty..!")
	private String newPassword;
	@NotBlank(message = "Confirm Password can not be empty..!")
	private String confirmNewPassword;
	@NotBlank(message = "Invalid Input, email does not match..!")
	private String email;
	@NotBlank(message = "Invalid Input, rpvkey does not match..!")
	private String rpvkey; // resetPasswordVerificationKey

	/**
	 * @param email
	 * @param rpvkey
	 */
	public ResetPasswordDto(String email, String rpvkey) {
		super();
		this.email = email;
		this.rpvkey = rpvkey;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the confirmNewPassword
	 */
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	/**
	 * @param confirmNewPassword the confirmNewPassword to set
	 */
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
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
	 * @return the rpvkey
	 */
	public String getRpvkey() {
		return rpvkey;
	}

	/**
	 * @param rpvkey the rpvkey to set
	 */
	public void setRpvkey(String rpvkey) {
		this.rpvkey = rpvkey;
	}

	@Override
	public String toString() {
		return "ResetPasswordDto [newPassword=" + newPassword + ", confirmNewPassword=" + confirmNewPassword
				+ ", email=" + email + ", rpvkey=" + rpvkey + "]";
	}

}
