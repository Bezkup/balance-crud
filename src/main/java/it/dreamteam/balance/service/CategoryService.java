package it.dreamteam.balance.service;

import it.dreamteam.balance.entity.Category;
import it.dreamteam.balance.exception.BalanceErrors;
import it.dreamteam.balance.exception.category.CategoryAlreadyExistsException;
import it.dreamteam.balance.exception.category.CategoryNotFoundException;
import it.dreamteam.balance.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> findAllCategories(){
        return repository.findAll();
    }

    public Category findCategoryByID(long id) throws CategoryNotFoundException {
        return repository.findById(id).orElseThrow(() -> new CategoryNotFoundException(BalanceErrors.ERR_CATEGORY_NOT_FOUND_BY_ID, id));
    }

    public Optional<Category> findCategoryByName(String name) {
        return repository.findByCategory(name);
    }

    public Category saveCategory(String categoryName) throws CategoryAlreadyExistsException {
        if (repository.existsByCategory(categoryName))
            throw new CategoryAlreadyExistsException(BalanceErrors.ERR_CATEGORY_ALREADY_EXISTS, categoryName);
        Category category = new Category(categoryName);
        return repository.save(category);
    }

    public void deleteCategory(long id) throws CategoryNotFoundException {
        Category categoryToDelete = repository.findById(id).orElseThrow(() -> new CategoryNotFoundException(BalanceErrors.ERR_CATEGORY_NOT_FOUND_BY_ID, id));
        repository.delete(categoryToDelete);
    }
}
