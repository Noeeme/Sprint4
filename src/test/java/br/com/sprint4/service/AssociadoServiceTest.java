package br.com.sprint4.service;

import br.com.sprint4.dtos.AssociadoDto;
import br.com.sprint4.dtos.AssociadoDtoResponse;
import br.com.sprint4.exceptions.AssociadoNotFoundException;
import br.com.sprint4.exceptions.PartidoNotFoundException;
import br.com.sprint4.models.AssociadoEPartido;
import br.com.sprint4.models.AssociadoModelo;
import br.com.sprint4.models.CargoPolitico;
import br.com.sprint4.models.PartidoModelo;
import br.com.sprint4.repositories.AssociadoRepository;
import br.com.sprint4.services.AssociadoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AssociadoServiceTest {

    @Autowired
    private AssociadoService associadoService;

    @MockBean
    private AssociadoRepository associadoRepository;

    @Test
    public void mostrarTodos_sucesso() {
        AssociadoModelo associado1 = new AssociadoModelo();
        associado1.setNome("João");
        AssociadoModelo associado2 = new AssociadoModelo();
        associado2.setNome("João");
        List<AssociadoModelo> modelos = new ArrayList<>();
        modelos.add(associado1);
        modelos.add(associado2);
        when(associadoRepository.findAll()).thenReturn(modelos);

        List<AssociadoDtoResponse> associadoDtoResponses = associadoService.mostrarTodos(null, null);

        Assert.assertEquals(modelos.size(), associadoDtoResponses.size());
    }

    @Test
    public void mostrarPorId_sucesso(){
        when(associadoRepository.findById(1l)).thenReturn(Optional.of(new AssociadoModelo()));
        AssociadoDto associadoDto = associadoService.mostrarPorId(1l);

        Assert.assertNotNull(associadoDto);
    }

    @Test(expected =AssociadoNotFoundException.class)
    public void mostrarPorId_notFound(){
        when(associadoRepository.findById(1l)).thenReturn(Optional.empty());
        associadoService.mostrarPorId(1l);
    }

    @Test
    public void salvarAssociado_sucesso() {
        AssociadoDto associadoDto = new AssociadoDto();
        associadoDto.setNome("João");
        associadoDto.setSexo("Masculino");
        associadoDto.setCargo("Presidente");

        associadoService.salvar(associadoDto);
        Assert.assertEquals("João", associadoDto.getNome());
    }

    //ATENÇÃO
//    @Test
//    public void associadoEmPartido_sucesso() {
//        when(associadoRepository.findById(1l)).thenReturn(Optional.of(new AssociadoModelo()));
//        PartidoModelo partidoModelo = new PartidoModelo();
//        AssociadoModelo associadoModelo = new AssociadoModelo();
//        AssociadoEPartido associadoEPartido = new AssociadoEPartido();
//        associadoEPartido.setIdAssociado(1l);
//        associadoEPartido.setIdPartido(1l);
//
//
//
//        associadoService.associadoEmPartido(1l, 1l);
//
//        Assert.assertEquals(Optional.of(1l), associadoModelo.getPartido());
//    }


    @Test
    public void atualizar_sucesso() {
        when(associadoRepository.findById(1l)).thenReturn(Optional.of(new AssociadoModelo()));
        AssociadoDto associadoDto = new AssociadoDto();
        associadoDto.setCargo("Nenhum");
        associadoDto.setSexo("Masculino");
        associadoDto.setNome("José");
        associadoService.atualizar(associadoDto, 1l);
        Assert.assertEquals("José", associadoDto.getNome());
    }

    @Test
    public void deletar_sucesso() {
        when(associadoRepository.findById(1l)).thenReturn(Optional.of(new AssociadoModelo()));
        associadoService.deletar(1l);
        verify(associadoRepository, times(1)).findById(1l);
    }

    //ATENÇÃO
    @Test
    public void deletarAssociadoDoPartido() {
    }
}