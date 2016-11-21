package homeJobMarketplace.model;

public class Address{
	//private int memberId;
	private String street1;
	private String street2;
	private String country;
	private String state;
	private int pincode;
	public Address()
	{
		
	}
	public void setStreet1(String street1) {
		this.street1 =street1;
	}
	public void setStreet2(String street2) {
		this.street2 =street2;
	}
	public void setCountry(String country) {
		this.country =country;
	}
	public void setState(String state) {
		this.state =state;
	}
	public void setPincode(int pincode) {
		this.pincode =pincode;
	}
	public String getStreet1() {
		return street1;
	}
	public String getStreet2() {
		return street2;
	}
	public String getCountry() {
	              return country;
	}
	public String getState() {
		return state;
	}
	public int getPincode() {
		return pincode;
	}
	public String getAddress(){
		String add=(getStreet1()+" "+getStreet2()+" "+getState());
		return add;
	}
	
	

	public Address(String street1 ,String street2, String country,String state,int pincode){
		this.street1 =street1;
		this.street2 =street2;
		this.country =country;
		this.state =state;
		this.pincode =pincode;
	}
	public String toString() {
		return "Address [Street1=" + getStreet1() + ", Street2=" + getStreet1()
				+ ", Country=" + getCountry() + ", PinCode=" + getPincode()
				+ ", State=" + getState();
	}
	/*public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}*/
}
