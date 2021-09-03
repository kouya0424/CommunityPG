package com.rugbyaholic.communityPG.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rugbyaholic.communityPG.common.aspects.LogRequired;
import com.rugbyaholic.communityPG.common.repositories.UserRepository;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	@LogRequired
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return repository.identifyUser(username).orElseThrow(() -> new UsernameNotFoundException(username));
	}
}