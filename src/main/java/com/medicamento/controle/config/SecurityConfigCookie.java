package com.medicamento.controle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class SecurityConfigCookie {

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setSameSite("None"); // Permite requisições cruzadas (Front na Vercel + Backend externo)
        serializer.setUseSecureCookie(true); // Exige HTTPS (obrigatório em produção)
        return serializer;
    }
}
