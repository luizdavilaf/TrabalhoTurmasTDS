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
    public ResponseEntity<List<TurmaDTO>> getTurmas(
            @RequestParam(name = "sigla", required = false) String sigla,
            @RequestParam(name = "ano", required = false) String ano) {
        if (sigla != null && !sigla.isEmpty()) {
            List<TurmaDTO> result = turmaService.findBySigla(sigla);
            return ResponseEntity.ok(result);
        } else if (ano != null && !ano.isEmpty()) {
            List<TurmaDTO> result = turmaService.findByAno(ano);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(turmaService.findAll());
    }

    @PostMapping("/api/v1/turmas")
    public ResponseEntity<?> salvarTurma(@Valid @RequestBody NovaTurma turma) {
        try {
            Turma newTurma = new Turma(turma.getAno(), turma.getNome(), turma.getSigla(), turma.getVagasMax(),
                    turma.getVagasMin(), turma.getSemestre());
            if (turmaRepository.existsById(newTurma.getId())) {
                throw new DataIntegrityViolationException("Já existe uma turma com o mesmo ID");
            }
            turmaRepository.save(newTurma);
            TurmaDTO dto = new TurmaDTO();
            BeanUtils.copyProperties(newTurma, dto);
            return ResponseEntity.ok(dto);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest()
                    .body("Já existe uma turma com o mesmo ID ou campo único.");
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
}
