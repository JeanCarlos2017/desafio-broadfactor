package com.broadfactor.domain.service;

import org.springframework.stereotype.Service;

import com.broadfactor.domain.exception.RequisicaoException;
import com.broadfactor.domain.model.EmpresaEntidade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class EmpresaService {
	static String webService = "https://receitaws.com.br/v1/cnpj/";
	static int codigoSucesso = 200;
	
	public  EmpresaEntidade buscaEmpresaPeloCNPJ(String cnpj) throws Exception {
        String urlParaChamada = webService + cnpj;

        try {
            URL url = new URL(urlParaChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            if (conexao.getResponseCode() != codigoSucesso)
                throw new RequisicaoException("HTTP error code :  + conexao.getResponseCode()");

            BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            String jsonEmString = Util.converteJsonEmString(resposta);
            System.out.println(jsonEmString);
            
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        		    .create();
            EmpresaEntidade empresaEntidade = gson.fromJson(jsonEmString, EmpresaEntidade.class);

            return empresaEntidade;
        } catch (Exception e) {
        	throw new RequisicaoException("Erro de requisição: "+ e);
        }
    }
}
