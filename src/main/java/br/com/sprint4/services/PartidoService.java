package br.com.sprint4.services;

import br.com.sprint4.dtos.PartidoDto;
import br.com.sprint4.exceptions.PartidoNotFoundException;
import br.com.sprint4.models.AssociadoModelo;
import br.com.sprint4.models.Ideologia;
import br.com.sprint4.models.PartidoModelo;
import br.com.sprint4.repositories.PartidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private ValidationService validationService;
    @Autowired
    ModelMapper modelMapper;

    public List<PartidoDto> mostrarTodos(Ideologia ideologia, String sortDescBy) {
        List<PartidoModelo> mostrarTodos = partidoRepository.findAll();
        List<PartidoDto> dtos = mostrarTodos.stream()
                .map(PartidoModelo -> modelMapper.map(PartidoModelo, PartidoDto.class)).collect(Collectors.toList());
        return dtos;
    }

    public PartidoDto mostrarPorId(Long id){
        PartidoModelo partidoModelo = partidoRepository.findById(id).orElseThrow(PartidoNotFoundException::new);
        return modelMapper.map(partidoModelo, PartidoDto.class);
    }

    public List<PartidoModelo> mostrarAssociados(Long id){
        List<PartidoModelo> partidoModelo = partidoRepository.findAllById(Collections.singleton(id));
        return partidoModelo;
    }

    public void atualizar(PartidoDto partidoDto, Long id){
        validationService.validaIdeologia(partidoDto);
        PartidoModelo partidoModelo = partidoRepository.findById(id).orElseThrow(PartidoNotFoundException::new);
        modelMapper.map(partidoDto, partidoModelo);
        partidoModelo.setDataFundacao(LocalDate.now(ZoneId.of("UTC")));
        partidoRepository.save(partidoModelo);
    }

    @Transactional
    public PartidoModelo salvar(PartidoDto partidoDto) {
        validationService.validaIdeologia(partidoDto);
        PartidoModelo partidoModelo = new PartidoModelo();
        BeanUtils.copyProperties(partidoDto, partidoModelo);
        partidoModelo.setDataFundacao(LocalDate.now(ZoneId.of("UTC")));
        return partidoRepository.save(partidoModelo);
    }

    public void deletar(Long id){
        partidoRepository.findById(id).orElseThrow(PartidoNotFoundException::new);
        partidoRepository.deleteById(id);
    }
}
