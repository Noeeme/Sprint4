package br.com.sprint4.controllers;

import br.com.sprint4.dtos.AssociadoDto;
import br.com.sprint4.models.AssociadoEPartido;
import br.com.sprint4.models.CargoPolitico;
import br.com.sprint4.services.AssociadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

    final AssociadoService associadoService;

    public AssociadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarAssociado(@RequestBody
                                                   @Valid AssociadoDto associadoDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(associadoService.salvar(associadoDto));
    }
    @GetMapping
    public ResponseEntity<List<AssociadoDto>> mostrarTodos(@RequestParam(required = false) CargoPolitico cargoPolitico,
                                                           @RequestParam(required = false, defaultValue = "id") String sortDescBy){

        //FALTA ORDENAÇAO POR NOME!
        List<AssociadoDto> lista = associadoService.mostrarTodos(cargoPolitico, sortDescBy);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociadoDto> mostrarPorId(@PathVariable Long id){
        AssociadoDto associadoDto = associadoService.mostrarPorId(id);
        return ResponseEntity.ok(associadoDto);
    }
    @PostMapping("/partidos")
    public ResponseEntity<Void> associacao(@RequestBody @Valid AssociadoEPartido associadoEPartido){
        associadoService.AssociadoEmPartido(associadoEPartido.getIdAssociado(), associadoEPartido.getIdPartido());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@RequestBody @Valid AssociadoDto associadoDto, @PathVariable Long id){
        associadoService.atualizar(associadoDto, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        associadoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idA}/partidos/{idP}")
    public ResponseEntity<Void> deletarAssociadoDoPartido(@PathVariable Long idA, Long idP){
        associadoService.deletarAssociadoDoPartido(idA, idP);
        return ResponseEntity.noContent().build();
    }
}
