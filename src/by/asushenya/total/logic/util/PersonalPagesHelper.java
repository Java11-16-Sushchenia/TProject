package by.asushenya.total.logic.util;

import java.util.HashMap;
import java.util.Map;

import by.asushenya.total.bean.util.UserRole;

public class PersonalPagesHelper {
		private static final PersonalPagesHelper instance = new PersonalPagesHelper();
		
		private Map<UserRole,String> pages = new HashMap<UserRole,String>();
		
		public PersonalPagesHelper(){
			pages.put(UserRole.ADMIN, "redirectToAdminPage");
			pages.put(UserRole.BOOKMAKER, "redirectToBookmakerPage");
			pages.put(UserRole.USER, "redirectToUserPage");
		}
		
		public static PersonalPagesHelper getInstance(){
			return instance;
		}
		
		public String getPersonalPage(UserRole role){
			return pages.get(role);
		}
		
}
