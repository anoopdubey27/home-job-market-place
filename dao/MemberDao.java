package homeJobMarketplace.dao;

import homeJobMarketplace.DatabaseConnect;
import homeJobMarketplace.model.Address;
import homeJobMarketplace.model.Member;
import homeJobMarketplace.model.Seeker;
import homeJobMarketplace.model.Sitter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberDao {
    public Member searchMemberDao(String emailId) throws SQLException{
    	Connection con = DatabaseConnect.getConnection();
    	Statement stmt = con.createStatement();
    	String query="SELECT TYPE FROM MEMBER WHERE EMAIL_ADDRESS='"+emailId+"'";
    	ResultSet rs = stmt.executeQuery(query);
    	if (rs.next()) {
    	   if(rs.getString("TYPE").equals("SITTER")){
    		   Sitter sitterOb=new Sitter();
    		   Statement stmt1 = con.createStatement();
    	       String query1="SELECT * FROM MEMBER INNER JOIN SITTER ON MEMBER.ID=SITTER.SITTER_ID where EMAIL_ADDRESS='"+emailId+"'";
    	       ResultSet rs1 = stmt1.executeQuery(query1);
    	       if(rs1.next()){
    	    	sitterOb.setId(rs1.getInt("ID"));
               	sitterOb.setFirstName(rs1.getString("FIRST_NAME"));
               	sitterOb.setLastName(rs1.getString("LAST_NAME"));
               	sitterOb.setPhoneNumber(rs1.getString("PHONE_NUMBER"));
               	sitterOb.setEmailAddress(rs1.getString("EMAIL_ADDRESS"));
               	sitterOb.setAddress(rs1.getString("ADDRESS"));
               	sitterOb.setTp(Member.Type.SITTER);
               	sitterOb.setPassword(rs1.getString("PASSWORD"));
               	sitterOb.setYearOfExperience(rs1.getInt("YEAR_OF_EXPERIENCE"));
				sitterOb.setExpectedPay(rs1.getInt("EXPECTED_PAY"));
				stmt1.close();
				return sitterOb;
    	       }
    	   }
    	   if(rs.getString("TYPE").equals("SEEKER")){
    		   Seeker seekerOb=new Seeker();
    		   Statement stmt2 = con.createStatement();
    	       String query2="SELECT * FROM MEMBER INNER JOIN SEEKER ON MEMBER.ID=SEEKER.SEEKER_ID where EMAIL_ADDRESS='"+emailId+"'";
    	       ResultSet rs2 = stmt2.executeQuery(query2);
    	       if(rs2.next()){
    	    	seekerOb.setId(rs2.getInt("ID"));
               	seekerOb.setFirstName(rs2.getString("FIRST_NAME"));
               	seekerOb.setLastName(rs2.getString("LAST_NAME"));
               	seekerOb.setPhoneNumber(rs2.getString("PHONE_NUMBER"));
               	seekerOb.setEmailAddress(rs2.getString("EMAIL_ADDRESS"));
               	seekerOb.setAddress(rs2.getString("ADDRESS"));
               	seekerOb.setTp(Member.Type.SEEKER);
               	seekerOb.setPassword(rs2.getString("PASSWORD"));
               	seekerOb.setNocFamily(rs2.getInt("NOC_FAMILY"));
            	seekerOb.setSpouseName(rs2.getString("SPOUSE_NAME"));
            	stmt2.close();
            	return seekerOb;
    	       }
    	   }
    	}
    	else 
    	   {
    		System.out.println("Entered Email Id does not exist");
    		Member memberOb=null;
    		return memberOb;
    	   }	
    	return null;
	}
     
	public void createMemberDao(Member memberOb)throws SQLException{
    	Connection con = DatabaseConnect.getConnection();
    	Statement stmt = con.createStatement();
    	String query="INSERT INTO MEMBER(FIRST_NAME,LAST_NAME,PHONE_NUMBER,EMAIL_ADDRESS,PASSWORD,ADDRESS,TYPE) "
    			+ "VALUES ('" + memberOb.getFirstName()+"',"
    			+ "'"+memberOb.getLastName()+"',"
    			+ "'"+memberOb.getPhoneNumber()+"',"
    			+ "'"+memberOb.getEmailAddress()+"',"
    			+ "'"+memberOb.getPassword()+"',"
    			+ "'"+memberOb.getAddress()+"',"
    			+ "'"+memberOb.getTp().name()+"')";
    	int i = stmt.executeUpdate(query);
    	String query1="SELECT ID FROM MEMBER WHERE EMAIL_ADDRESS='"+memberOb.getEmailAddress()+"'";
    	Statement stmt1 = con.createStatement();
    	ResultSet rs = stmt1.executeQuery(query1);
    	if (rs.next()) {
    		int id = rs.getInt("ID");
    		memberOb.setId(id);
    	}
		stmt.close();
		stmt1.close();
		DatabaseConnect.closeConnection();
    	}
	
	public boolean checkUniqueEmail(String emailId) throws SQLException{
		Connection con = DatabaseConnect.getConnection();
    	Statement stmt = con.createStatement();
    	String query="SELECT EMAIL_ADDRESS FROM MEMBER WHERE EMAIL_ADDRESS='"+emailId+"'";
    	ResultSet rs = stmt.executeQuery(query);
    	if (rs.next()) {
    		return true;
    	}
    	return false;
	}
	
	public void updateMemberDao(Member memberOb) throws SQLException{
		Connection con = DatabaseConnect.getConnection();
    	Statement stmt = con.createStatement();
    	String query="UPDATE MEMBER "
    			+ "SET FIRST_NAME='" + memberOb.getFirstName()+"',"
    			+ "LAST_NAME='"+memberOb.getLastName()+"',"
    			+ "PHONE_NUMBER='"+memberOb.getPhoneNumber()+"',"
    			+ "EMAIL_ADDRESS='"+memberOb.getEmailAddress()+"',"
    			+ "PASSWORD='"+memberOb.getPassword()+"',"
    			+ "ADDRESS='"+memberOb.getAddress()+"'WHERE EMAIL_ADDRESS='"+memberOb.getEmailAddress()+"'";
    	int i = stmt.executeUpdate(query);
		stmt.close();
		DatabaseConnect.closeConnection();
    	}

	public void createAddressDao(Address addressOb) {
		
		
	}
}
