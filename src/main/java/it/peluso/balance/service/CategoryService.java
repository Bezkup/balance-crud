package it.peluso.balance.service;

import it.peluso.balance.entity.Category;
import it.peluso.balance.exception.CategoryNotFoundException;
import it.peluso.balance.model.request.CategoryRequest;
import it.peluso.balance.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO: this is just a draft to allow postman to add new categories
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
        return repository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Nessuna categoria trovata con id: " + id));
    }

    public long findCategoryIDByName(String name) throws CategoryNotFoundException {
        return repository.findIDByCategory(name)
                .orElseThrow(() -> new CategoryNotFoundException("Nessuna categoria trovata con nome: " + name))
                .getId();
    }

    public Category saveCategory(CategoryRequest request) {
        Category category = new Category();
        category.setCategory(request.getCategory());
        return repository.save(category);
    }

    public int deleteCategory(long id){
        Category categoryToDelete = repository.findById(id).orElseThrow();
        repository.delete(categoryToDelete);
        return 1;
    }
}
