package tdsif.turmas.turmas.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tdsif.turmas.turmas.domain.dto.AlunoDTO;
import tdsif.turmas.turmas.domain.dto.NovaTurma;
import tdsif.turmas.turmas.domain.dto.TurmaDTO;
import tdsif.turmas.turmas.domain.entity.Aluno;
import tdsif.turmas.turmas.domain.entity.Turma;
import tdsif.turmas.turmas.domain.repository.AlunoRepository;
import tdsif.turmas.turmas.domain.repository.TurmaRepository;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private AlunoService alunoService;

    public TurmaService(
            TurmaRepository turmaRepository,
            AlunoService alunoService) {
        this.turmaRepository = turmaRepository;
        this.alunoService = alunoService;
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

    public TurmaDTO save(NovaTurma novaturma) {
        Turma turma = new Turma();
        BeanUtils.copyProperties(novaturma, turma);
        turma.setId(turma.getSigla() + "-" + turma.getAno() + "-" + turma.getSemestre());
        if (turmaRepository.existsById(turma.getId())) {
            throw new DataIntegrityViolationException("ja turma com esse codigo, verifique a sigla, ano e semestre");
        }
        turma = turmaRepository.save(turma);
        TurmaDTO dto = new TurmaDTO();
        dto = this.converte(turma);
        return dto;
    }

    public TurmaDTO matricular(String turmaId, Integer alunoId) throws Exception {
        Optional<Turma> turmaOptional = turmaRepository.findById(turmaId);
        if (turmaOptional.isPresent()) {
            Turma turma = turmaOptional.get();
            Aluno aluno = alunoService.findByIdObject(alunoId);
            if (aluno != null) {
                turma.getAlunos().add(aluno);
                turma = turmaRepository.save(turma);
                aluno.getTurmas().add(turma);
                alunoService.update(aluno);
                return converte(turma);
            } else {
                throw new Exception("aluno nao encontrado");
            }
        } else {
            throw new Exception("turma nao encontrada");
        }
    }

    public List<AlunoDTO> getAlunos(String turmaId) throws Exception {
        Optional<Turma> turmaOptional = turmaRepository.findById(turmaId);
            if (turmaOptional.isPresent()) {
                Turma turma = turmaOptional.get();
                List<Aluno> alunos = turma.getAlunos();
                List<AlunoDTO> alunoDTOs = new ArrayList<>();
                if(alunos.size()==0){
                    throw new Exception("turma vazia");
                }
                for (Aluno aluno : alunos) {
                    alunoDTOs.add(alunoService.converte(aluno));
                }
                return alunoDTOs;
            }else {
            throw new Exception("turma nao encontrada");
        }
    }

}
