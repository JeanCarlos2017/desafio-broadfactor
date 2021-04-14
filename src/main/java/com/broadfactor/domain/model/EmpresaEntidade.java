package com.broadfactor.domain.model;

import java.util.ArrayList;
import java.util.List;


public class EmpresaEntidade {
	private List<AtividadePrincipal> atividade_principal= new ArrayList<>();
	private String data_situacao;
	
	public List<AtividadePrincipal> getAtividade_principal() {
		return atividade_principal;
	}

	public void setAtividade_principal(List<AtividadePrincipal> atividade_principal) {
		this.atividade_principal = atividade_principal;
	}

	public String getData_situacao() {
		return data_situacao;
	}

	public void setData_situacao(String data_situacao) {
		this.data_situacao = data_situacao;
	}



	private class AtividadePrincipal{
		private String text;
		private String code;
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
		
	}


//"tipo": "MATRIZ",
//"nome": "PAULA CRISTINA PEREIRA FRANCA 36103245893",
//"telefone": "(11) 4636-7202",
//"email": "lellapiu90@gmail.com",
//"atividades_secundarias": [
//    {
//        "text": "Fornecimento de alimentos preparados preponderantemente para consumo domiciliar",
//        "code": "56.20-1-04"
//    },
//    {
//        "text": "Fabricação de produtos derivados do cacau e de chocolates",
//        "code": "10.93-7-01"
//    }
//],
//"situacao": "ATIVA",
//"bairro": "JARDIM NOVA POA",
//"logradouro": "RUA CAPITAO PEDRO ESPERIDIAO HOFFER",
//"numero": "150",
//"cep": "08.568-700",
//"municipio": "POA",
//"fantasia": "LELLA DOCES",
//"porte": "MICRO EMPRESA",
//"abertura": "09/07/2018",
//"natureza_juridica": "213-5 - Empresário (Individual)",
//"uf": "SP",
//"cnpj": "30.885.426/0001-30",
//"ultima_atualizacao": "2020-11-23T23:59:59.000Z",
//"status": "OK",
//"complemento": "",
//"efr": "",
//"motivo_situacao": "",
//"situacao_especial": "",
//"data_situacao_especial": "",
//"capital_social": "1000.00",
//"qsa": [],
//"extra": {},
//"billing": {
//    "free": true,
//    "database": true
//}
	public EmpresaEntidade() {
		// TODO Auto-generated constructor stub
	}

}
