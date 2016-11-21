package homeJobMarketplace;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class PatternMatcher {

	public static boolean validateEmailAddress(String userInp, int endLimit) {
		if ((userInp.length() < endLimit)
				&& Pattern.matches(
						"^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$",
						userInp) && userInp != null)
			return true;
		else
			return false;
	}

	public static Date validDat(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (Exception e) {
		}
        //if(Integer.parseInt(date.substring(0, 4))>2100)
        	//return null;
       // else
		return d;
	}

	public static String validTim(String time) {
		time=time+":00";
		Time t = null;
		try {
			t = Time.valueOf(time);
	} catch (Exception e) {
		}
		if(t!=null){
			if(Integer.parseInt(time.substring(0,2))>24 || 
					Integer.parseInt(time.substring(0,2))<0 || 
					Integer.parseInt(time.substring(3,5))>60 || 
					(Integer.parseInt(time.substring(3,5)))<0)
			     return null;
			else
			   return t.toString();			
		}
		return null;
}

	public static boolean validateDate1BeforeDate2(Date date1, Date date2,String errormsg) {

		if (date1 == null || date2 == null)
			return false;
		if (!date1.equals(date2) && !date1.before(date2)) {
			System.out.println(errormsg);
			return false;
		} else
			return true;

	}

	public static int askInputInt(String informationText, String errorMsg,
			int startLimit, int endLimit) {
		Boolean error = false;
		String userInp = "";
		System.out.print(informationText);
		do {
			userInp = AppContext.getScannerInstance().nextLine();
			if (!isType(userInp, "int")
					|| Integer.parseInt(userInp) < startLimit
					|| Integer.parseInt(userInp) > endLimit) {
				error = true;
				System.out.print(errorMsg);
			} else {
				error = false;
			}
		} while (error == true);
		return Integer.parseInt(userInp);
	}

	public static Boolean isType(String testStr, String type) {
		try {
			if (type.equalsIgnoreCase("float")) {
				Float.parseFloat(testStr);
			} else if (type.equalsIgnoreCase("int")) {
				Integer.parseInt(testStr);
			} else if (type.equalsIgnoreCase("double")) {
				Double.parseDouble(testStr);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String stringValidate(String informationText,
			String errorMsg, int startLimit, int endLimit) {
		System.out.print(informationText);
		String userInp;
		while (true) {
			userInp = AppContext.getScannerInstance().nextLine();
			if (!Pattern.matches("[a-z A-Z]{" + startLimit + "," + endLimit
					+ "}", userInp)) {
				System.out.print(errorMsg);
			} else
				return userInp;
		}
	}

	public static String anyStringValidate(String informationText,
			String errorMsg, int startLimit, int endLimit) {
		System.out.print(informationText);
		String userInp;
		while (true) {
			userInp = AppContext.getScannerInstance().nextLine();
			if (userInp == null || userInp.length() > endLimit
					|| userInp.length() < startLimit) {
				System.out.print(errorMsg);
			} else
				return userInp;
		}
	}

	public static String validateEmailId(String informationText,
			String errorMsg, int endLimit) throws SQLException {
		System.out.print(informationText);
		String userInp;
		while (true) {
			userInp = AppContext.getScannerInstance().nextLine();
			boolean var = false;
			if (PatternMatcher.validateEmailAddress(userInp, endLimit)
					&& userInp != null) {
				if (AppContext.getMemberServiceInstance().checkUniqueEmail(
						userInp)) {
					System.out.println("Entered address already present");
					var = true;
				}
				if (!var)
					return userInp;
			} else
				System.out.print(errorMsg);
		}
	}

	public static String validatePhoneNumber(String informationText) {
		System.out.print(informationText);
		String userInp;
		while (true) {
			userInp = AppContext.getScannerInstance().nextLine();
			if (!Pattern.matches("\\d{10}", userInp)) {
				System.out.println("Enter valid Phone Number of ten digits");
			} else
				return userInp;
		}
	}

	public static String onlyValidateEmailId(String informationText,
			String errorMsg, int endLimit) {
		System.out.print(informationText);
		String userInp;
		while (true) {
			userInp = AppContext.getScannerInstance().nextLine();
			if ((userInp.length() < endLimit)
					&& Pattern
							.matches(
									"^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$",
									userInp) && userInp != null)
				return userInp;
			else
				System.out.print(errorMsg);
		}
	}

	public static Date isThisDateValid(String dateToValidate, String dateFromat) {

		if (dateToValidate == null) {
			return null;
		}
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {
			// if not valid, it will throw ParseException
			date = (Date) sdf.parse(dateToValidate);
			System.out.println(date);
		} catch (ParseException e) {
			return null;
		}

		return date;
	}
}
