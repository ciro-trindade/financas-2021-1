package br.financas.fatec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.financas.fatec.model.Cliente;
import br.financas.fatec.repositories.ClienteRepository;
import br.financas.fatec.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private ClienteRepository repo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente _cliente = repo.findByLogin(username);
		if (_cliente == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsImpl(_cliente.getId(), _cliente.getLogin(), 
				_cliente.getSenha(), _cliente.getPerfis());
	}


}
