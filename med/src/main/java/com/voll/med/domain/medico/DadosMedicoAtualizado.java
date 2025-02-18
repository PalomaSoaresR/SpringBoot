package com.voll.med.domain.medico;

import com.voll.med.domain.endereco.Endereco;

public record DadosMedicoAtualizado(
    Long id,
    String nome,
    String email,
    String crm,
    String telefone,
    Especialidades especialidades,
    Endereco endereco
) {
    public DadosMedicoAtualizado(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
