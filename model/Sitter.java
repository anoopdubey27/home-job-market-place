package homeJobMarketplace.model;

import homeJobMarketplace.model.Member.Type;

public class Sitter extends Member {
	private int yearOfExperience;
	private int expectedPay;

	public int getYearOfExperience() {
		return yearOfExperience;
	}

	public void setYearOfExperience(int yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}

	public int getExpectedPay() {
		return expectedPay;
	}

	public void setExpectedPay(int expectedPay) {
		this.expectedPay = expectedPay;
	}
	
	@Override
	public String toString() {
		return "Member [memberId=" + getId() + ", firstName=" + getFirstName()
				+ ", lastName=" + getLastName() + ", phoneNumber=" + getPhoneNumber()
				+ ", emailId=" + getEmailAddress() + ", address="+ getAddress()+",year Of Experience="+getYearOfExperience()+",Expected Pay="+getExpectedPay()+", Type=Sitter ]";
	}
	
	public Sitter(){
		
	}
	
	public Sitter(int id,String firstName,String lastName,String phoneNumber,String emailAddress,String password,String address,Type tp,int status,int yearOfExperience,int expectedPay){
		super(id,firstName,lastName,phoneNumber,emailAddress,password,address,tp,status);
		this.yearOfExperience=yearOfExperience;
		this.expectedPay=expectedPay;
	}
}