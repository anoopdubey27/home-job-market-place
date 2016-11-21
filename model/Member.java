package homeJobMarketplace.model;

public class Member {
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;
	private String address;
	private Type tp;
	private String password;
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public enum Type {
		SEEKER, SITTER ,ADMIN
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getAddress() {
		Address addressOb=new Address();
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Type getTp() {
		return tp;
	}

	public void setTp(Type tp) {
		this.tp = tp;
	}
	
	public Member(){
		
	}
	
	Member(int id,String firstName,String lastName,String phoneNumber,String emailAddress,String password,String address,Type tp,int status){
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.phoneNumber=phoneNumber;
		this.emailAddress=emailAddress;
		this.password=password;
		this.address=address;
		this.tp=tp;
		this.status=status;
	}
}
