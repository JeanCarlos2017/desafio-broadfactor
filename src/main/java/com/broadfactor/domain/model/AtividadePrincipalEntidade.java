package com.broadfactor.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity @Table(name="tb_atividade")
public class AtividadePrincipalEntidade {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_atvidade;
	
	private String text;
	
	private String code;
	
	@ManyToOne
	@JsonIgnoreProperties("atividade_principal")
	private EmpresaEntidade empresa;
	
	public AtividadePrincipalEntidade() {}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public long getId_atvidade() {
		return id_atvidade;
	}

	public EmpresaEntidade getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaEntidade empresa) {
		this.empresa = empresa;
	}
	
	
	
}
