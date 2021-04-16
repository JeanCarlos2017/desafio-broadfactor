package com.broadfactor.domain.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.broadfactor.domain.exception.RequisicaoException;
import com.broadfactor.domain.model.AtividadePrincipalEntidade;
import com.broadfactor.domain.model.EmpresaEntidade;
import com.broadfactor.domain.respositorio.EmpresaRepositorio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class EmpresaService {
	static String webService = "https://receitaws.com.br/v1/cnpj/";
	static int codigoSucesso = 200;
	
	@Autowired
	private EmpresaRepositorio empresaRepositorio;
	@Autowired
	private AtividadeService atividadeService;

	private EmpresaEntidade buscaEmpresaPeloCNPJ(String cnpj) throws Exception {
		String urlParaChamada = webService + cnpj;

		try {
			URL url = new URL(urlParaChamada);
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

			if (conexao.getResponseCode() != codigoSucesso)
				throw new RequisicaoException("HTTP error code :  + conexao.getResponseCode()");

			BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
			String jsonEmString = Util.converteJsonEmString(resposta);
			
			//em caso de erro
			if(jsonEmString.indexOf("ERROR") != -1) {
				return null;
			}
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
			EmpresaEntidade empresaEntidade = gson.fromJson(jsonEmString, EmpresaEntidade.class);
			
			return empresaEntidade;
		} catch (Exception e) {
			// throw new RequisicaoException("Erro de requisição: "+ e);
			return null;
		}
	}

	public EmpresaEntidade salvarEmpresa(String cnpj) throws Exception {
		Optional<EmpresaEntidade> buscaNoJPA = this.empresaRepositorio.findByCnpj(cnpj);
		if (buscaNoJPA.isPresent()) {
			// já existe uma empresa com esse cnpj no banco
			return buscaNoJPA.get();
			
		} else {
			// tenta fazer a busca da empresa na api
			EmpresaEntidade empresa = this.buscaEmpresaPeloCNPJ(cnpj);
			
			if(empresa != null) {
				//o cnpj vai ter apenas números 
				empresa.setCnpj(cnpj);
				//salva a empresa sem as atividades
				empresa= this.empresaRepositorio.save(empresa);
				//salva as atividades da empresa no banco
				List<AtividadePrincipalEntidade> atividadeList= this.atividadeService.saveAtividadeDaEmpresa(empresa);
				//ligação entre empresa e atividade
				empresa.setAtividade_principal(atividadeList);
				empresa= this.empresaRepositorio.save(empresa);
			}
			return empresa;
		}
	}
}
