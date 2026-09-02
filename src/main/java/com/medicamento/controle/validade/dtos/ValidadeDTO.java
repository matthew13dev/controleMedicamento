package com.medicamento.controle.validade.dtos;

import com.medicamento.controle.medicamentos.dtos.MedicamentoDTO;

import java.time.LocalDate;

public record ValidadeDTO(
        Long id,
        String lote,
        LocalDate data_vencimento,
        Long dias_restantes,
        MedicamentoDTO medicamento
) {
}
