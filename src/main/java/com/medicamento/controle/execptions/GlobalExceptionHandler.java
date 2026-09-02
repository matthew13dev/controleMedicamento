package com.medicamento.controle.execptions;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(MedicamentoException.class)
    public ResponseEntity<ErroResponseDTO> handleMedicacamentoException(MedicamentoException exception){

        ErroResponseDTO erroResponseDTO = new ErroResponseDTO(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResponseDTO);
    }


    @ExceptionHandler(ValidadeException.class)
    public ResponseEntity<ErroResponseDTO> handleValidadeException(ValidadeException exception){
        ErroResponseDTO erroResponseDTO = new ErroResponseDTO(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResponseDTO);
    }


//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ErroResponseDTO> handleDataIntegrity(DataIntegrityViolationException ex) {
//        ErroResponseDTO body = new ErroResponseDTO(
//                ex.getMessage(),
//                HttpStatus.BAD_REQUEST.value()
//        );
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponseDTO> handleGeneric(Exception ex) {
        ErroResponseDTO body = new ErroResponseDTO(
                ex.getMessage() != null ? ex.getMessage() : "Erro interno",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

}
