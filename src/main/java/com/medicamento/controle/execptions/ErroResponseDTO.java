package com.medicamento.controle.execptions;

public record ErroResponseDTO(
        String mensagem,
        Integer codigo
) {
}
