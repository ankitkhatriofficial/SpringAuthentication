package com.SpringAuthentication.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Ankit Khatri
 */

/* User Entity class */
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "email", name = "email"),
		@UniqueConstraint(columnNames = "contactNo", name = "contactNo") })
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@NotBlank(message = "First Name cannot be empty..!")
	private String firstName;

	@NotBlank(message = "Last Name cannot be empty..!")
	private String lastName;

	@Email(message = "Invalid email..!")
	@NotBlank(message = "Email cannot be empty..!")
	private String email;

	@Pattern(regexp = "[6-9][0-9]{9}", message = "Invalid contact Number..!")
	@NotBlank(message = "ContactNo cannot be empty..!")
	private String contactNo;

	private String password;

	private Date signupDateTime;

	private boolean status;

	private String signupVerificationKey;

	private String resetPasswordVerificationKey;

	private Date resetPasswordReqDateTime;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

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

	/**
	 * @return the signupDateTime
	 */
	public Date getSignupDateTime() {
		return signupDateTime;
	}

	/**
	 * @param signupDateTime the signupDateTime to set
	 */
	public void setSignupDateTime(Date signupDateTime) {
		this.signupDateTime = signupDateTime;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the signupVerificationKey
	 */
	public String getSignupVerificationKey() {
		return signupVerificationKey;
	}

	/**
	 * @param signupVerificationKey the signupVerificationKey to set
	 */
	public void setSignupVerificationKey(String signupVerificationKey) {
		this.signupVerificationKey = signupVerificationKey;
	}

	/**
	 * @return the resetPasswordVerificationKey
	 */
	public String getResetPasswordVerificationKey() {
		return resetPasswordVerificationKey;
	}

	/**
	 * @param resetPasswordVerificationKey the resetPasswordVerificationKey to set
	 */
	public void setResetPasswordVerificationKey(String resetPasswordVerificationKey) {
		this.resetPasswordVerificationKey = resetPasswordVerificationKey;
	}

	/**
	 * @return the resetPasswordReqDateTime
	 */
	public Date getResetPasswordReqDateTime() {
		return resetPasswordReqDateTime;
	}

	/**
	 * @param resetPasswordReqDateTime the resetPasswordReqDateTime to set
	 */
	public void setResetPasswordReqDateTime(Date resetPasswordReqDateTime) {
		this.resetPasswordReqDateTime = resetPasswordReqDateTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNo=" + contactNo + ", password=" + password + ", signupDateTime=" + signupDateTime
				+ ", status=" + status + ", signupVerificationKey=" + signupVerificationKey
				+ ", resetPasswordVerificationKey=" + resetPasswordVerificationKey + ", resetPasswordReqDateTime="
				+ resetPasswordReqDateTime + "]";
	}

}
