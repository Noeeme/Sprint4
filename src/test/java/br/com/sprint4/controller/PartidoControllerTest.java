package br.com.sprint4.controller;

import br.com.sprint4.controllers.AssociadoController;
import br.com.sprint4.controllers.PartidoController;
import br.com.sprint4.dtos.AssociadoDto;
import br.com.sprint4.dtos.AssociadoDtoResponse;
import br.com.sprint4.dtos.PartidoDto;
import br.com.sprint4.models.AssociadoModelo;
import br.com.sprint4.models.PartidoModelo;
import br.com.sprint4.services.PartidoService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PartidoControllerTest {

    @MockBean
    private PartidoService partidoService;

    @Test
    public void cadastrarPartido_sucesso() {
        PartidoDto partidoDto = new PartidoDto();
        PartidoController partidoController = new PartidoController(partidoService);
        partidoController.cadastrarPartido(partidoDto);

        Assert.assertNotNull(partidoDto);
    }

    @Test
    public void mostrarTodos() {
        PartidoController partidoController = new PartidoController(partidoService);
        ResponseEntity<List<PartidoDto>> partidoDtos = partidoController.mostrarTodos(null, null);

        Assert.assertNotNull(partidoDtos);
    }

    @Test
    public void mostrarPorId() {
        PartidoController partidoController = new PartidoController(partidoService);
        ResponseEntity<PartidoDto> partidoDto = partidoController.mostrarPorId(1l);

        Assert.assertNotNull(1l);
    }

    @Test
    public void atualizar() {
        PartidoController partidoController = new PartidoController(partidoService);
        PartidoDto partidoDto = new PartidoDto();
        partidoController.atualizar(partidoDto, 1l);

        Assert.assertNotNull(partidoDto);
    }

    @Test
    public void deletar() {
        PartidoController partidoController = new PartidoController(partidoService);
        PartidoDto partidoDto = new PartidoDto();
        partidoController.deletar(1l);

        Assert.assertNotEquals(Optional.of(1l), partidoDto);
    }
}
