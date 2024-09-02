package com.example.blogsystem.Advise;

import com.example.blogsystem.API.APIException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviseController {
    @ExceptionHandler(value = APIException.class)
    public ResponseEntity APIExceptionHandler(APIException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
