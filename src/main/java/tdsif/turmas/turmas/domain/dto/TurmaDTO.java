package tdsif.turmas.turmas.domain.dto;

import lombok.Data;

@Data
public class TurmaDTO {
    
    private String id;
  
    private String sigla;
    
    private String nome;
   
    private String ano;

    private Integer vagasMin;
    
    private Integer vagasMax;

    private Integer semestre;

    
}
