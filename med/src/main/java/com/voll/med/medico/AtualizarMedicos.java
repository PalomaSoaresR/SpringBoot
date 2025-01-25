package com.voll.med.medico;

import com.voll.med.endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record AtualizarMedicos
(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
    
}
