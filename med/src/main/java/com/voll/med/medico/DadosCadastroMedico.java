package com.voll.med.medico;

import com.voll.med.endereco.DadosEndereco;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
    //notações usafas para validar os dados usando o Bean Validation
    @NotBlank
    String nome,

    @NotBlank
    @Email 
    String email, 

    @NotBlank
    String telefone,

    @NotBlank
    //validar o digito de 4 a 6 do crm
    @Pattern(regexp = "\\d{4,6}")
    String crm, 

    @NotNull
    Especialidades especialidade, 
    DadosEndereco endereco ) {

}
