package tdsif.turmas.turmas.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tdsif.turmas.turmas.domain.dto.NovoAluno;
import tdsif.turmas.turmas.domain.dto.AlunoDTO;
import tdsif.turmas.turmas.domain.entity.Aluno;
import tdsif.turmas.turmas.domain.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository AlunoRepository;

    public AlunoService(AlunoRepository AlunoRepository) {
        this.AlunoRepository = AlunoRepository;
    }

    public AlunoDTO converte(Aluno aluno) {
        AlunoDTO dto = new AlunoDTO();
        BeanUtils.copyProperties(aluno, dto);
        return dto;
    }

    public AlunoDTO findById(Integer id) {
        Optional<Aluno> AlunoOptional = AlunoRepository.findById(id);
        if (AlunoOptional.isPresent()) {
            Aluno aluno = AlunoOptional.get();
            return converte(aluno);
        } else {
            return null;
        }
    }

    public List<AlunoDTO> findAll() {
        return AlunoRepository.findAll().stream().map(this::converte).collect(Collectors.toList());
    }  

    public AlunoDTO save(NovoAluno novoAluno) {         
        Aluno aluno = new Aluno();
        BeanUtils.copyProperties(novoAluno, aluno);    
        aluno = AlunoRepository.save(aluno);
        AlunoDTO dto = new AlunoDTO();
        dto = this.converte(aluno);        
        return dto;
    }

    public Aluno findByIdObject(Integer id) {
        Optional<Aluno> AlunoOptional = AlunoRepository.findById(id);
        if (AlunoOptional.isPresent()) {
            Aluno aluno = AlunoOptional.get();
            return aluno;
        } else {
            return null;
        }
    }

    public void update(Aluno aluno) {
        AlunoRepository.save(aluno);
    }

}
