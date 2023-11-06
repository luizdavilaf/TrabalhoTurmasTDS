package tdsif.turmas.turmas.domain.dto;



import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NovaTurma {//PAYLOAD ENTRADA
    @NotBlank(message = "Campo sigla é obrigatório")
    @Length(min = 3, max = 3, message = "O campo deve ter 3 caracteres")
    String sigla;

    @NotBlank(message = "Campo nome é obrigatório")
    String nome;

    @NotBlank(message = "Campo ano é obrigatório")
    @Length(min = 4, max = 4, message = "O campo deve ter 4 caracteres")
    String ano;
    
   
    @Min(value = 2, message = "O número de vagas de ser maior que 2")
    @Max(value = 50, message = "O número de vagas de ser menor que 50")
    Integer vagasMin;

    
    @Min(value = 2, message = "O número de vagas de ser maior que 2")
    @Max(value = 50, message = "O número de vagas de ser menor que 50")
    Integer vagasMax;

    
    @Min(value = 1, message = "o valor minimo de semestre deve ser 1")
    @Max(value = 2, message = "o valor maximo de semestre deve ser 1")
    Integer semestre;

}
