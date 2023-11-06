package tdsif.turmas.turmas.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tdsif.turmas.turmas.domain.entity.Turma;

public interface TurmaRepository extends CrudRepository<Turma, String>{
    List<Turma> findAll();

    Optional<Turma> findById(String id);
    List<Turma> findBySigla(String sigla);
    List<Turma> findByAno(String ano);
}
