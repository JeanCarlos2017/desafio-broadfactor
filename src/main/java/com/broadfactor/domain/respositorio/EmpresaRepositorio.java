package com.broadfactor.domain.respositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.broadfactor.domain.model.EmpresaEntidade;

public interface EmpresaRepositorio extends JpaRepository<EmpresaEntidade, Long> {
	public EmpresaEntidade findByCnpj(String cnpj);
}
