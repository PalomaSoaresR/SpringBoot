package com.voll.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voll.med.medico.DadosCadastroMedico;
import com.voll.med.medico.Medico;
import com.voll.med.medico.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    
    //injeção de dependencias - indica que o spring precisa instanciar automaticamente
    @Autowired
    private MedicoRepository repository;

    
    //usando o verbo post
    @PostMapping
    //usamos request body para receber o objeto json, pois sem ele é devolvido null, 
    //aqui ele entende que é para ler o "body" da requisição
    public void cadastrar(@RequestBody DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }
}
