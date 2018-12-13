package io.agilesalves.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectIdException(ProjectIdException exception, WebRequest request) {
        ProjectIdExceptionResponse projectIdExceptionResponse = new ProjectIdExceptionResponse(exception.getMessage());
        return new ResponseEntity(projectIdExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectTaskException(ProjectTaskException exception, WebRequest request){
        ProjectTaskExceptionResponse projectTaskExceptionResponse = new ProjectTaskExceptionResponse(exception.getMessage());
        return new ResponseEntity(projectTaskExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
