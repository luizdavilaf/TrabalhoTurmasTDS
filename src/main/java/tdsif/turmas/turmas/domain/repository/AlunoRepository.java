package tdsif.turmas.turmas.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import tdsif.turmas.turmas.domain.entity.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Integer>{
    List<Aluno> findAll();

    Optional<Aluno> findById(Integer id);
    
}
