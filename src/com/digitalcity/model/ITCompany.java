package com.digitalcity.model;

public class ITCompany {
	
	private int id;
	private String comanyName;
	private String physicalAddress;
	private String telephoneNumber;
	private String website;
	private String email;
	
	public ITCompany() {
		
	}

	public ITCompany(String comanyName, String physicalAddress, String telephoneNumber, String website, String email) {
		super();
		this.comanyName = comanyName;
		this.physicalAddress = physicalAddress;
		this.telephoneNumber = telephoneNumber;
		this.website = website;
		this.email = email;
	}

	public ITCompany(int id, String comanyName, String physicalAddress, String telephoneNumber, String website,
			String email) {
		super();
		this.id = id;
		this.comanyName = comanyName;
		this.physicalAddress = physicalAddress;
		this.telephoneNumber = telephoneNumber;
		this.website = website;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComanyName() {
		return comanyName;
	}

	public void setComanyName(String comanyName) {
		this.comanyName = comanyName;
	}

	public String getPhysicalAddress() {
		return physicalAddress;
	}

	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
