package homeJobMarketplace.dao;

import homeJobMarketplace.AppContext;
import homeJobMarketplace.DatabaseConnect;
import homeJobMarketplace.model.Job;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

public class JobDao {
	
	public void createJobDao(Job jobOb)throws SQLException{
    	Connection con = DatabaseConnect.getConnection();
    	
    	PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO JOB(JOB_TITLE,POSTED_BY,START_DATE,END_DATE,START_TIME,END_TIME,PAY_PER_HOUR) "
    			+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
    	preparedStatement.setString(1, jobOb.getJobTitle());
    	preparedStatement.setInt(2, AppContext.getSeekerInstance().getId());
    	preparedStatement.setDate(3, new Date(jobOb.getStartDate().getTime()));
    	preparedStatement.setDate(4, new Date(jobOb.getEndDate().getTime()));
    	preparedStatement.setTime(5, Time.valueOf(jobOb.getStartTime()));
    	preparedStatement.setTime(6, Time.valueOf(jobOb.getEndTime()));
    	preparedStatement.setInt(7, jobOb.getPayPerHour());
    	
    	preparedStatement.executeUpdate();
    	preparedStatement.close();
    	/*Statement stmt = con.createStatement();
    	String query="INSERT INTO JOB(JOB_TITLE,POSTED_BY,START_DATE,END_DATE,START_TIME,END_TIME,PAY_PER_HOUR)"
    			+"VALUES ('" + jobOb.getJobTitle()+"',"
    			+AppContext.getSeekerInstance().getMemberId()+","
    			+"'"+jobOb.getStartDate().toString()+"',"
    			+"'"+jobOb.getEndDate().toString()+"',"
    			+jobOb.getPayPerHour()+")";
    	int i = stmt.executeUpdate(query);
		stmt.close();*/
		DatabaseConnect.closeConnection();
    	}
	
    public void deleteJobDao(int jobId) throws SQLException{
    	Connection con = DatabaseConnect.getConnection();
    	Statement stmt = con.createStatement();
    	String query="UPDATE JOB j SET j.STATUS=0 where JOB_ID="+jobId;
    	int i = stmt.executeUpdate(query);
		stmt.close();
		Statement stmt1 = con.createStatement();
    	String query1="UPDATE APPLICATION a SET a.STATUS=0 where JOB_ID="+jobId;
    	i = stmt1.executeUpdate(query1);
		stmt1.close();
		DatabaseConnect.closeConnection();
	}

	public ArrayList<Job> listJobsDao() throws SQLException{
		Connection con = DatabaseConnect.getConnection();
    	Statement stmt = con.createStatement();
    	Job jobOb;
    	String query="SELECT * FROM JOB WHERE STATUS=1 AND END_DATE > CURDATE()";
    	ResultSet rs = stmt.executeQuery(query);
    	ArrayList<Job> al=new ArrayList<Job>();
    	while (rs.next()) {
    		jobOb=new Job();
    		jobOb.setJobId(rs.getInt("JOB_ID"));
    		jobOb.setJobTitle(rs.getString("JOB_TITLE"));
    		jobOb.setPostedBy(rs.getInt("POSTED_BY"));
    		jobOb.setStartDate(rs.getDate("START_DATE"));
    		jobOb.setEndDate(rs.getDate("END_DATE"));
    		jobOb.setStartTime(rs.getString("START_TIME"));
    		jobOb.setEndTime(rs.getString("END_TIME"));
    		jobOb.setPayPerHour(rs.getInt("PAY_PER_HOUR"));
    		al.add(jobOb);
    		jobOb=null;
    	}
		stmt.close();
		DatabaseConnect.closeConnection();
		return al;
	}
	
	public ArrayList<Job> listJobsBySeekerDao() throws SQLException{
		Connection con = DatabaseConnect.getConnection();
    	Statement stmt = con.createStatement();
    	Job jobOb;
    	String query="SELECT * FROM JOB WHERE POSTED_BY="+AppContext.getSeekerInstance().getId();
    	ResultSet rs = stmt.executeQuery(query);
    	ArrayList<Job> al=new ArrayList<Job>();
    	while (rs.next()) {
    		jobOb=new Job();
    		jobOb.setJobId(rs.getInt("JOB_ID"));
    		jobOb.setJobTitle(rs.getString("JOB_TITLE"));
    		jobOb.setPostedBy(rs.getInt("POSTED_BY"));
    		jobOb.setStartDate(rs.getDate("START_DATE"));
    		jobOb.setEndDate(rs.getDate("END_DATE"));
    		jobOb.setStartTime(rs.getString("START_TIME"));
    		jobOb.setEndTime(rs.getString("END_TIME"));
    		jobOb.setPayPerHour(rs.getInt("PAY_PER_HOUR"));
    		jobOb.setStatus(rs.getInt("STATUS"));
    		al.add(jobOb);
    		jobOb=null;
    	}
		stmt.close();
		DatabaseConnect.closeConnection();
		return al;
	}
}
