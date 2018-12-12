package io.agilesalves.ppmtool.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;

@Service
public class MapValidationErrorsService {

    public ResponseEntity<?> mapValidationError(BindingResult result) {
        HashMap<String, String> errorsMap = new HashMap<>();
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                errorsMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<HashMap<String, String>>(errorsMap , HttpStatus.BAD_REQUEST);
        }
        return null;
    }


}
