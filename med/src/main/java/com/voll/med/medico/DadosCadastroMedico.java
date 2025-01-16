package com.voll.med.medico;

import com.voll.med.endereco.DadosEndereco;

public record DadosCadastroMedico(
    String nome, 
    String email, 
    String crm, 
    Especialidades especialidade, 
    DadosEndereco endereco ) {

}
