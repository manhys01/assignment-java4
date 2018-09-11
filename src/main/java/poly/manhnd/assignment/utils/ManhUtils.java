package poly.manhnd.assignment.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManhUtils {
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean isEmail(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}
	
	public static boolean isUserName(String username) {
		String pattern = "^[a-zA-Z0-9]*$";
		return username.matches(pattern);
	}
	
	public static boolean isPhoneNumber(String phone) {
		phone = phone.replaceAll(" ", "");
		String pattern = "0[0-9]{9,13}";
		return phone.matches(pattern);
	}
	
}
