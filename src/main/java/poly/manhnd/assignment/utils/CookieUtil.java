package poly.manhnd.assignment.utils;

import javax.servlet.http.Cookie;

public class CookieUtil {
	
	public static String getCookieValue(Cookie[] cookies, String cookieName) {
		String cookieValue = null;
		if(cookies!=null) {
			for(Cookie cookie: cookies) {
				if(cookieName.equals(cookie.getName())){
					cookieValue = cookie.getValue();
				}
			}
		}
		return cookieValue;
	}
}