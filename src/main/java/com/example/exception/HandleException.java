package com.example.exception;

import com.example.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandleException {
    @ExceptionHandler(value = ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleException(
            ResourceNotFound e,
            WebRequest request
    ){
        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                request.getDescription(false),
                new Date()
        );
                return new ResponseEntity<>(errorDetails , HttpStatus.INTERNAL_SERVER_ERROR);
    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String , String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
//        Map<String , String> errorMAp = new HashMap<>();
//
//        ex.getBindingResult().getFieldErrors().forEach(error ->  {
//     errorMAp.put(error.getField() , error.getDefaultMessage());
//        });
//        return errorMAp;
//    }
}
