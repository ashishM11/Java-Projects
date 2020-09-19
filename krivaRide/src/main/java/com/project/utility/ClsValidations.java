package com.project.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClsValidations {

	public static boolean validation=false;
	// Email Regex java
	private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

	// static Pattern object, since pattern is fixed
	private static Pattern pattern;

	//static Matcher object because it's created from the input String
	private static Matcher matcher;

	
	
	public static boolean checkStringLen(String str,int length){		
		if(str.length() > length) {
			validation=true;
		}
		return validation; 
	}
	
	public static boolean isNumber(String str) {
		try
	    {
	        Integer.parseInt(str);
	        validation=true;
	    }
	    catch(NumberFormatException ex)
	    {
	        return validation;
	    }
		return validation;
	}
	
	public static boolean isValidMobile(String str) {
		//validate phone numbers of format "1234567890"
		if (str.matches("\\d{10}") && str.length()==10) 
			validation=true;
		
		return validation;
	}

	public static boolean validateEmail(String email) {
		// initialize the Pattern object
		pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
