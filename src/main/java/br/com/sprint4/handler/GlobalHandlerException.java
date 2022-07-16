package br.com.sprint4.handler;

import br.com.sprint4.exceptions.*;
import br.com.sprint4.models.Ideologia;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    private static final String NOME_PARTIDO_INVÁLIDO = "Partido inválido";
    private static final String SIGLA_INVÁLIDA = "Sigla inválida";
    private static final String IDEOLOGIA_INVÁLIDA = "Ideologia inválida";
    private static final String DATA_FUNDACAO_INVÁLIDA = "Data de fundação inválida";

    private static final String NOME_ASSOCIADO_INVÁLIDO = "Nome inválido";
    private static final String CARGO_POLITICO_INVÁLIDO = "Cargo político inválido";
    private static final String DATA_NASCIMENTO_INVÁLIDA = "Data de nascimento inválida";
    private static final String SEXO_INVÁLIDO = "Sexo inválido";

    @ExceptionHandler(value = {NomePartidoInvalidException.class})
    protected ResponseEntity<ErrorMessage> handlerNomePartidoInvalido(NomePartidoInvalidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(NOME_PARTIDO_INVÁLIDO));
    }

    @ExceptionHandler(value = {SiglaInvalidException.class})
    protected ResponseEntity<ErrorMessage> handlerSiglaInvalida(SiglaInvalidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(SIGLA_INVÁLIDA));
    }

    @ExceptionHandler(value = {IdeologiaInvalidException.class})
    protected ResponseEntity<ErrorMessage> handlerIdeologiaInvalida(IdeologiaInvalidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(IDEOLOGIA_INVÁLIDA));
    }

    @ExceptionHandler(value = {DataFundacaoInvalidException.class})
    protected ResponseEntity<ErrorMessage> handlerDataFundacaoInvalida(DataFundacaoInvalidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(DATA_FUNDACAO_INVÁLIDA));
    }

    @ExceptionHandler(value = {NomeAssociadoInvalidException.class})
    protected ResponseEntity<ErrorMessage> handlerNomeAssociadoInvalido(NomeAssociadoInvalidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(NOME_ASSOCIADO_INVÁLIDO));
    }

    @ExceptionHandler(value = {CargoInvalidException.class})
    protected ResponseEntity<ErrorMessage> handlerCargoPoliticoInvalido(CargoInvalidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(CARGO_POLITICO_INVÁLIDO));
    }

    @ExceptionHandler(value = {DataNascimentoInvalidException.class})
    protected ResponseEntity<ErrorMessage> handlerDataNascimentoInvalida(DataFundacaoInvalidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(DATA_NASCIMENTO_INVÁLIDA));
    }

    @ExceptionHandler(value = {SexoInvalidException.class})
    protected ResponseEntity<ErrorMessage> handlerSexoInvalido(SexoInvalidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(SEXO_INVÁLIDO));
    }
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError ->
                "Campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage()
        ).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new br.com.sprint4.handler.ErrorMessage(validationList));
    }
}
