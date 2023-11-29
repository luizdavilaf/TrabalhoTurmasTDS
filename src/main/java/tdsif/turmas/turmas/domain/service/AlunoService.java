package tdsif.turmas.turmas.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tdsif.turmas.turmas.domain.dto.NovoAluno;
import tdsif.turmas.turmas.domain.dto.TurmaDTO;
import tdsif.turmas.turmas.domain.dto.AlunoDTO;
import tdsif.turmas.turmas.domain.entity.Aluno;
import tdsif.turmas.turmas.domain.entity.Turma;
import tdsif.turmas.turmas.domain.repository.AlunoRepository;
import tdsif.turmas.turmas.domain.repository.TurmaRepository;
@RequiredArgsConstructor
@Service
public class AlunoService {

    private final AlunoRepository AlunoRepository;
    private final TurmaRepository turmaRepository;
    


    public AlunoDTO converte(Aluno aluno) {
        AlunoDTO dto = new AlunoDTO();
        BeanUtils.copyProperties(aluno, dto);
        return dto;
    }

    public TurmaDTO converteTurma(Turma turma) {
        TurmaDTO dto = new TurmaDTO();
        BeanUtils.copyProperties(turma, dto);
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



    public List<TurmaDTO> getTurmas(Integer alunoId) throws Exception {
        Optional<Aluno> alunoOptional = AlunoRepository.findById(alunoId);
        List<TurmaDTO> turmaDTOs = new ArrayList<>();
        if(alunoOptional.isPresent()){
            Aluno aluno = alunoOptional.get();
            List<Turma> turmas = aluno.getTurmas();            
            if(turmas.size()==0){
                throw new Exception("turma vazia");
            }
            for (Turma turma : turmas) {
                turmaDTOs.add(converteTurma(turma));
            }
        }
        return turmaDTOs;
    }

}
