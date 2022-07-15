package br.com.sprint4.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class AssociadoEPartido {
    @Positive
    private Long idPartido;
    @Positive
    private Long idAssociado;
}
