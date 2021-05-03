package br.financas.fatec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.financas.fatec.exception.AuthorizationException;
import br.financas.fatec.model.PessoaFisica;
import br.financas.fatec.repositories.PessoaFisicaRepository;
import br.financas.fatec.security.JWTUtil;

@Service
public class PessoaFisicaService implements ServiceInterface<PessoaFisica>{

	@Autowired
	private PessoaFisicaRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Override
	public PessoaFisica create(PessoaFisica obj) {
		obj.setSenha(passwordEncoder.encode(obj.getSenha()));
		repository.save(obj);
		return obj;
	}

	@Override
	public PessoaFisica findById(Long id) throws AuthorizationException {
		if (!jwtUtil.authorized(id)) {
			throw new AuthorizationException("Acesso negado!");
		}
		Optional<PessoaFisica> _conta = repository.findById(id);
		return _conta.orElse(null);
	}

	@Override
	public List<PessoaFisica> findAll() {		
		return repository.findAll();
	}

	@Override
	public boolean update(PessoaFisica obj) {
		if (repository.existsById(obj.getId())) {
			repository.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
	
}
