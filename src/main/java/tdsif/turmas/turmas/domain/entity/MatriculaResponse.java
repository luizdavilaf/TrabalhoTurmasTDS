package tdsif.turmas.turmas.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import tdsif.turmas.turmas.domain.dto.TurmaDTO;
@Data
@NoArgsConstructor
public class MatriculaResponse {
    private String mensagem;
    private TurmaDTO turma;

    public MatriculaResponse(String mensagem, TurmaDTO turma) {
        this.mensagem = mensagem;
        this.turma = turma;
    }

    @Override
    public String toString() {
        return "{mensagem=" + mensagem + ", turma=" + turma + "}";
    }
}
