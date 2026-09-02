package com.medicamento.controle.validade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ValidadeRepository  extends JpaRepository<ValidadeEntity, Long> {
}
