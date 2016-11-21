package homeJobMarketplace.dao;

import homeJobMarketplace.AppContext;
import homeJobMarketplace.DatabaseConnect;
import homeJobMarketplace.model.Application;
import homeJobMarketplace.model.Seeker;
import homeJobMarketplace.model.Sitter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ApplicationDao {

	public void applyForJobDao(Application applicatioOb) throws SQLException {
		Connection con = DatabaseConnect.getConnection();
    	Statement stmt = con.createStatement();
    	String query="INSERT INTO APPLICATION(JOB_ID,SITTER_ID,EXPECTED_PAY) "
    			+ " VALUES ("+ applicatioOb.getJobId()+" , "
    			+applicatioOb.getMemberId()+" , "
    			+applicatioOb.getExpectedPay()+" )";	
    	int i = stmt.executeUpdate(query);
		stmt.close();
		DatabaseConnect.closeConnection();
	}
	
	public ArrayList<Application> listAllApplicationsDao() throws SQLException{
		Connection con = DatabaseConnect.getConnection();
    	Statement stmt = con.createStatement();
    	Seeker seekerOb=AppContext.getSeekerInstance();
    	Application applicationOb;
    	String query="SELECT APPLICATION.* FROM APPLICATION "
    			+ "JOIN JOB ON APPLICATION.JOB_ID=JOB.JOB_ID "
    			+ "JOIN SEEKER ON JOB.POSTED_BY=SEEKER.SEEKER_ID"
    			+ " WHERE JOB.STATUS=1 AND APPLICATION.STATUS=1 AND SEEKER.SEEKER_ID="+seekerOb.getId()+" GROUP BY JOB.JOB_ID";
    	ResultSet rs = stmt.executeQuery(query);
    	ArrayList<Application> al=new ArrayList<Application>();
    	while (rs.next()) {
    		applicationOb=new Application();
    		applicationOb.setAppId(rs.getInt("APPLICATION_ID"));
    		applicationOb.setJobId(rs.getInt("JOB_ID"));
    		applicationOb.setMemberId(rs.getInt("SITTER_ID")); 		
    		applicationOb.setExpectedPay(rs.getInt("EXPECTED_PAY"));
    		al.add(applicationOb);
    		applicationOb=null;
    	}
		stmt.close();
		DatabaseConnect.closeConnection();
		return al;
	}

	public void deleteApplicationDao(int var) throws SQLException {
		Connection con = DatabaseConnect.getConnection();
    	Statement stmt = con.createStatement();
    	String query="UPDATE APPLICATION SET STATUS=0 WHERE APPLICATION_ID="+var;
    	int i=stmt.executeUpdate(query);
    	stmt.close();
		DatabaseConnect.closeConnection();
	}
	
	public ArrayList<Application> listApplicationsDao() throws SQLException{
		Connection con = DatabaseConnect.getConnection();
    	Statement stmt = con.createStatement();
    	Sitter sitterOb=AppContext.getSitterInstance();
    	Application applicationOb;
    	String query="SELECT APPLICATION.* FROM APPLICATION"
    			+ " WHERE STATUS=1 AND SITTER_ID="+sitterOb.getId();
    	ResultSet rs = stmt.executeQuery(query);
    	ArrayList<Application> al=new ArrayList<Application>();
    	while (rs.next()) {
    		applicationOb=new Application();
    		applicationOb.setAppId(rs.getInt("APPLICATION_ID"));
    		applicationOb.setJobId(rs.getInt("JOB_ID"));
    		applicationOb.setMemberId(rs.getInt("SITTER_ID")); 		
    		applicationOb.setExpectedPay(rs.getInt("EXPECTED_PAY"));
    		al.add(applicationOb);
    		applicationOb=null;
    	}
		stmt.close();
		DatabaseConnect.closeConnection();
		return al;
	}

}
