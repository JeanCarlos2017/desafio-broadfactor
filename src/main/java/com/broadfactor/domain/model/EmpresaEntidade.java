package com.broadfactor.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity @Table(name="tb_empresa")
public class EmpresaEntidade {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_empresa;
	
	@OneToMany(mappedBy= "empresa", cascade= CascadeType.REMOVE)
	@JsonIgnoreProperties("empresa")
	private List<AtividadePrincipalEntidade> atividade_principal= new ArrayList<>();
	
	private String data_situacao;
	
	private String nome;
	
	private String telefone;
	
	private String email;
	
	private String cep;
	
	private String situacao;
	
	private String fantasia;
	
	private String abertura;
	
	private String cnpj;
	

	public EmpresaEntidade() {
		// TODO Auto-generated constructor stub
	}

	
	
	public List<AtividadePrincipalEntidade> getAtividade_principal() {
		return atividade_principal;
	}

	public void setAtividade_principal(List<AtividadePrincipalEntidade> atividade_principal) {
		this.atividade_principal = atividade_principal;
	}

	public String getData_situacao() {
		return data_situacao;
	}

	public void setData_situacao(String data_situacao) {
		this.data_situacao = data_situacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getAbertura() {
		return abertura;
	}

	public void setAbertura(String abertura) {
		this.abertura = abertura;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public long getId_empresa() {
		return id_empresa;
	}
	
	
}
