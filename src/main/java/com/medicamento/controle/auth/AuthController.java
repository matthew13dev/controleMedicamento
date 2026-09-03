package com.medicamento.controle.auth;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastro(@RequestBody AuthEntity usuario){

        service.novoUser(usuario);
        return ResponseEntity.ok().body("Registrado com Sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> validarLogin(@RequestBody AuthEntity credencils, HttpServletRequest request, HttpServletResponse response) {

        AuthEntity usuario = service.validarLogin(credencils);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                usuario,
                null,
                usuario.getAuthorities());


        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authenticationToken);
        SecurityContextHolder.setContext(securityContext);

        HttpSessionSecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
        securityContextRepository.saveContext(securityContext,request,response);



        return ResponseEntity.ok("Login realizado com sessão criada com sucesso!");
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AuthEntity> obterUsuarioLogado(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).build();
        }

        // Busca direto no repositório pelo username que veio da sessão
        AuthEntity usuario = service.buscarUsuario(principal.getName());

        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/usuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AuthEntity>> buscarUsuarios(){

        return ResponseEntity.ok().body(service.buscarUsuarios());
    }
}
