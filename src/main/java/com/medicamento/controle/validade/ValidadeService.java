package com.medicamento.controle.validade;


import com.medicamento.controle.execptions.ValidadeException;
import com.medicamento.controle.medicamentos.MedicamentoEntity;
import com.medicamento.controle.medicamentos.MedicamentoService;
import com.medicamento.controle.validade.dtos.ValidadeCreateDTO;
import com.medicamento.controle.validade.dtos.ValidadeDTO;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ValidadeService {

    private final ValidadeRepository repository;
    private final ValidadeMapper mapper;

    private final MedicamentoService medicamentoService;

    public ValidadeService(ValidadeRepository repository, ValidadeMapper mapper,MedicamentoService medicamentoService){
        this.repository = repository;
        this.mapper = mapper;
        this.medicamentoService = medicamentoService;
    }


    protected List<ValidadeDTO> buscarTodos(Long usuarioId){

        return repository.findByUsuarioId(usuarioId)
                .stream()
                .map(mapper::toDto)
                .sorted(Comparator.comparing(ValidadeDTO::dias_restantes))
                .toList();
    }

    protected  List<ValidadeDTO> buscar10Dias(Long usuarioId){

        return repository.findByUsuarioId(usuarioId)
                .stream()
                .map(mapper::toDto)
                .sorted(Comparator.comparing(ValidadeDTO::dias_restantes))
                .filter(item->item.dias_restantes() <=10)
                .toList();

    }



    protected ValidadeDTO criar(ValidadeCreateDTO dto,Long usuarioId){

        if(usuarioId == null){
            throw new ValidadeException("usuario não existe para poder criar uma validade");
        }

        if(dto.data_vencimento() == null){
            throw new ValidadeException("Data de vencimetno não encontrada, dados incompletos");
        }

        MedicamentoEntity existente = medicamentoService.findById(dto.medicamentoId())
                .orElseThrow(()->new ValidadeException("Medicamento não Existe para ser validade"));

        ValidadeEntity entity = mapper.toSaveEntity(dto);
        entity.setUsuarioId(usuarioId);
        entity.setMedicamento(existente);

        repository.save(entity);
        return mapper.toDto(entity);
    }



    protected ValidadeDTO atualizar(Long id, ValidadeCreateDTO dto){

        ValidadeEntity existente = repository.findByIdAndUsuarioId(id, dto.usuarioId())
                .orElseThrow(()-> new ValidadeException("Validade não existe"));


        existente.setDataVencimento(dto.data_vencimento());
        existente.setLote(dto.lote());

        ValidadeEntity atualizada = repository.save(existente);
        return mapper.toDto(atualizada);
    }


    protected void deletar(Long id,Long usuarioId){

        ValidadeEntity existente = repository.findByIdAndUsuarioId(id,usuarioId)
                .orElseThrow(()->new ValidadeException("Validade não existe"));

        repository.deleteById(id);
    }

}
