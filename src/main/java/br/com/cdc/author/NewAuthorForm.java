package br.com.cdc.author;

import jakarta.validation.constraints.*;

public record NewAuthorForm(@NotBlank String name,
                            @NotBlank @Email String email,
                            @NotBlank @Size(max = 400) String description) {

    public Author toModel() {
        return new Author(name, email, description);
    }
}
