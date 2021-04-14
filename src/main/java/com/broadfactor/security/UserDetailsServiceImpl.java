package com.broadfactor.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.broadfactor.domain.model.UsuarioEntidade;
import com.broadfactor.domain.respositorio.UsuarioRepositorio;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepositorio usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		Optional<UsuarioEntidade> user= usuarioRepository.findByNome(userName);
		user.orElseThrow(()-> new UsernameNotFoundException(userName + "not found"));
		return user.map(UserDetailsImpl::new).get();
	}

}
