package br.com.cdc.errors;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorOutputDto {

    private final List<String> errors = new ArrayList<>();
    private final List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();

    public void addError(String message) {
        errors.add(message);
    }

    public void addFieldError(String field, String message) {
        FieldErrorOutputDto fieldError = new FieldErrorOutputDto(field, message);
        fieldErrors.add(fieldError);
    }

    public List<String> getErrors() {
        return errors;
    }

    public List<FieldErrorOutputDto> getFieldErrors() {
        return fieldErrors;
    }

    public int getNumberOfErrors() {
        return errors.size() + fieldErrors.size();
    }
}
