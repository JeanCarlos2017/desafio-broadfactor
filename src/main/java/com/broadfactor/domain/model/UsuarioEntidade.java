package com.broadfactor.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity @Table(name="tb_usuario")
public class UsuarioEntidade {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_usuario;
	
	@NotBlank(message = "{email.not.blank}")
	@Email(message = "{email.not.valid}")
	private String email;
	
	@NotBlank(message = "{senha.not.blank}")
	private String senha;
	
	@NotBlank(message = "{name.not.blank}")
	private String nome;
	
	@NotBlank(message = "{cnpj.not.blank}")
	private String cnpj;
	
	@OneToOne(cascade = CascadeType.ALL)
	private EmpresaEntidade empresa;
	
	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public EmpresaEntidade getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaEntidade empresa) {
		this.empresa = empresa;
	}
	
	
	
}
