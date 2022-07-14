package br.com.sprint4.services;

import br.com.sprint4.dtos.PartidoDto;
import br.com.sprint4.exceptions.IdeologiaInvalidException;
import br.com.sprint4.models.Ideologia;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ValidationService {
    public void validate(PartidoDto partidoDto){
        boolean valido = Arrays.stream(Ideologia.values()).anyMatch(ideologia -> ideologia.getValor().equals(partidoDto.getIdeologia()));

        if(!valido){
            throw new IdeologiaInvalidException();
        }
    }
}
