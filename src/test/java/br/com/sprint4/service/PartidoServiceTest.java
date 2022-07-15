package br.com.sprint4.service;

import br.com.sprint4.dtos.AssociadoDto;
import br.com.sprint4.dtos.AssociadoDtoResponse;
import br.com.sprint4.dtos.PartidoDto;
import br.com.sprint4.exceptions.PartidoNotFoundException;
import br.com.sprint4.models.AssociadoModelo;
import br.com.sprint4.models.PartidoModelo;
import br.com.sprint4.repositories.PartidoRepository;
import br.com.sprint4.services.PartidoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PartidoServiceTest {

    @Autowired
    private PartidoService partidoService;

    @MockBean
    private PartidoRepository partidoRepository;

    @Test
    public void mostrarTodos_sucesso() {
        PartidoModelo partido1 = new PartidoModelo();
        partido1.setNome("Partido Central");
        PartidoModelo partido2 = new PartidoModelo();
        partido2.setNome("Partido Brasileiro");
        List<PartidoModelo> modelos = new ArrayList<>();
        modelos.add(partido1);
        modelos.add(partido2);
        when(partidoRepository.findAll()).thenReturn(modelos);

        List<PartidoDto> partidoDtos = partidoService.mostrarTodos(null, null);

        Assert.assertEquals(modelos.size(), partidoDtos.size());
    }

    @Test
    public void mostrarPorId_sucesso(){
        when(partidoRepository.findById(1l)).thenReturn(Optional.of(new PartidoModelo()));
        PartidoDto partidoDto = partidoService.mostrarPorId(1l);

        Assert.assertNotNull(partidoDto);
    }

    @Test(expected = PartidoNotFoundException.class)
    public void mostrarPorId_notFound(){
        when(partidoRepository.findById(1l)).thenReturn(Optional.empty());
        partidoService.mostrarPorId(1l);
    }

//    @Test
//    public void mostrarAssociadosDoPartido_sucesso(){
//        when(partidoRepository.findById(1l)).thenReturn(Optional.of(new List<PartidoModelo>()));
//        List<PartidoModelo> partidoDtos = partidoService.mostrarAssociados(1l);
//
//        Assert.assertNotNull(partidoDtos);
//    }

    @Test
    public void atualizar_sucesso() {
        when(partidoRepository.findById(1l)).thenReturn(Optional.of(new PartidoModelo()));
        PartidoDto partidoDto = new PartidoDto();
        partidoDto.setIdeologia("Centro");
        partidoDto.setNome("Partido Centralizado");
        partidoService.atualizar(partidoDto, 1l);
        Assert.assertEquals("Partido Centralizado", partidoDto.getNome());
    }

    @Test
    public void salvarPartido_sucesso() {
        PartidoDto partidoDto = new PartidoDto();
        partidoDto.setNome("Partido do Brasil");
        partidoDto.setIdeologia("Centro");
        partidoDto.setSigla("CBP");

        partidoService.salvar(partidoDto);
        Assert.assertEquals("Partido do Brasil", partidoDto.getNome());
    }


    @Test
    public void deletar_sucesso() {
        when(partidoRepository.findById(1l)).thenReturn(Optional.of(new PartidoModelo()));
        partidoService.deletar(1l);
        verify(partidoRepository, times(1)).findById(1l);
    }
}
