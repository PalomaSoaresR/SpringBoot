package com.voll.med.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voll.med.medico.AtualizarMedicos;
import com.voll.med.medico.DadosCadastroMedico;
import com.voll.med.medico.ListarMedicos;
import com.voll.med.medico.Medico;
import com.voll.med.medico.MedicoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    
    //injeção de dependencias - indica que o spring precisa instanciar automaticamente
    @Autowired
    private MedicoRepository repository;

    
    //usando o verbo post
    @PostMapping
    @Transactional
    //usamos request body para receber o objeto json, pois sem ele é devolvido null, 
    //aqui ele entende que é para ler o "body" da requisição
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    //np PageabreDefault é definido a quant, pagina e ordenação, se não vier na url da req
    public Page<ListarMedicos> listar(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(ListarMedicos::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizarMedicos dados) {
    var medico = repository.getReferenceById(dados.id());
    medico.atualizarInformacoes(dados);
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
 