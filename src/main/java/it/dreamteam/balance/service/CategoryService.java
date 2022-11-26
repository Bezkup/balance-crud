package it.dreamteam.balance.service;

import it.dreamteam.balance.entity.Category;
import it.dreamteam.balance.exception.BalanceErrors;
import it.dreamteam.balance.exception.category.CategoryAlreadyExistsException;
import it.dreamteam.balance.exception.category.CategoryNotFoundException;
import it.dreamteam.balance.model.request.CategoryRequest;
import it.dreamteam.balance.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public long findCategoryIDByName(String name) throws CategoryNotFoundException {
        return repository.findByCategory(name)
                .orElseThrow(() -> new CategoryNotFoundException(BalanceErrors.ERR_CATEGORY_NOT_FOUND_BY_NAME, name))
                .getId();
    }

    public Category saveCategory(CategoryRequest request) throws CategoryAlreadyExistsException {
        Category category = new Category();
        category.setCategory(request.getCategory());
        if (repository.existsByCategory(category.getCategory()))
            throw new CategoryAlreadyExistsException(BalanceErrors.ERR_CATEGORY_ALREADY_EXISTS, category.getCategory());
        return repository.save(category);
    }

    public int deleteCategory(long id){
        Category categoryToDelete = repository.findById(id).orElseThrow();
        repository.delete(categoryToDelete);
        return 1;
    }
}
