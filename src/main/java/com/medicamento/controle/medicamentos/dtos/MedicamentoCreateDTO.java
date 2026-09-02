package com.medicamento.controle.medicamentos.dtos;

public record MedicamentoCreateDTO(
        String ean,
        String descricao,
        String fabricante,
        String tipo,
        String classificacao,
        String principio_ativo,
        String registro_anviza

) {
}
