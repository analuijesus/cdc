package br.com.cdc.author;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    private final AuthorRepository repository;
    private final EmailAuthorValidator emailValidator;

    public AuthorController(AuthorRepository repository, EmailAuthorValidator emailValidator) {
        this.repository = repository;
        this.emailValidator = emailValidator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    @Transactional
    @PostMapping("/api/new-author")
    public ResponseEntity<?> create(@RequestBody @Valid NewAuthorForm form) {
        Author author = form.toModel();
        repository.save(author);

        return ResponseEntity.ok(author);
    }
}