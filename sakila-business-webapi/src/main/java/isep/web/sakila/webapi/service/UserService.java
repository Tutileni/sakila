package isep.web.sakila.webapi.service;

import org.springframework.security.core.context.SecurityContextHolder;

import isep.web.sakila.webapi.security.Login;

public class UserService {
	
	public static Login authenticated() {
		try {
			return (Login) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
