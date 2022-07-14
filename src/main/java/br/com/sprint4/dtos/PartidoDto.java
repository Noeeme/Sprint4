package br.com.sprint4.dtos;

import br.com.sprint4.models.Ideologia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartidoDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String sigla;
    private String ideologia;
}
