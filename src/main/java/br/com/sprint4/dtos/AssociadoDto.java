package br.com.sprint4.dtos;

import br.com.sprint4.models.CargoPolitico;
import br.com.sprint4.models.Sexo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssociadoDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String cargo;
    private Date dataNascimento;
    @NotBlank
    private String sexo;
}
