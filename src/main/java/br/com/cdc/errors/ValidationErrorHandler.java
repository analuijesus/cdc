package br.com.cdc.errors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    private final MessageSource messageSource;

    public ValidationErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorOutputDto handleValidationError(MethodArgumentNotValidException exception) {
        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> filedErrors = exception.getBindingResult().getFieldErrors();

        return buildValidationErrors(globalErrors, filedErrors);
    }

    private ValidationErrorOutputDto buildValidationErrors(List<ObjectError> objectErrors, List<FieldError> fieldErrors) {
        ValidationErrorOutputDto validationErrors = new ValidationErrorOutputDto();

        objectErrors.forEach(error -> validationErrors.addError(error.getDefaultMessage()));
        fieldErrors.forEach(error -> validationErrors.addFieldError(error.getField(), getErrorMessage(error)));

        return validationErrors;
    }

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }
}
