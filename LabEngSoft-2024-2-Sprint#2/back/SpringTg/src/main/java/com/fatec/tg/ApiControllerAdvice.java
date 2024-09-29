package com.fatec.tg;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestControllerAdvice;
// import org.springframework.web.server.ResponseStatusException;

// import com.fatec.tg.erros.ErrosApi;

// @RestControllerAdvice
// public class ApiControllerAdvice {

//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     @ResponseStatus(HttpStatus.BAD_REQUEST)
//     public ErrosApi handleValidationErrors(MethodArgumentNotValidException ex) {
//         return processValidationErrors(ex.getBindingResult());
//     }

//     @ExceptionHandler(ResponseStatusException.class)
//     public ResponseEntity<ErrosApi> handleResponseStatusException(ResponseStatusException ex) {
//         return buildErrorResponse(ex.getReason(), ex.getStatus());
//     }

//     private ErrosApi processValidationErrors(BindingResult bindingResult) {
//         List<String> errorMessages = bindingResult.getAllErrors()
//                 .stream()
//                 .map(error -> error.getDefaultMessage())
//                 .collect(Collectors.toList());
//         return new ErrosApi(errorMessages);
//     }

//     private ResponseEntity<ErrosApi> buildErrorResponse(String message, HttpStatus status) {
//         ErrosApi apiError = new ErrosApi(message);
//         return new ResponseEntity<>(apiError, status);
//     }
// }


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fatec.tg.erros.ErrosApi;
import com.fatec.tg.erros.RepresentanteCadastrado;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrosApi handleValidationErrors(MethodArgumentNotValidException ex) {
        return processValidationErrors(ex.getBindingResult());
    }

    @ExceptionHandler(RepresentanteCadastrado.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrosApi> handleRepresentanteCadastrado(RepresentanteCadastrado ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrosApi handleGeneralException(Exception ex) {
        return new ErrosApi("Erro interno do servidor: " + ex.getMessage());
    }

    private ErrosApi processValidationErrors(BindingResult bindingResult) {
        List<String> errorMessages = bindingResult.getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        return new ErrosApi(errorMessages);
    }

    private ResponseEntity<ErrosApi> buildErrorResponse(String message, HttpStatus status) {
        ErrosApi apiError = new ErrosApi(message);
        return new ResponseEntity<>(apiError, status);
    }
}
