package homeJobMarketplace;

import homeJobMarketplace.dao.AddressDao;
import homeJobMarketplace.dao.AdminDao;
import homeJobMarketplace.dao.ApplicationDao;
import homeJobMarketplace.dao.JobDao;
import homeJobMarketplace.dao.MemberDao;
import homeJobMarketplace.dao.SeekerDao;
import homeJobMarketplace.dao.SitterDao;
import homeJobMarketplace.model.Address;
import homeJobMarketplace.model.Application;
import homeJobMarketplace.model.Job;
import homeJobMarketplace.model.Member;
import homeJobMarketplace.model.Seeker;
import homeJobMarketplace.model.Sitter;
import homeJobMarketplace.service.AdminService;
import homeJobMarketplace.service.ApplicationService;
import homeJobMarketplace.service.JobService;
import homeJobMarketplace.service.MemberService;

import java.util.Scanner;

public class AppContext {
	static Sitter sitterInstance;
	static Seeker seekerInstance;
    static Member memberInstance;
/*	static Job jobInstance;*/
/*	static Application applicationInstance;*/
	static AdminService adminServiceInstance;
	static ApplicationService applicationServiceInstance;
	static JobService jobServiceInstance;
	static MemberService memberServiceInstance;
	static AdminDao adminDaoInstance;
	static ApplicationDao applicationDaoInstance;
	static JobDao jobDaoInstance;
	static MemberDao memberDaoInstance;
	static SeekerDao seekerDaoInstance;
	static SitterDao sitterDaoInstance;
	static AddressDao addressDaoInstance;
	static Address addressOb;
	static Scanner scannerInstance;
	public static AddressDao getApplicationInstance() {
		if(addressDaoInstance==null){
			addressDaoInstance= new AddressDao();
			return addressDaoInstance;
		}		    
		else
			return addressDaoInstance;
	}
	
	public static Member getMemberInstance() {
		/*if(memberInstance==null){
			memberInstance=new Member();
			return memberInstance;
		}
		    
		else*/
			return memberInstance;
	}

	public static void setMemberInstance(Member member){
		memberInstance = member;
	}
	
	public static Sitter getSitterInstance() {
		Sitter sitter = null;
		if(memberInstance != null && memberInstance instanceof Sitter){
			sitter = (Sitter)memberInstance; 
		}
		return sitter;
	}

	public static Seeker getSeekerInstance() {
		Seeker seeker = null;
		if(memberInstance != null && memberInstance instanceof Seeker){
			seeker = (Seeker)memberInstance; 
		}
		return seeker;
	}
	
/*
	public static Job getJobInstance() {
		if(jobInstance==null){
			jobInstance = new Job();
			return jobInstance;	
		}
		else
			return jobInstance;
	}*
	public static Application getApplicationInstance() {
		if(applicationInstance==null){
			applicationInstance= new Application();
			return applicationInstance;
		}		    
		else
			return applicationInstance;
	}
*/
	public static AdminService getAdminServiceInstance() {
		if(adminServiceInstance==null){
			adminServiceInstance=new AdminService();
			return adminServiceInstance;
		}
		else
			return adminServiceInstance;
	}

	public static ApplicationService getApplicationServiceInstance() {
		if(applicationServiceInstance==null){
			applicationServiceInstance=new ApplicationService();
			return applicationServiceInstance;
		}		    
		else
			return applicationServiceInstance;
	}

	public static JobService getJobServiceInstance() {
		if(jobServiceInstance==null){
			jobServiceInstance=new JobService();
			return jobServiceInstance;
		}		    
		else
			return jobServiceInstance;
	}

	public static MemberService getMemberServiceInstance() {
		if(memberServiceInstance==null){
			memberServiceInstance=new MemberService();
			return memberServiceInstance;
		}		   
		else
			return memberServiceInstance;
	}

	public static AdminDao getAdminDaoInstance() {
		if(adminDaoInstance==null){
			adminDaoInstance=new AdminDao();
			return adminDaoInstance;
		}		    
		else
			return adminDaoInstance;
	}

	public static ApplicationDao getApplicationDaoInstance() {
		if(applicationDaoInstance==null){
			applicationDaoInstance=new ApplicationDao();
			return applicationDaoInstance;
		}
		else
			return applicationDaoInstance;
	}

	public static JobDao getJobDaoInstance() {
		if(jobDaoInstance==null){
			jobDaoInstance=new JobDao();
			return jobDaoInstance;
		}
		else
			return jobDaoInstance;
	}

	public static MemberDao getMemberDaoInstance() {
		if(memberDaoInstance==null){
			memberDaoInstance=new MemberDao();
			return memberDaoInstance;
		}
		else
			return memberDaoInstance;
	}

	public static SeekerDao getSeekerDaoInstance() {
		if(seekerDaoInstance==null){
			seekerDaoInstance=new SeekerDao();
		    return seekerDaoInstance;			
		}
		else
			return seekerDaoInstance;
	}
	
	public static AddressDao getAddressDaoInstance() {
		if(addressDaoInstance==null){
			addressDaoInstance=new AddressDao();
		    return addressDaoInstance;			
		}
		else
			return addressDaoInstance;
	}

	public static SitterDao getSitterDaoInstance() {
		if(sitterDaoInstance==null){
			sitterDaoInstance=new SitterDao();
		    return sitterDaoInstance;
		}
		else
			return sitterDaoInstance;
	}

	public static Scanner getScannerInstance() {
		if(scannerInstance==null){
			scannerInstance=new Scanner(System.in);
		    return scannerInstance;
		}
		else
			return scannerInstance;
	}
	
	
 
}
