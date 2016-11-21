package homeJobMarketplace.model;

import homeJobMarketplace.model.Member.Type;

public class Seeker extends Member {
	private int nocFamily;
	private String spouseName;

	public int getNocFamily() {
		return nocFamily;
	}

	public void setNocFamily(int nocFamily) {
		this.nocFamily = nocFamily;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	
	@Override
	public String toString() {
		return "Member [memberId=" + getId() + ", firstName=" + getFirstName()
				+ ", lastName=" + getLastName() + ", phoneNumber=" + getPhoneNumber()
				+ ", emailId=" + getEmailAddress() + ", type=" + getTp() + ", address="
				+ getAddress() +"Seeker [noOfChildren=" + nocFamily + ", spouseName="
						+ spouseName +", Type=Seeker ]";
	}
	
	public Seeker(){
		
	}
	
	public Seeker(int id,String firstName,String lastName,String phoneNumber,String emailAddress,String password,String address,Type tp,int status,int nocFamily,String spouseName){
		super(id,firstName,lastName,phoneNumber,emailAddress,password,address,tp,status);
		this.nocFamily=nocFamily;
		this.spouseName=spouseName;
	}
}