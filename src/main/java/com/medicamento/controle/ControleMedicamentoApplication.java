package com.medicamento.controle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControleMedicamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControleMedicamentoApplication.class, args);

        int porta = 8081;
        System.out.println("Sistema rodando :) na porta: " + porta);
    }

}
