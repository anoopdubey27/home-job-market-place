package homeJobMarketplace.service;

import homeJobMarketplace.AppContext;
import homeJobMarketplace.model.Member;

import java.sql.SQLException;

public class AdminService {  
    public boolean loginAdminService(String emailId,String password) throws SQLException{
		return AppContext.getAdminDaoInstance().loginAdminDao(emailId,password);
	}
    
    public void updateAdminDetailService(Member memberOb) throws SQLException{
    	AppContext.getAdminDaoInstance().updateAdminDetailDao(memberOb);
    }
}
