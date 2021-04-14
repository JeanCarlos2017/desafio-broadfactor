package com.broadfactor.domain.respositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.broadfactor.domain.model.UsuarioEntidade;

@Repository
public interface UsuarioRepositorio extends JpaRepository<UsuarioEntidade, Long> {

	Optional<UsuarioEntidade> findByNome(String userName);


}
