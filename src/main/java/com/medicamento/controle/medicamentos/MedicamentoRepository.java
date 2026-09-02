package com.medicamento.controle.medicamentos;

import com.medicamento.controle.medicamentos.enums.CLASSIFICAO;
import com.medicamento.controle.medicamentos.enums.TIPO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MedicamentoRepository  extends JpaRepository<MedicamentoEntity, Long> {



    List<MedicamentoEntity> findByClassificacao(CLASSIFICAO classificacao);

    List<MedicamentoEntity> findByTipo(TIPO tipo);

    List<MedicamentoEntity> findByDescricaoContainingIgnoreCase(String descricao);

    List<MedicamentoEntity> findByEan(String ean);


    @Modifying
    @Transactional
    @Query("UPDATE MedicamentoEntity SET registro_anvisa = :registro WHERE ean = :ean")
    int updateRegistroAnviza(@Param("ean") String ean, @Param("registro") String registro);
}
