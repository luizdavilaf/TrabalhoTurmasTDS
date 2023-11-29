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
public class NovoAluno {
    @NotBlank(message = "Campo cpf é obrigatório")
    @Length(min = 11, max = 11, message = "O campo cpf deve ter 11 caracteres")
    String cpf;

    @NotBlank(message = "Campo nome é obrigatório")
    String nome;


}
