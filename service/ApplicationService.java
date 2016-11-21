package homeJobMarketplace.service;

import homeJobMarketplace.AppContext;
import homeJobMarketplace.model.Application;

import java.sql.SQLException;
import java.util.ArrayList;

public class ApplicationService {

	public void applyForJobService(Application applicatioOb) throws SQLException{
		AppContext.getApplicationDaoInstance().applyForJobDao(applicatioOb);
	}
	
	public ArrayList<Application> listAllApplicationsService() throws SQLException{
		return AppContext.getApplicationDaoInstance().listAllApplicationsDao();
	}
	
	public void deleteApplicationService(int var) throws SQLException{
		AppContext.getApplicationDaoInstance().deleteApplicationDao(var);
	}
	
	public ArrayList<Application> listApplicationsService() throws SQLException{
		return AppContext.getApplicationDaoInstance().listApplicationsDao();
	}
}
