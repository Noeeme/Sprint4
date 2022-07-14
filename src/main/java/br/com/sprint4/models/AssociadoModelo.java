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
    private String  cargo;
    private Date dataNascimento;
    private String sexo;

    @JoinColumn(name = "partido_id")
    @ManyToOne
    private PartidoModelo partido;

}
