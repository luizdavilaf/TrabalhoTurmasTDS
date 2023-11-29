package tdsif.turmas.turmas.domain.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.ToString;
import tdsif.turmas.turmas.domain.entity.Aluno;

//@ToString(exclude = "turmas")
@Data
public class AlunoDTO {
    
    private Integer id;
  
    private String cpf;
    
    private String nome;

    //private List<TurmaDTO> turmas;
   

    
}
