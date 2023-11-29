package tdsif.turmas.turmas.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tdsif.turmas.turmas.domain.dto.NovoAluno;
import tdsif.turmas.turmas.domain.dto.AlunoDTO;
import tdsif.turmas.turmas.domain.entity.Aluno;
import tdsif.turmas.turmas.domain.repository.AlunoRepository;
import tdsif.turmas.turmas.domain.service.AlunoService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
@RestController
public class AlunoController {
    private AlunoService AlunoService;

    @Autowired
    AlunoRepository AlunoRepository;

    public AlunoController(AlunoService AlunoService) {
        this.AlunoService = AlunoService;
    }

    @GetMapping("/api/v1/alunos")
    public ResponseEntity<List<AlunoDTO>> getAlunos() {
        return ResponseEntity.ok(AlunoService.findAll());
    }

    @PostMapping("/api/v1/alunos")
    public ResponseEntity<?> salvarAluno(@Valid @RequestBody NovoAluno aluno) {
        try {
            System.out.println(aluno);
            
            AlunoDTO AlunoDTO = AlunoService.save(aluno);
            return ResponseEntity.ok(AlunoDTO);

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }

    }

    @GetMapping("/alunos/{id}")
    public ResponseEntity<AlunoDTO> getAlunoById(@PathVariable Integer id) {        
        AlunoDTO Aluno = AlunoService.findById(id);
        if (Aluno != null) {
            return ResponseEntity.ok(Aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
