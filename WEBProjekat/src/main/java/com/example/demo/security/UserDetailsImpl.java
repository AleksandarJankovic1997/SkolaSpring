package com.example.demo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import model.Ajrole;

public class UserDetailsImpl implements UserDetails{
	private String password;
	private String username;
	private List<Ajrole> roles;
	@Override
	public Collection<SimpleGrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities=new ArrayList<SimpleGrantedAuthority>();
		for(Ajrole r:roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+r.getNaziv()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public void setUserName(String userName) {
		this.username=userName;
	}
	public List<Ajrole> getRoles(){
		return this.roles;
	}
	public void setRoles(List<Ajrole> roles) {
		this.roles=roles;
	}
}

