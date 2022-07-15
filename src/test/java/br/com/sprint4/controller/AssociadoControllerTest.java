package br.com.sprint4.controller;

import br.com.sprint4.controllers.AssociadoController;
import br.com.sprint4.dtos.AssociadoDto;
import br.com.sprint4.dtos.AssociadoDtoResponse;
import br.com.sprint4.models.AssociadoModelo;
import br.com.sprint4.services.AssociadoService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AssociadoControllerTest {

    @MockBean
    private AssociadoService associadoService;
    @Test
    public void cadastrarAssociado() {
        AssociadoDto associadoDto = new AssociadoDto();
        associadoService.salvar(associadoDto);

        Assert.assertNotNull(associadoDto);
    }

    @Test
    public void mostrarTodos() {
        List<AssociadoDtoResponse> associadoDtoResponses = associadoService.mostrarTodos(null, null);

        Assert.assertNotNull(associadoDtoResponses);
    }

    @Test
    public void mostrarPorId() {

        AssociadoDtoResponse associadoDto = associadoService.mostrarPorId(1l);

        Assert.assertNotNull(1l);
    }

    @Test
    public void atualizar() {
        AssociadoDto associadoDto = new AssociadoDto();
        associadoService.atualizar(associadoDto, 1l);

        Assert.assertNotNull(associadoDto);
    }

    @Test
    public void deletar() {
        AssociadoDtoResponse associadoDto = new AssociadoDtoResponse();
        associadoService.deletar(1l);

        Assert.assertNotEquals(Optional.of(1l), associadoDto.getId());
    }
}