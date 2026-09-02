package com.medicamento.controle.validade.dtos;

import java.time.LocalDate;

public record ValidadeCreateDTO(
        String lote,
        LocalDate data_vencimento,
        Long medicamentoId
) {
}
