package br.com.cdc.category;

import jakarta.validation.constraints.NotBlank;

public record NewCategoryForm(@NotBlank String name) {
    public Category toModel() {
        return new Category(name);
    }
}
