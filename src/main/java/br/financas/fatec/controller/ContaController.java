package br.financas.fatec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.financas.fatec.model.Conta;
import br.financas.fatec.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController implements ControllerInterface<Conta> {

	@Autowired
	private ContaService service;

	@Override
	@GetMapping
	public ResponseEntity<List<Conta>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Conta _conta = service.findById(id);
		if (_conta != null)
			return ResponseEntity.ok(_conta);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@PostMapping
	public ResponseEntity<Conta> post(@RequestBody Conta conta) {
		service.create(conta);
		return ResponseEntity.ok(conta);
	}

	/*
	 * @PostMapping("/depositar/{id}/{valor}") public ResponseEntity<?>
	 * depositar(@PathVariable("id") Long id,
	 * 
	 * @PathVariable("valor") Float valor) { if (service.depositar(id, valor)) {
	 * return ResponseEntity.ok().build(); } return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); }
	 * 
	 * @PostMapping("/sacar/{id}/{valor}") public ResponseEntity<?>
	 * sacar(@PathVariable("id") Long id,
	 * 
	 * @PathVariable("valor") Float valor) { if (service.sacar(id, valor)) { return
	 * ResponseEntity.ok().build(); } return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); }
	 */

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Conta conta) {
		if (service.update(conta)) {
			return ResponseEntity.ok(conta);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping(value = "/agencia/{agencia}")
	public ResponseEntity<List<Conta>> getByAgencia(@PathVariable("agencia") Integer agencia) {
		return ResponseEntity.ok(service.listarPorAgencia(agencia));
	}

	@GetMapping(value = "/agencia/{agencia}/{from}/{to}")
	public ResponseEntity<List<Conta>> getByAgenciaESaldo(@PathVariable("agencia") Integer agencia,
			@PathVariable("from") Float from, @PathVariable("to") Float to) {
		return ResponseEntity.ok(service.listarPorAgenciaESaldo(agencia, from, to));
	}

	@GetMapping(value = "/cliente/{nome}")
	public ResponseEntity<List<Conta>> getByNomeCliente(@PathVariable("nome") String nome) {
		return ResponseEntity.ok(service.listarPorNomeCliente(nome));
	}
}
