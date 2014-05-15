package com.ecivil.web.formatter;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import com.ecivil.model.user.User;
import com.ecivil.service.UserService;

/**
 * @author Milan 14 мая 2014 г. - 14:13:51
 * 
 */
public class UserFormatter implements Formatter<User> {
	private final UserService userService;

	@Autowired
	public UserFormatter(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String print(User user, Locale locale) {
		return user.getLogin();
	}

	@Override
	public User parse(String text, Locale locale) throws ParseException {
		List<User> users = this.userService.getAllUsers();
		for (User user : users) {
			if (user.getLogin().equals(text)) {
				return user;
			}
		}

		throw new ParseException("user not found: " + text, 0);
	}
}
