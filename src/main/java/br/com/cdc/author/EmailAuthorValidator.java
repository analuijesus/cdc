package br.com.cdc.author;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class EmailAuthorValidator implements Validator {

    private final AuthorRepository repository;

    public EmailAuthorValidator(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NewAuthorForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NewAuthorForm form = (NewAuthorForm) target;

        repository
                .findByEmail(form.email())
                .ifPresent(author -> errors.rejectValue("email", null, "[Error] author's email " + form.email() + " already exists"));

    }
}
