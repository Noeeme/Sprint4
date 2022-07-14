package br.com.sprint4.controllers;

import br.com.sprint4.dtos.PartidoDto;
import br.com.sprint4.models.Ideologia;
import br.com.sprint4.models.PartidoModelo;
import br.com.sprint4.services.PartidoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    final PartidoService partidoService;

    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarPartido(@RequestBody
                                                       @Valid PartidoDto partidoDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(partidoService.salvar(partidoDto));
    }

    @GetMapping
    public ResponseEntity<List<PartidoDto>> mostrarTodos(@RequestParam(required = false)Ideologia ideologia,
                                                         @RequestParam(required = false, defaultValue = "id") String sortDescBy){
        List<PartidoDto> lista = partidoService.mostrarTodos(ideologia, sortDescBy);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartidoDto> mostrarPorId(@PathVariable Long id){
        PartidoDto partidoDto = partidoService.mostarPorId(id);
        return ResponseEntity.ok(partidoDto);
    }

//    @GetMapping("/{id}/associados")

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@RequestBody @Valid PartidoDto partidoDto, @PathVariable Long id){
        partidoService.atualizar(partidoDto, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        partidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
