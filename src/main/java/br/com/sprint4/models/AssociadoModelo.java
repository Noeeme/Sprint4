package br.com.sprint4.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoModelo {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private CargoPolitico cargo;
    private Date dataNascimento;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @ManyToOne
    private PartidoModelo partido;
}
