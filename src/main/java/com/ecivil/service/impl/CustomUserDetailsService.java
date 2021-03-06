package com.ecivil.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecivil.model.Role;
import com.ecivil.repository.UserDao;


@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {
	public static final String ROLE_PREFIX = "ROLE_";
	
	@Autowired
	private UserDao userDAO;	

	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		
		com.ecivil.model.user.User domainUser = userDAO.getUser(login);
	
		if(domainUser != null && domainUser.getRole() != null) {
			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;

			return new User(
					domainUser.getLogin(), 
					domainUser.getPassword(), 
					enabled, 
					accountNonExpired, 
					credentialsNonExpired, 
					accountNonLocked,
					getAuthorities(domainUser.getRole())
			);
			
		}
		else {
			return null;
		}

	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(Role role) {
		List<String> roles = new ArrayList<String>();
		if(role != null && !role.getRole().isEmpty()) {
			roles.add(ROLE_PREFIX + role.getRole());
		}
		List<GrantedAuthority> authList = getGrantedAuthorities(roles);
		return authList;
	}
	
	
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}
