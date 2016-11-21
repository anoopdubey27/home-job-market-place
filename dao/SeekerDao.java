package homeJobMarketplace.dao;

import homeJobMarketplace.AppContext;
import homeJobMarketplace.DatabaseConnect;
import homeJobMarketplace.model.Member;
import homeJobMarketplace.model.Seeker;
import homeJobMarketplace.model.Sitter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SeekerDao {
	public void createSeekerDao(Seeker seekerOb) throws SQLException {		
		AppContext.getMemberDaoInstance().createMemberDao(seekerOb);
		Connection con = DatabaseConnect.getConnection();
		Statement stmt = con.createStatement();
		String query="INSERT INTO SEEKER(SEEKER_ID,NOC_FAMILY,SPOUSE_NAME) VALUES ("+seekerOb.getId()+","+seekerOb.getNocFamily()+",'"+seekerOb.getSpouseName()+"')";
      	int i = stmt.executeUpdate(query);
      	DatabaseConnect.closeConnection();
	}

	public boolean loginSeekerDao(String emailId, String password) throws SQLException {
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
			String query1="SELECT * FROM MEMBER JOIN SEEKER ON MEMBER.ID=SEEKER.SEEKER_ID WHERE EMAIL_ADDRESS='"+emailId+"'" ;
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(query1);
			Seeker seekerOb=null;
            while(rs1.next()){
    			   seekerOb= new Seeker(rs1.getInt("ID"),rs1.getString("FIRST_NAME"),rs1.getString("LAST_NAME"),rs1.getString("PHONE_NUMBER"),
    			        rs1.getString("EMAIL_ADDRESS"),rs1.getString("PASSWORD"),rs1.getString("ADDRESS")
    					,Member.Type.SEEKER,1,rs1.getInt("NOC_FAMILY"),rs1.getString("SPOUSE_NAME"));
            }
            stmt1.close();
			AppContext.setMemberInstance(seekerOb);
			DatabaseConnect.closeConnection();
			return true;
		}
		else {
			DatabaseConnect.closeConnection(); 
			return false;
		}
   }

	public void deleteSeekerDao() throws SQLException {
		Connection con = DatabaseConnect.getConnection();
    	Statement stmt = con.createStatement();
    	String query="UPDATE MEMBER SET MEMBER.STATUS=0 WHERE ID="+AppContext.getSeekerInstance().getId();
    	int i = stmt.executeUpdate(query);
		stmt.close();
		Statement stmt1 = con.createStatement();
    	String query1="UPDATE JOB SET JOB.STATUS=0 WHERE POSTED_BY="+AppContext.getSeekerInstance().getId();
        i = stmt1.executeUpdate(query1);
        stmt1.close();
        Statement stmt2 = con.createStatement();
    	String query2="UPDATE JOB JOIN APPLICATION ON JOB.JOB_ID=APPLICATION.JOB_ID SET APPLICATION.STATUS=0 WHERE POSTED_BY="+AppContext.getSeekerInstance().getId();
        i = stmt2.executeUpdate(query2);
        stmt2.close();
		DatabaseConnect.closeConnection();
	}
	
	public void updateSeekerDetailDao(Seeker seekerOb) throws SQLException {
		AppContext.getMemberDaoInstance().updateMemberDao(seekerOb);
		Connection con = DatabaseConnect.getConnection();
		Statement stmt = con.createStatement();
		String query="UPDATE SEEKER SET NOC_FAMILY="+seekerOb.getNocFamily()+",SPOUSE_NAME='"+seekerOb.getSpouseName()+"'WHERE SEEKER_ID="+seekerOb.getId();
      	int i = stmt.executeUpdate(query);
      	AppContext.setMemberInstance(seekerOb);
      	DatabaseConnect.closeConnection();
	}

	public ArrayList<Seeker> listSeekerDao() throws SQLException {
		Connection con = DatabaseConnect.getConnection();
    	Statement stmt = con.createStatement();
    	Seeker seekerOb;
    	String query="SELECT * FROM MEMBER WHERE STATUS=1 AND TYPE='SEEKER'";
    	ResultSet rs = stmt.executeQuery(query);
    	ArrayList<Seeker> al=new ArrayList<Seeker>();
    	while (rs.next()) {
    		seekerOb = new Seeker();
    		seekerOb.setId(rs.getInt("ID"));
    		seekerOb.setFirstName(rs.getString("FIRST_NAME"));
    		seekerOb.setLastName(rs.getString("LAST_NAME"));
    		seekerOb.setPhoneNumber(rs.getString("PHONE_NUMBER"));
    		seekerOb.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
    		seekerOb.setAddress(rs.getString("ADDRESS"));
    		Statement stmt1 = con.createStatement();
    		String query1="SELECT * FROM SEEKER WHERE SEEKER_ID="+seekerOb.getId();
        	ResultSet rs1 = stmt1.executeQuery(query1);
        	while(rs1.next()){
        		seekerOb.setNocFamily(rs1.getInt("NOC_FAMILY"));
        		seekerOb.setSpouseName(rs1.getString("SPOUSE_NAME"));
        	}
        	stmt1.close();
    		al.add(seekerOb);
    	}
    	stmt.close();
		DatabaseConnect.closeConnection();
		return al;
	}
}
