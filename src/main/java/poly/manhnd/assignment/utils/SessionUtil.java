package poly.manhnd.assignment.utils;

import javax.servlet.http.HttpSession;

public class SessionUtil {
	
	public static void removeSessionAttribute(HttpSession session, String... name) {
		for(int i=0; i<name.length;i++) {
			session.removeAttribute(name[i]);
		}
	}
}
