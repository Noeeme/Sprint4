package br.com.sprint4.services;

import br.com.sprint4.dtos.AssociadoDto;
import br.com.sprint4.dtos.AssociadoDtoResponse;
import br.com.sprint4.exceptions.AssociadoNotFoundException;
import br.com.sprint4.exceptions.PartidoNotFoundException;
import br.com.sprint4.models.AssociadoModelo;
import br.com.sprint4.models.CargoPolitico;
import br.com.sprint4.models.PartidoModelo;
import br.com.sprint4.repositories.AssociadoRepository;
import br.com.sprint4.repositories.PartidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.mongo.ReactiveStreamsMongoClientDependsOnBeanFactoryPostProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private PartidoRepository partidoRepository;
    @Autowired
    private ValidationService validationService;
    @Autowired
    ModelMapper modelMapper;

    public List<AssociadoDtoResponse> mostrarTodos(CargoPolitico cargoPolitico, String nome) {
        List<AssociadoModelo> mostrarTodos = associadoRepository.findAll();
        List<AssociadoDtoResponse> dtos = mostrarTodos.stream()
                .map(AssociadoModelo -> modelMapper.map(AssociadoModelo, AssociadoDtoResponse.class)).collect(Collectors.toList());
        return dtos;
    }

    @Transactional
    public AssociadoModelo salvar(AssociadoDto associadoDto) {
        validationService.validaCargo(associadoDto);
        validationService.validaSexo(associadoDto);
        AssociadoModelo associadoModelo = new AssociadoModelo();
        BeanUtils.copyProperties(associadoDto, associadoModelo);
        return associadoRepository.save(associadoModelo);
    }

    public void associadoEmPartido(Long idAssociado, Long idPartido){
        PartidoModelo partidoModelo = new PartidoModelo();
        AssociadoModelo idA = associadoRepository.getReferenceById(idAssociado);
        PartidoModelo idP = partidoRepository.getReferenceById(idPartido);
        idA.setPartido(idP);
        associadoRepository.save(idA);
    }

    public AssociadoDtoResponse mostrarPorId(Long id){
        AssociadoModelo associadoModelo = associadoRepository.findById(id).orElseThrow(AssociadoNotFoundException::new);
        return modelMapper.map(associadoModelo, AssociadoDtoResponse.class);
    }
    public void atualizar(AssociadoDto associadoDto, Long id){
        validationService.validaCargo(associadoDto);
        validationService.validaSexo(associadoDto);
        AssociadoModelo associadoModelo = associadoRepository.findById(id).orElseThrow(AssociadoNotFoundException::new);
        modelMapper.map(associadoDto, associadoModelo);
        associadoRepository.save(associadoModelo);
    }

    public void deletar(Long id){
        associadoRepository.findById(id).orElseThrow(PartidoNotFoundException::new);
        associadoRepository.deleteById(id);
    }

    public void deletarAssociadoDoPartido(Long idAssociado, Long idPartido){
        AssociadoModelo idA = associadoRepository.getReferenceById(idAssociado);
        PartidoModelo idP = partidoRepository.getReferenceById(idPartido);
        idA.setPartido(null);
        associadoRepository.save(idA);
    }
}
