package homeJobMarketplace.dao;

import homeJobMarketplace.AppContext;
import homeJobMarketplace.DatabaseConnect;
import homeJobMarketplace.model.Member;
import homeJobMarketplace.model.Sitter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDao {
	public boolean loginAdminDao(String emailId, String password) throws SQLException {
		Connection con = DatabaseConnect.getConnection();
		Statement stmt = con.createStatement();
		String query="SELECT EMAIL_ADDRESS,PASSWORD FROM MEMBER WHERE EMAIL_ADDRESS='"+emailId+"' AND TYPE='ADMIN'" ;
    	ResultSet rs = stmt.executeQuery(query);
    	String emailId1=null;;
    	String password1=null;
    	while(rs.next()){
    		emailId1=rs.getString("EMAIL_ADDRESS");
    		password1=rs.getString("PASSWORD");
    	}
    	stmt.close();
		if(emailId.equals(emailId1) && password.equals(password1)){
			//AppContext.setMemberInstance(memberOb);
			String query1="SELECT * FROM MEMBER WHERE EMAIL_ADDRESS='"+emailId+"'" ;
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(query1);
			Member memberOb=new Member();
            while(rs1.next()){
            	memberOb.setId(rs1.getInt("ID"));
            	memberOb.setFirstName(rs1.getString("FIRST_NAME"));
            	memberOb.setLastName(rs1.getString("LAST_NAME"));
            	memberOb.setPhoneNumber(rs1.getString("PHONE_NUMBER"));
            	memberOb.setEmailAddress(rs1.getString("EMAIL_ADDRESS"));
            	memberOb.setAddress(rs1.getString("ADDRESS"));
            	memberOb.setTp(Member.Type.ADMIN);
            	memberOb.setPassword(rs1.getString("PASSWORD"));
            }
            stmt1.close();
            AppContext.setMemberInstance(memberOb);
			DatabaseConnect.closeConnection();
			return true;
		}
		else {
			DatabaseConnect.closeConnection(); 
			return false;
		}
   }

	public void updateAdminDetailDao(Member memberOb) throws SQLException{
		AppContext.getMemberDaoInstance().updateMemberDao(memberOb);
		AppContext.setMemberInstance(memberOb);
	}
}
