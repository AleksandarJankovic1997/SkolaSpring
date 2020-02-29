package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.AjuserRepository;

import model.Ajuser;
@Service("customUserDetailService")
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private AjuserRepository ur;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Ajuser user=ur.findByUsername(username);
		UserDetailsImpl userDetails=new UserDetailsImpl();
		userDetails.setUserName(user.getUsername());
		userDetails.setPassword(user.getPassword());
		userDetails.setRoles(user.getAjroles());
		return userDetails;
	}

}
