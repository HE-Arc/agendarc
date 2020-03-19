package com.hearc.agendarc;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hearc.agendarc.model.Role;
import com.hearc.agendarc.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  
  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    final com.hearc.agendarc.model.User utilisateur = userRepository.findByName(username);
    if (utilisateur == null)
      throw new UsernameNotFoundException(username);

    final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    for (final Role role : utilisateur.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new User(utilisateur.getName(), utilisateur.getPwd(), grantedAuthorities);
  }
}