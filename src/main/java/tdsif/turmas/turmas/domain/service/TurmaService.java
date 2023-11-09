package tdsif.turmas.turmas.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tdsif.turmas.turmas.domain.dto.NovaTurma;
import tdsif.turmas.turmas.domain.dto.TurmaDTO;
import tdsif.turmas.turmas.domain.entity.Turma;
import tdsif.turmas.turmas.domain.repository.TurmaRepository;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    private TurmaDTO converte(Turma turma) {
        TurmaDTO dto = new TurmaDTO();
        BeanUtils.copyProperties(turma, dto);
        return dto;
    }

    public TurmaDTO findById(String id) {
        Optional<Turma> turmaOptional = turmaRepository.findById(id);
        if (turmaOptional.isPresent()) {
            Turma turma = turmaOptional.get();
            return converte(turma);
        } else {
            return null;
        }
    }

    public List<TurmaDTO> findAll() {
        return turmaRepository.findAll().stream().map(this::converte).collect(Collectors.toList());
    }

    public List<TurmaDTO> findByAno(String ano) {
        return turmaRepository.findByAno(ano).stream().map(this::converte).collect(Collectors.toList());
    }

    public List<TurmaDTO> findBySigla(String sigla) {
        return turmaRepository.findBySigla(sigla).stream().map(this::converte).collect(Collectors.toList());
    }

    public TurmaDTO save(Turma turma) {
        if(turmaRepository.existsById(turma.getId())){
            throw new DataIntegrityViolationException("ja turma com esse codigo, verifique a sigla, ano e semestre");
        }
        turma = turmaRepository.save(turma);
        TurmaDTO dto = new TurmaDTO();
        dto = this.converte(turma);        
        return dto;
    }

}
