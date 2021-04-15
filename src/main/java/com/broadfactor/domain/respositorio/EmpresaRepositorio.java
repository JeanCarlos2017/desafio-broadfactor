package com.broadfactor.domain.respositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.broadfactor.domain.model.EmpresaEntidade;

public interface EmpresaRepositorio extends JpaRepository<EmpresaEntidade, Long> {
	public Optional<EmpresaEntidade> findByCnpj(String cnpj);
}
