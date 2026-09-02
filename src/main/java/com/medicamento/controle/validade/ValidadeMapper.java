package com.medicamento.controle.validade;

import com.medicamento.controle.execptions.ValidadeException;
import com.medicamento.controle.medicamentos.MedicamentoMapper;
import com.medicamento.controle.medicamentos.MedicamentoService;
import com.medicamento.controle.validade.dtos.ValidadeCreateDTO;
import com.medicamento.controle.validade.dtos.ValidadeDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class ValidadeMapper {

    private final MedicamentoMapper medicamentoMapper;
    private final MedicamentoService medicamentoService;

    public ValidadeMapper(MedicamentoMapper medicamentoMapper,MedicamentoService medicamentoService){
        this.medicamentoMapper = medicamentoMapper;
        this.medicamentoService = medicamentoService;
    }

    public ValidadeDTO toDto(ValidadeEntity entity){

        return new ValidadeDTO(
                entity.id,
                entity.lote,
                entity.dataVencimento,
                ChronoUnit.DAYS.between(LocalDate.now(), entity.getDataVencimento()),
                medicamentoMapper.toDto(entity.medicamento)
        );
    }

    public ValidadeEntity toEntity(ValidadeDTO dto){

        ValidadeEntity entity =  new ValidadeEntity();

            entity.setId(dto.id());
            entity.setLote(dto.lote());
            entity.setDataVencimento(dto.data_vencimento());
            entity.setMedicamento(medicamentoMapper.toEntity(dto.medicamento()));

        return entity;
    }


    public ValidadeEntity toSaveEntity(ValidadeCreateDTO dto){

        ValidadeEntity entity =  new ValidadeEntity();

        entity.setLote(dto.lote());
        entity.setDataVencimento(dto.data_vencimento());
        entity.setMedicamento(medicamentoService.findById(dto.medicamentoId()).orElseThrow(()->new ValidadeException("Validade, medicamento nao existe")));

        return entity;
    }
}
