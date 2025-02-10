package com.voll.med.domain.medico;

import com.voll.med.domain.endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record AtualizarMedicos
(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
    
}
