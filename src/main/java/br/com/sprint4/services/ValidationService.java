package br.com.sprint4.services;

import br.com.sprint4.dtos.AssociadoDto;
import br.com.sprint4.dtos.PartidoDto;
import br.com.sprint4.exceptions.CargoInvalidException;
import br.com.sprint4.exceptions.IdeologiaInvalidException;
import br.com.sprint4.exceptions.SexoInvalidException;
import br.com.sprint4.models.CargoPolitico;
import br.com.sprint4.models.Ideologia;
import br.com.sprint4.models.Sexo;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ValidationService {
    public void validaIdeologia(PartidoDto partidoDto){
        boolean valido = Arrays.stream(Ideologia.values()).anyMatch(ideologia -> ideologia.getValor().equals(partidoDto.getIdeologia()));

        if(!valido){
            throw new IdeologiaInvalidException();
        }
    }
    public void validaCargo(AssociadoDto associadoDto) throws CargoInvalidException {
        boolean valido = Arrays.stream(CargoPolitico.values()).anyMatch(cargoPolitico -> cargoPolitico.getValor().equals(associadoDto.getCargo()));

        if(!valido){
            throw new CargoInvalidException();
        }
    }
    public void validaSexo(AssociadoDto associadoDto) throws SexoInvalidException {
        boolean valido = Arrays.stream(Sexo.values()).anyMatch(sexo -> sexo.getValor().equals(associadoDto.getSexo()));

        if (!valido) {
            throw new SexoInvalidException();
        }
    }
}
