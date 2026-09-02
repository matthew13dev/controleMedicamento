package com.medicamento.controle.auth;


import com.medicamento.controle.execptions.AuthException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


    public  void novoUser(String username,String password){

        AuthEntity usuario = new AuthEntity();
        usuario.setUsername(username);

        usuario.setPassword(passwordEncoder.encode(password));

        repository.save(usuario);

    }
}
