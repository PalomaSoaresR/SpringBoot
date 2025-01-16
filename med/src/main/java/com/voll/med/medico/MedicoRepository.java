package com.voll.med.medico;

import org.springframework.data.jpa.repository.JpaRepository;

//a interface herda a interface JpaRepository definindo a entidade (Medico) e a chave primária (long id)
public interface MedicoRepository extends JpaRepository<Medico,Long>{
    
}
