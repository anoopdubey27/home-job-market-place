package homeJobMarketplace.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import homeJobMarketplace.AppContext;
import homeJobMarketplace.DatabaseConnect;
import homeJobMarketplace.model.Address;
import homeJobMarketplace.model.Member;


public class AddressDao{
	public void createAddressDao(Address addressOb,Member memberOb) throws SQLException {		
		Connection con = DatabaseConnect.getConnection();
		Statement stmt = con.createStatement();
		String query="INSERT INTO ADDRESS(STREET1,STREET2,COUNTRY,PINCODE,STATE) VALUES ('"+addressOb.getStreet1()+"','"+addressOb.getStreet2()+"','"+addressOb.getCountry()+"',"+addressOb.getPincode()+",'"+addressOb.getState()+"','"+memberOb.getId()+"')";
      	int i = stmt.executeUpdate(query);
      	DatabaseConnect.closeConnection();
	
	}
	
	public void updateAddressDao(Address addressOb,Member memberOb)throws SQLException{
		Connection con = DatabaseConnect.getConnection();
    	Statement stmt = con.createStatement();
    	String query="UPDATE ADDRESS "
    			+ "SET STREET1='" + addressOb.getStreet1()+"',"
    			+ "Street2='"+addressOb.getStreet2()+"',"
    			+ "Country='"+addressOb.getCountry()+"',"
    			+ "Pincode="+addressOb.getPincode()+","
    			+ "State="+addressOb.getState()+","
    			+ "PASSWORD='"+memberOb.getPassword()+"',"
    			+ "ADDRESS='"+memberOb.getAddress()+"'WHERE EMAIL_ADDRESS='"+memberOb.getEmailAddress()+"'";
    	int i = stmt.executeUpdate(query);
		stmt.close();
		DatabaseConnect.closeConnection();
    	}
}
