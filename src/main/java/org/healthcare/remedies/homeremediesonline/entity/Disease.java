package org.healthcare.remedies.homeremediesonline.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "disease_details")
public class Disease implements Serializable{
	
	private static final long serialVersionUID = -1000119078147252957L;

	private String dcode;
	private String name;
	private String remedies;
	private Date createDate;
	private String pcode;
	
	
	public Disease() {
		
	}
	@Id
    @Column(name = "dcode", length = 20, nullable = false)
	public String getDcode() {
		return dcode;
	}
	public void setDcode(String dcode) {
		this.dcode = dcode;
	}
	@Column(name = "name", length = 255, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "remedies", length = 255, nullable = false)
	public String getRemedies() {
		return remedies;
	}
	public void setRemedies(String remedies) {
		this.remedies = remedies;
	}
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name = "pcode", length = 20, nullable = false)
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public Disease(String dcode, String name, String remedies, Date createDate, String pcode) {
		super();
		this.dcode = dcode;
		this.name = name;
		this.remedies = remedies;
		this.createDate = createDate;
		this.pcode = pcode;
	}
	
	
	
	
}
