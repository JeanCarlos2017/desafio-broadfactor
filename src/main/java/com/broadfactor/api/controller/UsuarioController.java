package com.broadfactor.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.broadfactor.domain.model.EmpresaEntidade;
import com.broadfactor.domain.model.UsuarioEntidade;
import com.broadfactor.domain.model.UsuarioLogin;
import com.broadfactor.domain.service.EmpresaService;
import com.broadfactor.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping("/listar")
	public ResponseEntity<List<UsuarioEntidade>> getAll(){
		return ResponseEntity.ok(usuarioService.getUsuarioRepository().findAll());
	}
	
	@GetMapping("/buscaPorId/{id}")
	public ResponseEntity<UsuarioEntidade> findById(@PathVariable long id){
		return ResponseEntity.ok(usuarioService.getById(id));
	}
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioEntidade> postUsuario(@Valid @RequestBody UsuarioEntidade usuario){
		UsuarioEntidade user = usuarioService.cadastraUsuario(usuario);
		return this.valida(user, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UsuarioLogin> logar(@Valid @RequestBody Optional<UsuarioLogin> userParam){
		return usuarioService.login(userParam).map(resp -> ResponseEntity.ok(resp))
									.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<UsuarioEntidade> putUsuario(@Valid @RequestBody UsuarioEntidade usuario, 
			@PathVariable long id){
		UsuarioEntidade user = usuarioService.alteraUsuario(usuario, id);
		return this.valida(user, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable long id){
		this.usuarioService.deleteUsuario(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	private ResponseEntity<UsuarioEntidade> valida(UsuarioEntidade user, HttpStatus status){
		if (user == null) return ResponseEntity.badRequest().build();
		else return ResponseEntity.status(status).body(user);
	}
	
	@GetMapping("/get/empresa/{cnpj}")
	public ResponseEntity<EmpresaEntidade> getCNPJTeste2(@PathVariable String cnpj) throws Exception{
		EmpresaEntidade dadosEmpresa= this.empresaService.buscaEmpresaPeloCNPJ(cnpj);
		if (dadosEmpresa == null) return ResponseEntity.badRequest().build();
		else return ResponseEntity.status(HttpStatus.OK).body(dadosEmpresa);
	}
}
