package com.medicamento.controle.medicamentos;

import com.medicamento.controle.medicamentos.dtos.MedicamentoCreateDTO;
import com.medicamento.controle.medicamentos.dtos.MedicamentoDTO;
import com.medicamento.controle.medicamentos.enums.CLASSIFICAO;
import com.medicamento.controle.medicamentos.enums.TIPO;
import org.springframework.stereotype.Component;

@Component
public class MedicamentoMapper {


    public MedicamentoEntity toEntity(MedicamentoDTO dto){
        MedicamentoEntity entity = new MedicamentoEntity();


        entity.setId(dto.id());
        entity.setEan(dto.ean());
        entity.setDescricao(dto.descricao());
        entity.setFabricante(dto.fabricante());
        entity.setTipo(TIPO.valueOf(dto.tipo()));
        entity.setClassificacao(CLASSIFICAO.valueOf(dto.classificacao()));

        return entity;
    }


    public MedicamentoDTO toDto(MedicamentoEntity entity){

        return new MedicamentoDTO(
                entity.getId(),
                entity.getEan(),
                entity.getDescricao(),
                entity.getFabricante(),
                entity.getTipo().toString(),
                entity.getClassificacao().toString(),
                entity.getPrincipio_ativo(),
                entity.getRegistro_anvisa()
        );
    }

    public MedicamentoEntity toSaveEntity(MedicamentoCreateDTO dto){
        MedicamentoEntity entity = new MedicamentoEntity();

        entity.setEan(dto.ean());
        entity.setDescricao(dto.descricao());
        entity.setFabricante(dto.fabricante());
        entity.setTipo(TIPO.valueOf(dto.tipo().toUpperCase()));
        entity.setClassificacao(CLASSIFICAO.valueOf(dto.classificacao().toUpperCase()));
        entity.setPrincipio_ativo(dto.principio_ativo());
        entity.setRegistro_anvisa(dto.registro_anviza());

        return entity;
    }


}
