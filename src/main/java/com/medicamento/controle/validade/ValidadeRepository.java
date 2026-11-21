package com.medicamento.controle.validade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ValidadeRepository  extends JpaRepository<ValidadeEntity, Long> {

    List<ValidadeEntity> findByUsuarioId(Long usuarioId);
    Optional<ValidadeEntity> findByIdAndUsuarioId(Long id, Long usuarioId);
}
