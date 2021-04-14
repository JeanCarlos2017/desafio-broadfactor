package com.broadfactor.domain.service;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.broadfactor.domain.exception.CadastroException;
import com.broadfactor.domain.exception.EntidadeNaoEncontradaException;
import com.broadfactor.domain.model.UsuarioEntidade;
import com.broadfactor.domain.model.UsuarioLogin;
import com.broadfactor.domain.respositorio.UsuarioRepositorio;



@Service
public class UsuarioService {
	//validação de email
	private static final String EMAIL_PATTERN = 
	        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
	@Autowired
	private UsuarioRepositorio usuarioRepository;
	
	private boolean validaEmail(String email) {
		Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	}

	private boolean validaNome(String nome) {
		//verifica se já existe um usuário com esse nome
		Optional<UsuarioEntidade> encontrou= usuarioRepository.findByNome(nome);
		if(encontrou.isEmpty()) return true;
		else throw new EntidadeNaoEncontradaException("Nome de usuário já está em uso, por favor escolha outro");
		
	}

	public UsuarioEntidade cadastraUsuario(UsuarioEntidade usuario) {
		//verifico se o emial já existe 
		if(this.usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
			throw new CadastroException("Email já existente, por favor tente outro!");
		}
		//verifica se o email e nome sao válidos 
		if(this.validaEmail(usuario.getEmail()) && this.validaNome(usuario.getNome())) {
			//criptografa a senha do usuário 
			BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
			String senhaEncoder= encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);
			//por fim salva o usuário
			return usuarioRepository.save(usuario);
		}else return null;
	}

	public Optional<UsuarioLogin> login(Optional<UsuarioLogin> userParametro) {
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		//busca o usuário no banco de dados pelo nome, como o nome é unico não há conflitos
		Optional<UsuarioEntidade> usuario= usuarioRepository.findByNome(userParametro.get().getNome());
		if(usuario.isPresent()) {
			//verifica se a senha do usuário é igual a senha do banco, faz essa verificação considerando a criptografia
			if(encoder.matches(userParametro.get().getSenha(), usuario.get().getSenha())) {
				//gero o token do usuário
				String auth= userParametro.get().getNome() + ":"+ userParametro.get().getSenha();
				byte[] encondeAuth= Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader= "Basic "+new String(encondeAuth);
				//colocando as informações de usuário no userParametro - que é o usuário para login 
				userParametro.get().setToken(authHeader);
				userParametro.get().setEmail(usuario.get().getEmail());
				userParametro.get().setNome(usuario.get().getNome());
				userParametro.get().setId(usuario.get().getId_usuario());
				return userParametro;				
			}else {
				throw new EntidadeNaoEncontradaException("Senha incorreta");
			}
		}else {
			throw new EntidadeNaoEncontradaException("Nome de usuário não encontrado, "
					+ "por favor verifique se o mesmo está correto");
		}
	}

	public void logout(UsuarioEntidade usuario) {

	}

	public UsuarioRepositorio getUsuarioRepository() {
		return usuarioRepository;
	}

	public UsuarioEntidade getById(long id) {
		Optional<UsuarioEntidade> user= this.usuarioRepository.findById(id);
		if(user.isPresent()) return user.get();
		else throw new EntidadeNaoEncontradaException("id_usuário de usuário não encontrado");
	}
	
	public UsuarioEntidade alteraUsuario(UsuarioEntidade user, long id_usuario) {
		Optional<UsuarioEntidade> encontrou= usuarioRepository.findByNome(user.getNome());
		//para garantir que será passado o id usuário
		user.setId_usuario(id_usuario);
		if(encontrou.isPresent() && encontrou.get().getId_usuario() != user.getId_usuario()) {
			//está tentando mudar o nome para um usuário que já existe 
			throw new CadastroException("nome de usuário já existente, por favor tente outro!");
		}
		//verifica email
		encontrou= usuarioRepository.findByEmail(user.getEmail());
		if(encontrou.isPresent() && encontrou.get().getId_usuario() != user.getId_usuario()) {
			//está tentando mudar o email para um email que já existe em outro usuário
			throw new CadastroException("E-mail já existente, por favor tente outro!");
		}
		
		if(!this.validaEmail(user.getEmail())) {
			throw new CadastroException("e-mail inválido, por favor verifique");
		}
		//passou nas verificações então começo o processo para salvar o usuário 
		//criptografa a senha do usuário 
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		String senhaEncoder= encoder.encode(user.getSenha());
		user.setSenha(senhaEncoder);
		//por fim salva o usuário
		return this.usuarioRepository.save(user);
	}
}