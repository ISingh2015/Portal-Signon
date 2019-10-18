package com.inderjit.portal.signon.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Signon")
public class Signon {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email", nullable=false, length=200)
	private String email;

	@Column(name="mobileNo", nullable=false, length=15)
	private String mobileNo;

	@Column(name="signon_name", nullable=false, length=25)
	private String signOn;
	
	@Column(name="signon_password", nullable=false, length=35)
	private String signonPassword;
	
	@Column(name="signon_role")
	private String signonRole;
	
	@Column(name="signon_active")
	private String signonActive;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
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
		return "Signon [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", signOn=" + signOn
				+ ", signonRole=" + signonRole + ", signonActive=" + signonActive + "]";
	}

	
}
