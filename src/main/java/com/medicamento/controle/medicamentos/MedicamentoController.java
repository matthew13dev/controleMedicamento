package com.medicamento.controle.medicamentos;


import com.medicamento.controle.medicamentos.dtos.MedicamentoCreateDTO;
import com.medicamento.controle.medicamentos.dtos.MedicamentoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {


    private final MedicamentoService service;

    public MedicamentoController(MedicamentoService service){
        this.service = service;
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<MedicamentoDTO>> buscarTodos(){
        return ResponseEntity.ok().body(service.buscarTodos());
    }

    @GetMapping("/classificacao")
    public ResponseEntity<List<MedicamentoDTO>> medicamentosClassificacao(@RequestParam String classificacao){
        return ResponseEntity.ok(service.buscarByClassificacao(classificacao));
    }


    @GetMapping("/tipo")
    public ResponseEntity<List<MedicamentoDTO>> medicamentoTipo(@RequestParam String tipo){
        return ResponseEntity.ok(service.buscarTipo(tipo));
    }

    @GetMapping("/descricao")
    public ResponseEntity<List<MedicamentoDTO>> buscarDescricao(@RequestParam String descricao){
        return ResponseEntity.ok().body(service.buscarDescricao(descricao));
    }

    @GetMapping("/ean")
    public ResponseEntity<List<MedicamentoDTO>> buscarEan(@RequestParam String ean){
        return ResponseEntity.ok().body(service.buscarEan(ean));
    }


    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDTO> buscarId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.buscarId(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedicamentoDTO> novoMedicamento(@RequestBody MedicamentoCreateDTO dto){
        return ResponseEntity.ok().body(service.novoMedicamento(dto));
    }


}
