package homeJobMarketplace.service;

import homeJobMarketplace.AppContext;
import homeJobMarketplace.model.Job;

import java.sql.SQLException;
import java.util.ArrayList;

public class JobService {
	
	public void deleteJobService(int jobId) throws SQLException {
		AppContext.getJobDaoInstance().deleteJobDao(jobId);
	}
	
	public ArrayList<Job> listJobsService() throws SQLException{
		return AppContext.getJobDaoInstance().listJobsDao();
	}
	
	public void createJobService(Job jobOb) throws SQLException{
		AppContext.getJobDaoInstance().createJobDao(jobOb);
	}
	
	public ArrayList<Job> listJobsBySeekerService() throws SQLException{
		return AppContext.getJobDaoInstance().listJobsBySeekerDao();
	}
}
