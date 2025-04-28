package br.com.cdc.category;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NomeCategoryValidator implements Validator {

    private final CategoryRepository repository;

    public NomeCategoryValidator(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NewCategoryForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NewCategoryForm form = (NewCategoryForm) target;

        repository
                .findByName(form.name())
                .ifPresent(category -> errors.rejectValue("name", null, "Category name " + form.name() + " already exists"));
    }
}
