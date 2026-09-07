package com.medicamento.controle.config;


import com.medicamento.controle.auth.AuthEntity;
import com.medicamento.controle.auth.AuthRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInicializerConfig {


    @Bean
    CommandLineRunner initAdmin(AuthRepository authRepository, PasswordEncoder passwordEncoder){

        return  args -> {

            if(authRepository.count() == 0){

                AuthEntity adminb = new AuthEntity();

                adminb.setUsername("admin");
                adminb.setPassword(passwordEncoder.encode("admin123"));
                adminb.setRole("ADMIN");


                authRepository.save(adminb);
                System.out.println(">>>>>>>>>Usuário ADMIN criado automaticamente com SUCESSO!");
            }
        };

    }
}
