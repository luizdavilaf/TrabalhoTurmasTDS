package tdsif.turmas.turmas.controller;

import java.util.List;

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
import tdsif.turmas.turmas.domain.dto.NovaTurma;
import tdsif.turmas.turmas.domain.dto.TurmaDTO;
import tdsif.turmas.turmas.domain.entity.Aluno;
import tdsif.turmas.turmas.domain.entity.MatriculaRequest;
import tdsif.turmas.turmas.domain.entity.MatriculaResponse;
import tdsif.turmas.turmas.domain.entity.Turma;
import tdsif.turmas.turmas.domain.repository.TurmaRepository;
import tdsif.turmas.turmas.domain.service.TurmaService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
@RestController
public class TurmaController {
    private TurmaService turmaService;

    @Autowired
    TurmaRepository turmaRepository;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping("/api/v1/turmas")
    public ResponseEntity<?> getTurmas(
            @RequestParam(name = "sigla", required = false) String sigla,
            @RequestParam(name = "ano", required = false) String ano) {
        try {
            if (sigla != null && !sigla.isEmpty()) {
                List<TurmaDTO> result = turmaService.findBySigla(sigla);
                return ResponseEntity.ok(result);
            } else if (ano != null && !ano.isEmpty()) {
                List<TurmaDTO> result = turmaService.findByAno(ano);
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.ok(turmaService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno ao processar a requisição: " + e.getMessage());

        }
    }

    @PostMapping("/api/v1/turmas")
    public ResponseEntity<?> salvarTurma(@Valid @RequestBody NovaTurma turma) {
        try {
            TurmaDTO turmaDTO = turmaService.save(turma);
            return ResponseEntity.ok(turmaDTO);

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/turmas/{id}")
    public ResponseEntity<TurmaDTO> getTurmaById(@PathVariable String id) {
        TurmaDTO turma = turmaService.findById(id);
        if (turma != null) {
            return ResponseEntity.ok(turma);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/v1/turmas/{turmaId}/matriculas")
    public ResponseEntity<String> matricularAlunoNaTurma(
            @PathVariable String turmaId,
            @RequestBody MatriculaRequest matriculaRequest) {
        try {
            TurmaDTO turma = turmaService.matricular(turmaId, matriculaRequest.getAlunoId());
            MatriculaResponse matriculaResponse = new MatriculaResponse("Aluno matriculado com sucesso.", turma);
            return ResponseEntity.ok(matriculaResponse.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/api/v1/turmas/{turmaId}/matriculas")
    public ResponseEntity<?> getMatriculas(@PathVariable String turmaId) {
        try {

            return ResponseEntity.ok(turmaService.getAlunos(turmaId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno ao processar a requisição: " + e.getMessage());

        }
    }
}
