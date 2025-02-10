 package com.voll.med.domain.medico;

public record ListarMedicos(Long id, String nome, String email, String crm, Especialidades especialidades) {
   
    //construtor que recebe os dados do medico
    public ListarMedicos(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}