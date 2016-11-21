package homeJobMarketplace.dao;

import homeJobMarketplace.AppContext;
import homeJobMarketplace.DatabaseConnect;
import homeJobMarketplace.model.Member;
import homeJobMarketplace.model.Sitter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SitterDao {
	public void createSitterDao(Sitter sitterOb) throws SQLException{
		AppContext.getMemberDaoInstance().createMemberDao(sitterOb);
		Connection con=DatabaseConnect.getConnection();
		Statement stmt = con.createStatement();
		String query="INSERT INTO SITTER(SITTER_ID,YEAR_OF_EXPERIENCE,EXPECTED_PAY) VALUES ("+sitterOb.getId()+","+sitterOb.getYearOfExperience()+","+sitterOb.getExpectedPay()+")";
      	int i = stmt.executeUpdate(query);
      	DatabaseConnect.closeConnection();
	}
/*
	public void deleteSitterDao(String emailId, String password) {

	}
*/	
	public void updateSitterDetailDao(Sitter sitterOb) throws SQLException {
		AppContext.getMemberDaoInstance().updateMemberDao(sitterOb);
		Connection con = DatabaseConnect.getConnection();
		Statement stmt = con.createStatement();
		String query="UPDATE SITTER SET YEAR_OF_EXPERIENCE="+sitterOb.getYearOfExperience()+",EXPECTED_PAY="+sitterOb.getExpectedPay()+" WHERE SITTER_ID="+sitterOb.getId();
      	int i = stmt.executeUpdate(query);
      	AppContext.setMemberInstance(sitterOb);
      	DatabaseConnect.closeConnection();
	}

	public boolean loginSitterDao(String emailId, String password) throws SQLException {
		Connection con = DatabaseConnect.getConnection();
		Statement stmt = con.createStatement();
		String query="SELECT EMAIL_ADDRESS,PASSWORD FROM MEMBER WHERE EMAIL_ADDRESS='"+emailId+"'" ;
    	ResultSet rs = stmt.executeQuery(query);
    	String emailId1=null;;
    	String password1=null;
    	while(rs.next()){
    		emailId1=rs.getString("EMAIL_ADDRESS");
    		password1=rs.getString("PASSWORD");
    	}
    	stmt.close();
		if(emailId.equals(emailId1) && password.equals(password1)){
			String query1="SELECT * FROM MEMBER JOIN SITTER ON MEMBER.ID=SITTER.SITTER_ID WHERE EMAIL_ADDRESS='"+emailId+"'" ;
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(query1);
			Sitter sitterOb=null;
            while(rs1.next()){
            	sitterOb=new Sitter(rs1.getInt("ID"),rs1.getString("FIRST_NAME"),rs1.getString("LAST_NAME"),rs1.getString("PHONE_NUMBER")
            			,rs1.getString("EMAIL_ADDRESS"),rs1.getString("PASSWORD"),rs1.getString("ADDRESS"),Member.Type.SITTER,1
            			,rs1.getInt("YEAR_OF_EXPERIENCE"),rs1.getInt("EXPECTED_PAY"));
            }
            stmt1.close();
            AppContext.setMemberInstance(sitterOb);
			DatabaseConnect.closeConnection();
			return true;
		}
		else {
			DatabaseConnect.closeConnection(); 
			return false;
		}
   }
	
	public ArrayList<Sitter> listSitterDao() throws SQLException {
		Connection con = DatabaseConnect.getConnection();
    	Statement stmt = con.createStatement();
    	Sitter sitterOb;
    	String query="SELECT * FROM MEMBER WHERE STATUS=1 AND TYPE='SITTER'";
    	ResultSet rs = stmt.executeQuery(query);
    	ArrayList<Sitter> al=new ArrayList<Sitter>();
    	while (rs.next()) {
    		sitterOb = new Sitter();
    		sitterOb.setId(rs.getInt("ID"));
    		sitterOb.setFirstName(rs.getString("FIRST_NAME"));
    		sitterOb.setLastName(rs.getString("LAST_NAME"));
    		sitterOb.setPhoneNumber(rs.getString("PHONE_NUMBER"));
    		sitterOb.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
    		sitterOb.setAddress(rs.getString("ADDRESS"));
    		Statement stmt1 = con.createStatement();
    		String query1="SELECT * FROM SITTER WHERE SITTER_ID="+sitterOb.getId();
        	ResultSet rs1 = stmt1.executeQuery(query1);
        	while(rs1.next()){
        		sitterOb.setYearOfExperience(rs1.getInt("YEAR_OF_EXPERIENCE"));
        		sitterOb.setExpectedPay(rs1.getInt("EXPECTED_PAY"));
        	}
        	stmt1.close();
    		al.add(sitterOb);
    	}
    	stmt.close();
		DatabaseConnect.closeConnection();
		return al;
	}
	
	public void deleteSitterDao() throws SQLException {
		Connection con = DatabaseConnect.getConnection();
    	Statement stmt = con.createStatement();
    	String query="UPDATE MEMBER SET MEMBER.STATUS=0 WHERE ID="+AppContext.getSitterInstance().getId();
    	int i = stmt.executeUpdate(query);
		stmt.close();
        Statement stmt2 = con.createStatement();
    	String query2="UPDATE SITTER JOIN APPLICATION ON SITTER.SITTER_ID=APPLICATION.SITTER_ID SET APPLICATION.STATUS=0 WHERE SITTER.SITTER_ID="+AppContext.getSitterInstance().getId();
        i = stmt2.executeUpdate(query2);
        stmt2.close();
		DatabaseConnect.closeConnection();
	}
}
