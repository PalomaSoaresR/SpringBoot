package com.voll.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.voll.med.domain.medico.AtualizarMedicos;
import com.voll.med.domain.medico.DadosCadastroMedico;
import com.voll.med.domain.medico.DadosMedicoAtualizado;
import com.voll.med.domain.medico.ListarMedicos;
import com.voll.med.domain.medico.Medico;
import com.voll.med.domain.medico.MedicoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    // injeção de dependencias - indica que o spring precisa instanciar
    // automaticamente
    @Autowired
    private MedicoRepository repository;

    // usando o verbo post
    @PostMapping
    @Transactional
    // usamos request body para receber o objeto json, pois sem ele é devolvido
    // null,
    // aqui ele entende que é para ler o "body" da requisição
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);
        var URI = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(URI).body(new DadosMedicoAtualizado(medico));
    }

    @GetMapping
    // no PageabreDefault é definido a quant, pagina e ordenação, se não vier na url
    // da req
    public ResponseEntity<Page<ListarMedicos>> listar(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(ListarMedicos::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizarMedicos dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosMedicoAtualizado(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosMedicoAtualizado(medico));
    }
}