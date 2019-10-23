package com.inderjit.portal.signon.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "Signon")
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Signon implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQUENCE1", sequenceName = "SEQUENCE1", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE1")
	private long id;

	@ApiModelProperty(notes = "First Name of the User", name = "firstName", required = false, value = "Test Firstname")
	@NotEmpty(message = "first name cannot be empty")
	@Column(name = "first_name")
	private String firstName;

	@ApiModelProperty(notes = "Last Name of the User", name = "lastName", required = false, value = "Test Firstname")
	@NotEmpty(message = "last name cannot be empty")
	@Column(name = "last_name")
	private String lastName;

	@ApiModelProperty(notes = "Email of the User", name = "email", required = false, value = "Test Firstname")
	@NotEmpty(message = "email cannot be empty")
	@Email(message = "email should be valid email")
	@Column(name = "email", nullable = false, length = 200)
	private String email;

	@ApiModelProperty(notes = "Mobile Number of the User", name = "mobileNo", required = false, value = "Test Firstname")
	@Column(name = "mobileNo", nullable = false, length = 15)
	private String mobileNo;

	@ApiModelProperty(notes = "Login Name of the User", name = "signOn", required = false, value = "Test Firstname")
	@NotEmpty(message = "login name cannot be empty")
	@Column(name = "signon_name", nullable = false, length = 25)
	private String signOn;

	@ApiModelProperty(notes = "Password of the User", name = "signonPassword", required = false, value = "Test Firstname")
	@NotEmpty(message = "password cannot be empty")
	@Column(name = "signon_password", nullable = false, length = 60)
	private String signonPassword;

	@ApiModelProperty(notes = "Role of the User", name = "signonRole", required = false, value = "Test Firstname")
	@Column(name = "signon_role")
	private String signonRole;

	@ApiModelProperty(notes = "Login Status of the User", name = "signonActive", required = false, value = "Test Firstname")
	@Column(name = "signon_active")
	private String signonActive;


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
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return the signOn
	 */
	public String getSignOn() {
		return signOn;
	}

	/**
	 * @param signOn the signOn to set
	 */
	public void setSignOn(String signOn) {
		this.signOn = signOn;
	}

	/**
	 * @return the signonPassword
	 */
	public String getSignonPassword() {
		return signonPassword;
	}

	/**
	 * @param signonPassword the signonPassword to set
	 */
	public void setSignonPassword(String signonPassword) {
		this.signonPassword = signonPassword;
	}

	/**
	 * @return the signonRole
	 */
	public String getSignonRole() {
		return signonRole;
	}

	/**
	 * @param signonRole the signonRole to set
	 */
	public void setSignonRole(String signonRole) {
		this.signonRole = signonRole;
	}

	/**
	 * @return the signonActive
	 */
	public String getSignonActive() {
		return signonActive;
	}

	/**
	 * @param signonActive the signonActive to set
	 */
	public void setSignonActive(String signonActive) {
		this.signonActive = signonActive;
	}

	@Override
	public String toString() {
		return "Signon [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", signOn="
				+ signOn + ", signonRole=" + signonRole + ", signonActive=" + signonActive + "]";
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public Signon(long id, @NotEmpty(message = "first name cannot be empty") String firstName,
			@NotEmpty(message = "last name cannot be empty") String lastName,
			@NotEmpty(message = "email cannot be empty") @Email(message = "email should be valid email") String email,
			String mobileNo, @NotEmpty(message = "login name cannot be empty") String signOn,
			@NotEmpty(message = "password cannot be empty") String signonPassword, String signonRole,
			String signonActive) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.signOn = signOn;
		this.signonPassword = signonPassword;
		this.signonRole = signonRole;
		this.signonActive = signonActive;
	}

	public Signon() {
		super();
	}
}
