package com.medicamento.controle.auth;


import com.medicamento.controle.execptions.AuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    private final AuthRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthRepository repository,PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(()-> new AuthException("Usuário não existe"));
    }


    public AuthEntity validarLogin(AuthEntity credencials){

        System.out.println(credencials.getUsername());
        System.out.println(credencials.getPassword());

        AuthEntity usuario = repository.findByUsername(credencials.getUsername()).orElseThrow(() -> new AuthException("Usuário não existe"));

        if(!passwordEncoder.matches(credencials.getPassword(), usuario.getPassword())){
            throw new AuthException("Senha incorreta");
        }

        return usuario;
    }

    public  void novoUser(AuthEntity novoUsuario){

        if(repository.findByUsername(novoUsuario.getUsername()).isPresent()){
            throw  new AuthException("Nome de usuário já existe, não é possivel criar");
        }
        AuthEntity usuario = new AuthEntity();
        usuario.setUsername(novoUsuario.getUsername());
        usuario.setRole(novoUsuario.getRole());

        usuario.setPassword(passwordEncoder.encode(novoUsuario.getPassword()));

        repository.save(usuario);

    }


    public List<AuthEntity> buscarUsuarios(){
        return repository.findAll();
    }

    public AuthEntity buscarUsuario(String name) {

        return repository.findByUsername(name).orElseThrow(()->new AuthException("usuario nao existe"));
    }
}
