package br.com.sprint4.service;

import br.com.sprint4.dtos.AssociadoDto;
import br.com.sprint4.dtos.AssociadoDtoResponse;
import br.com.sprint4.dtos.PartidoDto;
import br.com.sprint4.exceptions.AssociadoNotFoundException;
import br.com.sprint4.exceptions.CargoInvalidException;
import br.com.sprint4.exceptions.IdeologiaInvalidException;
import br.com.sprint4.exceptions.SexoInvalidException;
import br.com.sprint4.models.CargoPolitico;
import br.com.sprint4.models.Ideologia;
import br.com.sprint4.models.Sexo;
import br.com.sprint4.services.ValidationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ValidationServiceTest {

    @Autowired
    private ValidationService validationService;

    @Test
    public void validaIdeologia() {
        PartidoDto partidoDto = new PartidoDto();
        partidoDto.setIdeologia("Centro");
        validationService.validaIdeologia(partidoDto);

        Assert.assertEquals("Centro", partidoDto.getIdeologia());
    }

    @Test(expected = IdeologiaInvalidException.class)
    public void validaIdeologia_retornandoException(){
        PartidoDto partidoDto = new PartidoDto();
        partidoDto.setIdeologia("Nenhuma");
        validationService.validaIdeologia(partidoDto);

        Assert.assertEquals(Ideologia.CENTRO, partidoDto.getIdeologia());
    }


    @Test
    public void validaCargo() {
        AssociadoDto associadoDto = new AssociadoDto();
        associadoDto.setCargo("Nenhum");
        validationService.validaCargo(associadoDto);

        Assert.assertEquals("Nenhum", associadoDto.getCargo());
    }

    @Test(expected = CargoInvalidException.class)
    public void validaCargo_retornandoException(){
        AssociadoDto associadoDto = new AssociadoDto();
        associadoDto.setCargo("Trabalhador");
        validationService.validaCargo(associadoDto);

        Assert.assertEquals(CargoPolitico.GOVERNADOR, associadoDto.getCargo());
    }

    @Test
    public void validaSexo() {
        AssociadoDto associadoDto = new AssociadoDto();
        associadoDto.setSexo("Feminino");
        validationService.validaSexo(associadoDto);

        Assert.assertEquals("Feminino", associadoDto.getSexo());
    }

    @Test(expected = SexoInvalidException.class)
    public void validaSexo_retornandoException(){
        AssociadoDto associadoDto = new AssociadoDto();
        associadoDto.setSexo("Nenhum");
        validationService.validaSexo(associadoDto);

        Assert.assertEquals(Sexo.FEMININO, associadoDto.getSexo());
    }
}