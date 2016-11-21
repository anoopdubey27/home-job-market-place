package homeJobMarketplace;

import homeJobMarketplace.model.Address;
import homeJobMarketplace.model.Application;
import homeJobMarketplace.model.Job;
import homeJobMarketplace.model.Member;
import homeJobMarketplace.model.Seeker;
import homeJobMarketplace.model.Sitter;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.InputMismatchException; import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
	Scanner sc = AppContext.getScannerInstance();
	
	private void createMember(String type) throws SQLException{
		if(type.equals("Seek"))
		{   
			Seeker seekerOb = new Seeker();
			Address addressOb= new Address();
			Member memberOb=new Member();
			askForMemberInfo(seekerOb);
			seekerOb.setTp(Member.Type.SEEKER);
			askForSeekerInfo(seekerOb);
			askForAddressInfo(addressOb);
			AppContext.getMemberServiceInstance().createSeekerService(seekerOb,addressOb,memberOb);
			AppContext.setMemberInstance(seekerOb);
			
		}
		else if(type.equals("Sitter")){
			Sitter sitterOb = new Sitter();
			Address addressOb= new Address();
			Member memberOb=new Member();
			askForMemberInfo(sitterOb);
			sitterOb.setTp(Member.Type.SITTER);
			askForSitterInfo(sitterOb);
			askForAddressInfo(addressOb);
			AppContext.getMemberServiceInstance().createSitterService(sitterOb,addressOb,memberOb);
			AppContext.setMemberInstance(sitterOb);
			
		}
		else if(type.equals("Admin")){
			Member memberOb=AppContext.getMemberInstance();
			askForMemberInfo(memberOb);
			memberOb.setTp(Member.Type.ADMIN);
		}
	}
    
	void askForMemberInfo(Member memberOb) throws SQLException{
		memberOb.setFirstName(PatternMatcher.stringValidate("Enter First Name\n","Invalid First Name or Length should be 1 to 20\n",1,20));
		memberOb.setLastName(PatternMatcher.stringValidate("Enter Last Name\n","Invalid Last Name or Length should be 1 to 20\n",1,20));
		memberOb.setPhoneNumber(PatternMatcher.validatePhoneNumber("Enter Phone Number\n"));
		memberOb.setEmailAddress(PatternMatcher.validateEmailId("Enter Email Address\n","Enter valid Email Address\n",50));
		memberOb.setPassword(PatternMatcher.anyStringValidate("Enter Password\n","Password Length should be 5 to 20\n",5,20));
		//memberOb.setAddress(PatternMatcher.anyStringValidate("Enter Address\n","Address Length should be 1 to 100\n",1,100));
	}
	
	void askForSeekerInfo(Seeker seekerOb){
		seekerOb.setNocFamily(PatternMatcher.askInputInt("Enter the number of children\n","Enter a whole no between 0 to 100\n",0,100));
		int var1=PatternMatcher.askInputInt("Enter Spouse First Name(Optional) \t Press 1 to Skip \t Press 2 to continue\n","Enter only 1 and 2\n",1,2);
		if(var1==2)
		seekerOb.setSpouseName(PatternMatcher.stringValidate("Enter Spouse First Name\n","Invalid Spouse Name or Length should be 1 to 20\n",1,20));
	    
	}
	void askForAddressInfo(Address addressOb){
		addressOb.setStreet1(PatternMatcher.stringValidate("Street1\n","Enter a valid Street1\n",0,30));
		addressOb.setStreet1(PatternMatcher.stringValidate("Street2\n","Enter a valid Street2\n",0,30));
		addressOb.setCountry(PatternMatcher.stringValidate("Country\n","Enter a valid Country\n",0,20));
		addressOb.setState(PatternMatcher.stringValidate("State\n","Enter a valid State\n",0,30));
		addressOb.setPincode(PatternMatcher.askInputInt("Enter the Pincode Code\n","Enter a valid Pincode Code\n",100000,999999));
	    
	}
	 
	void askForSitterInfo(Sitter sitterOb) throws SQLException{
		sitterOb.setYearOfExperience(PatternMatcher.askInputInt("Enter Year Of Experience \n","Enter a whole no between 0 to 100\n",0,100));
		sitterOb.setExpectedPay(PatternMatcher.askInputInt("Enter Expected Pay Per Anum\n","Enter a whole no between 0 to 990000000\n",0,990000000));
		//AppContext.getMemberServiceInstance().createSitterService(sitterOb);
	}
	
	void deleteSeeker() throws SQLException{
		if(PatternMatcher.askInputInt("Are You sure to Delete Your Account? \t Press 1 to Skip \t Press 2 to continue\n","Enter only 1 and 2\n",1,2)==2)
		AppContext.getMemberServiceInstance().deleteSeekerService();
		else 
			return;
	}
	
	void logoutSeeker(){
		Seeker seekerOb = AppContext.getSeekerInstance();
		seekerOb=null;
	}
 
	void loginSeeker() throws SQLException{
		System.out.println("Enter your email ID and Password");
		while(true){
			if(!AppContext.getMemberServiceInstance().loginSeekerService(PatternMatcher.onlyValidateEmailId("","Enter valid Email Address\n",50),PatternMatcher.anyStringValidate("","Password Length should be 5 to 20",5,20)))
				System.out.println("Email ID or Password is not correct");
			else
				break;
		}
	}
	
	void viewSeekerDetails(){
		Seeker seekerOb = AppContext.getSeekerInstance();
		Address addressOb = AppContext.getAddressInstance();
		System.out.println("1. First Name: "+seekerOb.getFirstName());
		System.out.println("2. Last Name: "+seekerOb.getLastName());
		System.out.println("3. Phone Number: "+seekerOb.getPhoneNumber());
		System.out.println("4. Password: "+seekerOb.getPassword());
		System.out.println("5. Address: "+addressOb.getAddress());
		System.out.println("6. No of Children in Family: "+seekerOb.getNocFamily());
		System.out.println("7. Spouse Name: "+seekerOb.getSpouseName());
	}

	void updateSitterDetail() throws SQLException{
		Sitter sitterOb = new Sitter();
		App appOb=new App();
		appOb.viewSitterDetail();
		System.out.println("8. Cancel Update");
		System.out.println("9. Exit Update");
		while(true){
			int ch=PatternMatcher.askInputInt("Enter Seriel no of the feilds to be updated followed by Updated value\n","Enter numbers only 1 to 9\n",1,9);
			switch(ch){
			case 1:
				sitterOb.setFirstName(PatternMatcher.stringValidate("Enter First Name\n","Invalid First Name or Length should be 1 to 20\n",1,20));
				break;
			case 2:
				sitterOb.setLastName(PatternMatcher.stringValidate("Enter Last Name\n","Invalid Last Name or Length should be 1 to 20\n",1,20));
				break;
			case 3:
				sitterOb.setPhoneNumber(PatternMatcher.validatePhoneNumber("Enter Phone Number\n"));
				break;
			case 4:
				sitterOb.setPassword(PatternMatcher.anyStringValidate("Enter Password\n","Password Length should be 5 to 20\n",5,20));
			    break;
			case 5:
				sitterOb.setAddress(PatternMatcher.anyStringValidate("Enter Address\n","Address Length should be 1 to 100\n",1,100));
				break;
			case 6:
				sitterOb.setYearOfExperience(PatternMatcher.askInputInt("Enter Year Of Experience \n","Enter a whole no between 0 to 100\n",0,100));
				break;
			case 7:
				sitterOb.setExpectedPay(PatternMatcher.askInputInt("Enter Expected Pay Per Anum\n","Enter a whole no between 0 to 990000000\n",0,990000000));
				break;
			case 8:
				return;
			case 9:
				Sitter sitterOb1=AppContext.getSitterInstance();
				if(sitterOb.getFirstName()==null)
					sitterOb.setFirstName(sitterOb1.getFirstName());
				if(sitterOb.getLastName()==null)
					sitterOb.setLastName(sitterOb1.getLastName());
				if(sitterOb.getPhoneNumber()==null)
					sitterOb.setPhoneNumber(sitterOb1.getPhoneNumber());
				if(sitterOb.getPassword()==null)
					sitterOb.setPassword(sitterOb1.getPassword());
				if(sitterOb.getAddress()==null)
					sitterOb.setAddress(sitterOb1.getAddress());
				if(sitterOb.getYearOfExperience()==0)
					sitterOb.setYearOfExperience(sitterOb1.getYearOfExperience());
				if(sitterOb.getExpectedPay()==0)
					sitterOb.setExpectedPay(sitterOb1.getExpectedPay());	
				AppContext.getMemberServiceInstance().updateSitterDetailService(sitterOb);
				return;
			}
		}
	}

	void viewSitterDetail() throws SQLException{
		Sitter sitterOb = AppContext.getSitterInstance();
		System.out.println("1. First Name: "+sitterOb.getFirstName());
		System.out.println("2. Last Name: "+sitterOb.getLastName());
		System.out.println("3. Phone Number: "+sitterOb.getPhoneNumber());
		System.out.println("4. Password: "+sitterOb.getPassword());
		System.out.println("5. Address: "+sitterOb.getAddress());
		System.out.println("6. Year of Experience: "+sitterOb.getYearOfExperience());
		System.out.println("7. Expected Pay: "+sitterOb.getExpectedPay());
	}
	
	void createJob() throws SQLException{
        Job jobOb =new Job();
        java.util.Date d;
        jobOb.setJobTitle(PatternMatcher.stringValidate("Enter job Title\n","Invalid Job Title or Length should be betweeen 1 to 20\n",1,20));
        jobOb.setPayPerHour(PatternMatcher.askInputInt("Enter Pay Per Hour\n","Enter a whole no between 0 to 300000\n",0,300000));       
        do {
			System.out.println("enter the correct startDate in format(yyyy-mm-dd)");
			String date = sc.nextLine();
			d = PatternMatcher.validDat(date);
			if(d!=null){
				java.util.Date currentDate = new java.util.Date();
				currentDate.setHours(0);
				currentDate.setMinutes(0);
				currentDate.setSeconds(0);
				/*if(!PatternMatcher.validateDate1BeforeDate2(jobOb.getStartDate(),currentDate,"Start Date should be after current Date")){
					d=null;
				}*/
			}
		} while (d == null);
		jobOb.setStartDate(d);

		do {
			System.out.println("enter the endDate in format(yyyy-mm-dd)");
			String date = sc.nextLine();
			d = PatternMatcher.validDat(date);
			if(d!=null){
				if(!PatternMatcher.validateDate1BeforeDate2(jobOb.getStartDate(),d,"End Date should be after start date\n")){
					d=null;
				}
			}	
		} while (d == null);
		jobOb.setEndDate(d);

        String t;
		do {
			System.out.println("enter the startTime in format(hh:mm)");
			String time = sc.nextLine();
			t = PatternMatcher.validTim(time);
		} while (t == null);
		jobOb.setStartTime(t);

		do {
			System.out.println("enter the endTime in format(hh:mm)");
			String time = sc.nextLine();
			t = PatternMatcher.validTim(time);
		} while (t == null);
		jobOb.setEndTime(t);

        AppContext.getJobServiceInstance().createJobService(jobOb);
	}

	void listJobsBySeeker() throws SQLException{
		ArrayList<Job> al=AppContext.getJobServiceInstance().listJobsBySeekerService();
		Iterator<Job> itr=al.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
			System.out.println("");
		}
	}
	
	void deleteJob() throws SQLException{
		ArrayList<Job> al=AppContext.getJobServiceInstance().listJobsBySeekerService();
		Iterator<Job> itr=al.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
			System.out.println("");
		}
		if(PatternMatcher.askInputInt("Are you sure to Delete a job? Press 1 to continue  Press 2 to skip\n","Enter only 1 and 2\n",1,2)==1){
		while(true){
				System.out.println("Enter Job ID");
				boolean var1=false;
				int var=PatternMatcher.askInputInt("","Enter valid job id",1,900000000);
				Iterator<Job> itr1=al.iterator();
				while(itr1.hasNext()){
					if(itr1.next().getJobId()==var){
						var1=true;
					}			
				}
				if(var1){
					AppContext.getJobServiceInstance().deleteJobService(var);
				    if(PatternMatcher.askInputInt("Want to delete Another?  Press 1 to continue  Press 2 to skip\n","Enter only 1 and 2\n",1,2)==2){
				    	return;
				    }
				}
				else
					System.out.println("Trying to delete a non existing job");
		}
		}
		else
			return;
	}

	void loginSitter() throws SQLException{
		System.out.println("Enter your email ID and Password");
		while(true){
			if(!AppContext.getMemberServiceInstance().loginSitterService(PatternMatcher.onlyValidateEmailId("","Enter valid Email Address",50),PatternMatcher.anyStringValidate("","Password Length should be 5 to 20",5,20)))
			     System.out.println("Email ID or Password is not correct");
			else
				break;
		}
	}
	
	void logoutSitter(){
		Sitter sitterOb=AppContext.getSitterInstance();
		sitterOb=null;
	}

	void deleteSitter() throws SQLException{
		if(PatternMatcher.askInputInt("Are You sure to Delete Your Account? \t Press 1 to Skip \t Press 2 to continue\n","Enter only 1 and 2\n",1,2)==2)
		AppContext.getMemberServiceInstance().deleteSitterService();
		else
			return;
	}

	void logoutSeeker(Boolean var1){
		Seeker seekerOb=AppContext.getSeekerInstance();
		seekerOb=null;
		var1=false;
	}
	
	void updateSeekerDetail() throws SQLException{
		Seeker seekerOb = new Seeker();
		App appOb=new App();
		appOb.viewSeekerDetails();
		System.out.println("8. Cancel Update");
		System.out.println("9. Exit Update");
		while(true){
			int ch=PatternMatcher.askInputInt("Enter Seriel no of the feild to be updated\n","Enter numbers only 1 to 9\n",1,9);			
			switch(ch){
			case 1:
			    seekerOb.setFirstName(PatternMatcher.stringValidate("Enter First Name\n","Invalid First Name or Length should be 1 to 20\n",1,20));
			    break;
			case 2:
			    seekerOb.setLastName(PatternMatcher.stringValidate("Enter Last Name\n","Invalid Last Name or Length should be 1 to 20\n",1,20));
			    break;
			case 3:
				seekerOb.setPhoneNumber(PatternMatcher.validatePhoneNumber("Enter Phone Number\n"));
		        break;
			case 4:    
				seekerOb.setPassword(PatternMatcher.anyStringValidate("Enter Password\n","Password Length should be 5 to 20\n",5,20));
			    break;	
			case 5:    
				seekerOb.setAddress(PatternMatcher.anyStringValidate("Enter Address\n","Address Length should be 1 to 100\n",1,100));
			    break;
			case 6:
			    seekerOb.setNocFamily(PatternMatcher.askInputInt("Enter the number of children\n","Enter a whole no between 0 to 100\n",0,100));
			    break;
			case 7:
			    seekerOb.setSpouseName(PatternMatcher.stringValidate("Enter Spouse Name\n","Invalid Spouse Name or Length should be 1 to 20\n",1,20));
			    break;
			case 8:
				return;
			case 9:
				Seeker seekerOb1=AppContext.getSeekerInstance();
				if(seekerOb.getFirstName()==null)
					seekerOb.setFirstName(seekerOb1.getFirstName());
				if(seekerOb.getLastName()==null)
					seekerOb.setLastName(seekerOb1.getLastName());
				if(seekerOb.getPhoneNumber()==null)
					seekerOb.setPhoneNumber(seekerOb1.getPhoneNumber());
				if(seekerOb.getPassword()==null)
					seekerOb.setPassword(seekerOb1.getPassword());
				if(seekerOb.getAddress()==null)
					seekerOb.setAddress(seekerOb1.getAddress());
				if(seekerOb.getSpouseName()==null)
					seekerOb.setSpouseName(seekerOb1.getSpouseName());
				if(seekerOb.getNocFamily()==0)
					seekerOb.setNocFamily(seekerOb1.getNocFamily());				
				AppContext.getMemberServiceInstance().updateSeekerDetailService(seekerOb);
				return;
			}
		}
	}

	void listSitter() throws SQLException{
		ArrayList<Sitter> al=AppContext.getMemberServiceInstance().listSitterService();
		Iterator<Sitter> itr=al.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
			System.out.println("");
		}
	}
	
	void listSeeker() throws SQLException{
		ArrayList<Seeker> al=AppContext.getMemberServiceInstance().listSeekerService();
		Iterator<Seeker> itr=al.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
			System.out.println("");
		}
	}

	void listAllApplications() throws SQLException{
		ArrayList<Application> al=AppContext.getApplicationServiceInstance().listAllApplicationsService();
		Iterator<Application> itr=al.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
			System.out.println("");
		}
	}
	
	ArrayList<Application> listApplications() throws SQLException{
		ArrayList<Application> al=AppContext.getApplicationServiceInstance().listApplicationsService();
		Iterator<Application> itr=al.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
			System.out.println("");
		}
		return al;
	}

	void deleteApplication() throws SQLException{
		ArrayList<Application> al=AppContext.getApplicationServiceInstance().listAllApplicationsService();
		Iterator<Application> itr=al.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
			System.out.println("");
		}
		while(true){
			if(PatternMatcher.askInputInt("Are you sure to Delete a Application? Press 1 to continue  Press 2 to skip\n","Enter only 1 and 2\n",1,2)==1){
				System.out.println("Enter Application ID");
				boolean var1=false;
				int var=PatternMatcher.askInputInt("","Enter valid Application id",1,900000000);
				Iterator<Application> itr1=al.iterator();
				while(itr1.hasNext()){
					if(itr1.next().getAppId()==var){
						var1=true;
					}			
				}
				if(var1){
					AppContext.getApplicationServiceInstance().deleteApplicationService(var);
				    System.out.println("Application is Deleted: ");
				}
				else
					System.out.println("Trying to delete a non existing Application");
			}
			else
				return;
		}
	}
	
   ArrayList<Job> listJobs() throws SQLException{
		ArrayList<Job> al=AppContext.getJobServiceInstance().listJobsService();
		Iterator<Job> itr=al.iterator();
		while(itr.hasNext()){
			Job jobOb=itr.next();
			System.out.println(jobOb);
			System.out.println("");
		}
		/*if(PatternMatcher.askInputInt("Want to apply for a job? \t1.Apply for a job \t2.Skip\n","Enter only 1 and 2\n",1,2)==1){
			Application applicationOb=AppContext.getApplicationInstance();
			while(true){
				boolean var=false;
				applicationOb.setJobId(PatternMatcher.askInputInt("Job Id \n","Enter a valid Job Id\n",0,9000000));
				while(itr.hasNext()){
					Job jobOb=itr.next();
					if(jobOb.getJobId()==applicationOb.getJobId())
						var=true;
				}
				if(var)
					break;
				else
					System.out.println("Enter a Valid Job Id");
			}
			applicationOb.setMemberId(AppContext.getSeekerInstance().getMemberId());
			applicationOb.setJobId(PatternMatcher.askInputInt("Enter Expected Pay\n","Enter a valid expected Pay\n",0,9000000));
	        AppContext.getApplicationServiceInstance().applyJobService(applicationOb);
		}*/
		return al;
	}
   
   void applyForJob() throws SQLException{
	   App appOb=new App();   
	   ArrayList<Job> al=appOb.listJobs();
		   while(true){
			if(PatternMatcher.askInputInt("Want to apply for a job? \t1.Apply for a job \t2.Skip\n","Enter only 1 and 2\n",1,2)==1){
			Application applicationOb=new Application();
			while(true){
				boolean var=false;
				applicationOb.setJobId(PatternMatcher.askInputInt("Job Id ?\n","Enter a valid Job Id\n",0,9000000));
				Iterator<Job> itr=al.iterator();
				while(itr.hasNext()){
					Job jobOb=itr.next();
					if(jobOb.getJobId()==applicationOb.getJobId())
						var=true;
				}
				if(var)
					break;
				else
					System.out.println("Trying to apply a non existing job");
			}
			applicationOb.setMemberId(AppContext.getSitterInstance().getId());
			applicationOb.setExpectedPay(PatternMatcher.askInputInt("Enter Expected Pay\n","Enter a valid expected Pay\n",0,9000000));
			ArrayList<Application> al1=new App().listApplications();
			Iterator<Application> itr1=al1.iterator();
			boolean var1=false;
			while(itr1.hasNext()){
				Application applicationOb1=itr1.next();
				if(applicationOb1.getJobId()==applicationOb.getJobId()){
					var1 =true;
				}
			}
			if(var1){
				System.out.println("Trying to apply for already applied job");
			}
			else
	           AppContext.getApplicationServiceInstance().applyForJobService(applicationOb);
		}
			else
				break;
			}
   }

   void loginAdmin() throws SQLException{
		System.out.println("Enter your email ID and Password");
		while(true){
			if(!AppContext.getAdminServiceInstance().loginAdminService(PatternMatcher.onlyValidateEmailId("","Enter valid Email Address",50),PatternMatcher.anyStringValidate("","Password Length should be 5 to 20",5,20)))
				System.out.println("Email ID or Password is not correct");
			else
				break;
		}
	}
   
   void viewAdminDetails() throws SQLException{
	   Member memberOb1=AppContext.getMemberInstance();
		//System.out.println("Enter Seriel no of the feilds to be updated followed by Updated value");
		System.out.println("1. First Name: "+memberOb1.getFirstName());
		System.out.println("2. Last Name: "+memberOb1.getLastName());
		System.out.println("3. Phone Number: "+memberOb1.getPhoneNumber());
		System.out.println("4. Password: "+memberOb1.getPassword());
		System.out.println("5. Address: "+memberOb1.getAddress());   
   }
   
   void updateAdminDetail() throws SQLException{
		Member memberOb = new Member();
		App appOb=new App();
		appOb.viewAdminDetails();
		System.out.println("6. Exit Update");
		while(true){
			int ch=PatternMatcher.askInputInt("Enter Seriel no of the feild to be updated followed by Updated value\n","Enter numbers only 1 to 6\n",1,6);			
			switch(ch){
			case 1:
				memberOb.setFirstName(PatternMatcher.stringValidate("Enter First Name\n","Invalid First Name or Length should be 1 to 20\n",1,20));
			    break;
			case 2:
				memberOb.setLastName(PatternMatcher.stringValidate("Enter Last Name\n","Invalid Last Name or Length should be 1 to 20\n",1,20));
			    break;
			case 3:
				memberOb.setPhoneNumber(PatternMatcher.validatePhoneNumber("Enter Phone Number\n"));
		        break;
			case 4:    
				memberOb.setPassword(PatternMatcher.anyStringValidate("Enter Password\n","Password Length should be 5 to 20\n",5,20));
			    break;	
			case 5:    
				memberOb.setAddress(PatternMatcher.anyStringValidate("Enter Address\n","Address Length should be 1 to 100\n",1,100));
			    break;
			case 6:
				Member memberOb1=AppContext.getMemberInstance();
				if(memberOb.getFirstName()==null)
					memberOb.setFirstName(memberOb1.getFirstName());
				if(memberOb.getLastName()==null)
					memberOb.setLastName(memberOb1.getLastName());
				if(memberOb.getPhoneNumber()==null)
					memberOb.setPhoneNumber(memberOb1.getPhoneNumber());
				if(memberOb.getPassword()==null)
					memberOb.setPassword(memberOb1.getPassword());
				if(memberOb.getAddress()==null)
					memberOb.setAddress(memberOb1.getAddress());
				AppContext.getAdminServiceInstance().updateAdminDetailService(memberOb);
				return;
			}
		}
	}
   
   void listMembers() throws SQLException{
	   System.out.println("Sitters");
	   App appOb=new App();
	   appOb.listSitter();
	   System.out.println("Seekers");
	   appOb.listSeeker();
   }
	void searchMember() throws SQLException{
		System.out.println("Enter the email ID of member whom you want to Search");
		Member memberOb=null;
		memberOb =AppContext.getMemberServiceInstance().searchMemberService(PatternMatcher.onlyValidateEmailId("","Enter valid Email Address",50));
		if(memberOb!=null){
			System.out.println(memberOb);
		}
	}

	public static void main(String[] argv) throws SQLException{
		Scanner sc = AppContext.getScannerInstance();
		App appOb = new App();
        while(true){
        	System.out.println("1. Are you a Seeker? ");
    		System.out.println("2. Are you a Sitter? ");
    		System.out.println("3. Are you an Admin? ");
    		System.out.println("4. Exit ");
    		int ch=PatternMatcher.askInputInt("Enter a choice:\n","Enter numbers only 1 to 4\n",1,4);
    		switch (ch) {
    		case 1:
    			System.out.println("1. Create a Seeker Account");
    			System.out.println("2. Login if already have Account");
    			int ch1=PatternMatcher.askInputInt("Enter a choice:\n","Enter numbers only 1 to 2\n",1,2);
    			Boolean isAlreadyLoggedin = false;
    			switch (ch1) {
    			case 1:
    				appOb.createMember("Seeker");
    				isAlreadyLoggedin = true;
    			case 2:
    				if (!isAlreadyLoggedin)
    					appOb.loginSeeker();
    				boolean var1=true;
    				while (var1) {
						System.out.println("Hello "+ AppContext.getSeekerInstance().getFirstName());
						System.out.println("1. View Details");
						System.out.println("2. Update Details");						
						System.out.println("3. List all Sitter");
						System.out.println("4. Create a Job");
						System.out.println("5. List Jobs");
						System.out.println("6. Delete a Job");
						System.out.println("7. List all Application");
						System.out.println("8. Delete an Application");
						System.out.println("9. Log Out  ");
						System.out.println("10. Delete My Account ");
						System.out.println("11. Exit ");
						int ch11=PatternMatcher.askInputInt("Enter a choice:\n","Enter numbers only 1 to 11\n",1,11);
						switch (ch11) {
						case 1:
							appOb.viewSeekerDetails();
							break;
						case 2:
							appOb.updateSeekerDetail();
							break;
						case 3:
							appOb.listSitter();
							break;
						case 4:
							appOb.createJob();
							break;
						case 5:
							appOb.listJobsBySeeker();
							break;
						case 6:
							appOb.deleteJob();
							break;
						case 7:
							appOb.listAllApplications();
							break;
						case 8:
							appOb.deleteApplication();
							break;
						case 9:
						    appOb.logoutSeeker();
						    var1=false;
							break;
						case 10:
							appOb.deleteSeeker();
							appOb.logoutSeeker();
							var1=false;
							break;
						case 11:
							System.exit(1);
						}
    			}
    			break;
    		}
            break;  
    		case 2:
    			System.out.println("1. Create a Sitter");
    			System.out.println("2. Login if already have Account");
    			int ch2=PatternMatcher.askInputInt("Enter a choice:\n","Enter numbers only 1 to 2\n",1,2);
    			boolean var1 = false;
    			switch (ch2) {
    			case 1:
    				appOb.createMember("Sitter");
    				var1 = true;
    			case 2:
    				if (!var1)
    					appOb.loginSitter();
    				Boolean var2=true;
    				while (var2) {
    						System.out.println("Hello "+ AppContext.getSitterInstance().getFirstName());
    						System.out.println("1. View Details");
    						System.out.println("2. Update Details");
    						System.out.println("3. List all Job");
    						System.out.println("4. List my Applications");
    						System.out.println("5. Apply for a Job ");
    						System.out.println("6. Log Out ");
    						System.out.println("7. Delete my Account");
    						System.out.println("8. Exit ");
    						int ch21=PatternMatcher.askInputInt("Enter a choice:\n","Enter numbers only 1 to 8\n",1,8);
    						switch (ch21) {
    						case 1:
    							appOb.viewSitterDetail();
    							break;
    						case 2:
    							appOb.updateSitterDetail();
    							break;
    						case 3:
    							appOb.listJobs();
    							break;
    						case 4:
    							appOb.listApplications();
    							break;
    						case 5:
    							appOb.applyForJob();
    							break;
    						case 6:
    							appOb.logoutSitter();
    							var2=false;
    							break;
    						case 7:
    							appOb.deleteSitter();
    							appOb.logoutSitter();
    							var2=false;
    							break;
    						case 8:
    							System.exit(1);
    						}
    					}
    				break;
    			}
    			break;
    		case 3:
    			appOb.loginAdmin();
    			while (true) {
    				System.out.println("Hello "+ AppContext.getMemberInstance().getFirstName());
					System.out.println("1. Update Details");
					System.out.println("2. List all Members");
					System.out.println("3. Search a Member");
					System.out.println("4. Exit ");
					int ch3 = PatternMatcher.askInputInt("Enter a choice:\n","Enter numbers only 1 to 4\n",1,4);
					switch (ch3) {
					case 1:
						appOb.updateAdminDetail();
						break;
					case 2:
						appOb.listMembers();
						break;
					case 3:
						appOb.searchMember();
						break;
					case 4:
						System.exit(1);
					}
				}
    		
    		}

       }
		
	}
}
