package com.broadfactor.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.broadfactor.domain.model.AtividadePrincipalEntidade;
import com.broadfactor.domain.model.EmpresaEntidade;
import com.broadfactor.domain.respositorio.AtividadeRepositorio;

@Service
public class AtividadeService {
	@Autowired
	private AtividadeRepositorio atividadeRepositorio;
	
	private AtividadePrincipalEntidade saveEntidade( AtividadePrincipalEntidade atividade, EmpresaEntidade empresa) {
		//salva a entidade 
		atividade= this.atividadeRepositorio.save(atividade);
		//faz a ligação entre a entidade e a empresa 
		atividade.setEmpresa(empresa);
		return this.atividadeRepositorio.save(atividade);
	}
	
	public List<AtividadePrincipalEntidade> saveAtividadeDaEmpresa(EmpresaEntidade empresa) {
		List<AtividadePrincipalEntidade> atividadeList= new ArrayList<AtividadePrincipalEntidade>();
		for(AtividadePrincipalEntidade atividade: empresa.getAtividade_principal()) {
			atividadeList.add(this.saveEntidade(atividade, empresa));
		}
		return atividadeList;
	}
}
