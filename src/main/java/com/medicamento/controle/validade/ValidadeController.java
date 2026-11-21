package com.medicamento.controle.validade;


import com.medicamento.controle.auth.AuthEntity;
import com.medicamento.controle.validade.dtos.ValidadeCreateDTO;
import com.medicamento.controle.validade.dtos.ValidadeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/validade")
public class ValidadeController {


    private final ValidadeService service;

    public ValidadeController(ValidadeService service){

        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<ValidadeDTO>> buscarTodos( @AuthenticationPrincipal AuthEntity usuarioLogado){
        return ResponseEntity.ok().body(service.buscarTodos(usuarioLogado.getId()));
    }

    @GetMapping("/10dias")
    public ResponseEntity<List<ValidadeDTO>> buscar10dias( @AuthenticationPrincipal AuthEntity usuarioLogado){
        return ResponseEntity.ok().body(service.buscar10Dias(usuarioLogado.getId()));
    }

    @PostMapping
    public ResponseEntity<ValidadeDTO> criar(@RequestBody ValidadeCreateDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ValidadeDTO> atualizar(@PathVariable Long id,@RequestBody ValidadeCreateDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.atualizar(id,dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletar(@PathVariable Long id, @AuthenticationPrincipal AuthEntity usuarioLogado){
        service.deletar(id, usuarioLogado.getId());
        return ResponseEntity.noContent().build();
    }

}
