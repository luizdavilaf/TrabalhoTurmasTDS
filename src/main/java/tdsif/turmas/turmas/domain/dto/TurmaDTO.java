package tdsif.turmas.turmas.domain.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;
import tdsif.turmas.turmas.domain.entity.Aluno;

@Data
public class TurmaDTO {
    
    private String id;
  
    private String sigla;
    
    private String nome;
   
    private String ano;

    private Integer vagasMin;
    
    private Integer vagasMax;

    private Integer semestre;

    private List<AlunoDTO> alunos;

    
}
