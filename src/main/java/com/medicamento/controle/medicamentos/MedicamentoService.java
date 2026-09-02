package com.medicamento.controle.medicamentos;


import com.medicamento.controle.execptions.MedicamentoException;
import com.medicamento.controle.medicamentos.dtos.MedicamentoCreateDTO;
import com.medicamento.controle.medicamentos.dtos.MedicamentoDTO;
import com.medicamento.controle.medicamentos.enums.CLASSIFICAO;
import com.medicamento.controle.medicamentos.enums.TIPO;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class MedicamentoService {

    private final MedicamentoRepository repository;
    private final MedicamentoMapper mapper;


    public MedicamentoService(MedicamentoRepository repository,MedicamentoMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }


    protected List<MedicamentoDTO> buscarByClassificacao(String classificacao){

        try{

            CLASSIFICAO classficacaoEnum = CLASSIFICAO.valueOf(classificacao.toUpperCase());

            return repository.findByClassificacao(classficacaoEnum)
                    .stream()
                    .sorted(Comparator.comparing(MedicamentoEntity::getDescricao))
                    .map(mapper::toDto)
                    .toList();
        } catch (IllegalArgumentException ex){
            throw new MedicamentoException("Classificao não Existe: "+ classificacao);
        }

    }

    protected List<MedicamentoDTO> buscarTipo(String tipo){



        try {

            TIPO tipoEnum = TIPO.valueOf(tipo.toUpperCase());

            return repository.findByTipo(tipoEnum)
                    .stream()
                    .sorted(Comparator.comparing(MedicamentoEntity::getDescricao))
                    .map(mapper::toDto)
                    .toList();
        }catch (IllegalArgumentException ex){
            throw new MedicamentoException("TIPO não existe: " + tipo);
        }
    }

    protected List<MedicamentoDTO> buscarDescricao(String descricao){

        return repository.findByDescricaoContainingIgnoreCase(descricao)
                .stream()
                .sorted(Comparator.comparing(MedicamentoEntity::getClassificacao))
                .map(mapper::toDto)
                .toList();
    }

    protected List<MedicamentoDTO> buscarEan(String ean){
        return repository.findByEan(ean)
                .stream()
                .sorted(Comparator.comparing(MedicamentoEntity::getDescricao))
                .map(mapper::toDto)
                .toList();
    }


    public MedicamentoDTO buscarId(Long id){
        Optional<MedicamentoEntity>  entity = repository.findById(id);
        return mapper.toDto(entity.orElseThrow(() -> new MedicamentoException("Falha ao buscar, ID não existe.")));
    }




    protected void deletar(Long id){

        if(!repository.existsById(id)){
            throw new MedicamentoException("Falha ao deletar, ID não exite.");
        }

        repository.deleteById(id);
    }


    protected MedicamentoDTO novoMedicamento(MedicamentoCreateDTO novoMedicmentoDTO){

        List<MedicamentoEntity> isExistsEan = repository.findByEan(novoMedicmentoDTO.ean());

        if(!isExistsEan.isEmpty()){
            throw new MedicamentoException("Medicamento com EAN já existe");
        }

        MedicamentoEntity entitySalva = repository.save(mapper.toSaveEntity(novoMedicmentoDTO));

        return mapper.toDto(entitySalva);
    }


    protected List<MedicamentoDTO> atualizarRegistroAnviza(String ean,String registro){

        int rowsEffect = repository.updateRegistroAnviza(ean, registro);

        if(rowsEffect == 0){
            throw new MedicamentoException("Não foi possivel atualizar registro, medicamento não encontrado");
        }

        return this.buscarEan(ean);
    }

    protected List<MedicamentoDTO> buscarTodos() {

        return repository.findAll()
                .stream()
                .sorted(Comparator.comparing(MedicamentoEntity::getDescricao))
                .map(mapper::toDto)
                .toList();
    }

    public Optional<MedicamentoEntity> findById(Long id){
        return this.repository.findById(id);
    }
}
