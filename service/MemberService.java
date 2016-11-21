package homeJobMarketplace.service;

import homeJobMarketplace.AppContext;
import homeJobMarketplace.model.Address;
import homeJobMarketplace.model.Member;
import homeJobMarketplace.model.Seeker;
import homeJobMarketplace.model.Sitter;

import java.sql.SQLException;
import java.util.ArrayList;

public class MemberService {
	public Member searchMemberService(String emailId) throws SQLException{
		return AppContext.getMemberDaoInstance().searchMemberDao(emailId);
	}

	public void createSitterService(Sitter sitterOb, Address addressOb, Member memberOb) throws SQLException{
		AppContext.getSitterDaoInstance().createSitterDao(sitterOb);
		AppContext.getAddressDaoInstance().createAddressDao(addressOb, memberOb);
	}
	
	public void updateSitterDetailService(Sitter sitterOb) throws SQLException{
		AppContext.getSitterDaoInstance().updateSitterDetailDao(sitterOb);
	}

	public void deleteSitterService() throws SQLException{
		AppContext.getSitterDaoInstance().deleteSitterDao();
	}

	public boolean loginSitterService(String emailId, String password) throws SQLException{
		return AppContext.getSitterDaoInstance().loginSitterDao(emailId, password);
	}
	
	public void createSeekerService(Seeker seekerOb, Address addressOb,Member memberOb) throws SQLException {
		AppContext.getSeekerDaoInstance().createSeekerDao(seekerOb);
		AppContext.getAddressDaoInstance().createAddressDao(addressOb, memberOb);
	}
	
    
	public void deleteSeekerService() throws SQLException{
		AppContext.getSeekerDaoInstance().deleteSeekerDao();
	}

	public boolean loginSeekerService(String emailId, String password) throws SQLException{
		return AppContext.getSeekerDaoInstance().loginSeekerDao(emailId, password);
	}
	
	public void updateSeekerDetailService(Seeker seekerOb) throws SQLException{
		AppContext.getSeekerDaoInstance().updateSeekerDetailDao(seekerOb);
	}

	public ArrayList<Sitter> listSitterService() throws SQLException{
		return AppContext.getSitterDaoInstance().listSitterDao();
	}
	
	public boolean checkUniqueEmail(String emailID) throws SQLException{
		return AppContext.getMemberDaoInstance().checkUniqueEmail(emailID);
	}
	
	public ArrayList<Seeker> listSeekerService() throws SQLException{
		return AppContext.getSeekerDaoInstance().listSeekerDao();
	}
}
