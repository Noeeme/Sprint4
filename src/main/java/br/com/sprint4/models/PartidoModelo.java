package br.com.sprint4.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartidoModelo {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String sigla;
    private String ideologia;
    private LocalDate dataFundacao;

//    @OneToMany(mappedBy = "nome", orphanRemoval = true, cascade = CascadeType.ALL)
//    private List<AssociadoModelo> associados;
}
