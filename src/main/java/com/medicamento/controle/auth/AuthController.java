package com.medicamento.controle.auth;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastro(@RequestBody AuthEntity usuario){

        service.novoUser(usuario.getUsername(),usuario.getPassword());
        return ResponseEntity.ok().body("Registrado com Sucesso!");
    }

    @GetMapping("/login")
    public ResponseEntity<String> validarLogin() {
        // Se o Spring Security deixou a requisição passar até aqui,
        // significa que o usuário e a senha enviados no header estão corretos!
        return ResponseEntity.ok("Login bem-sucedido!");
    }
}
