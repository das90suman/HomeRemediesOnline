package org.healthcare.remedies.homeremediesonline.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "User_Details")
public class SignUp implements Serializable {
	
	private static final long serialVersionUID = -1000119078147252957L;
	private String fname;
	private String lname;
	private String email;
	private String contact;
	private String passwd;
	private String cpasswd;
	private Date createDate;
	
	
	private String captcha;
	
	
	public SignUp() {
	}

	

	public SignUp(String fname, String lname, String email, String contact, String passwd, String cpasswd,
			Date createDate) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.contact = contact;
		this.passwd = passwd;
		this.cpasswd = cpasswd;
		this.createDate = createDate;
	}


	@Column(name = "Fname", length = 255, nullable = false)
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	 @Column(name = "Lname", length = 255, nullable = false)
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@Id
	@Column(name = "Email", length = 255, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Contact", length = 20, nullable = false)
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "Password", length = 255, nullable = false)
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Column(name = "CPassword", length = 255, nullable = false)
	public String getCpasswd() {
		return cpasswd;
	}

	public void setCpasswd(String cpasswd) {
		this.cpasswd = cpasswd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Create_Date", nullable = false)
	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	@Transient
	public String getCaptcha() {
		return captcha;
	}



	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}


}
