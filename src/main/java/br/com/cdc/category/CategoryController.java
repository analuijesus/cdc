package br.com.cdc.category;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/api/new-category")
    public ResponseEntity<?> create(@RequestBody @Valid NewCategoryForm form) {
        Category category = form.toModel();
        repository.save(category);

        return ResponseEntity.ok(category);
    }
}
