package com.digitalcity.model;


public class ProfessionalPortfolio extends Professional {
	
	private String postalAddress;
	private String cellNumber;
	private String dateOfBirth;
	private String country;
	private String profession;
	private String education;
	
	public ProfessionalPortfolio(String firstName, String lastName, String email, String postalAddress2, int cellNumber2, String dob, String country2, String profession2, String education2) {
		
		super();
		
	}
	public ProfessionalPortfolio(int id, String firstName, String lastName, String email, String postalAddress, String cellNumber, String dateOfBirth, String country,
			String profession, String education) {
		
		super(id, firstName, lastName, email);
		this.postalAddress = postalAddress;
		this.cellNumber = cellNumber;
		this.dateOfBirth = dateOfBirth;
		this.country = country;
		this.profession = profession;
		this.education = education;
		
	}
	public ProfessionalPortfolio(String firstName, String lastName, String email, String postalAddress, String cellNumber, String dateOfBirth, String country,
			String profession, String education) {
		
		super(firstName, lastName, email);
		this.postalAddress = postalAddress;
		this.cellNumber = cellNumber;
		this.dateOfBirth = dateOfBirth;
		this.country = country;
		this.profession = profession;
		this.education = education;
		
	}
	
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	public String getCellNumber() {
		return cellNumber;
	}
	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}

	
}
