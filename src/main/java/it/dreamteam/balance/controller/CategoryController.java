package it.dreamteam.balance.controller;

import it.dreamteam.balance.entity.Category;
import it.dreamteam.balance.exception.BalanceErrors;
import it.dreamteam.balance.exception.category.CategoryAlreadyExistsException;
import it.dreamteam.balance.exception.category.CategoryNotFoundException;
import it.dreamteam.balance.model.request.CategoryRequest;
import it.dreamteam.balance.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory(){
        return new ResponseEntity<>(
                service.findAllCategories(),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> getCategoryById(@PathVariable long id) throws CategoryNotFoundException {
        return new ResponseEntity<>(
                service.findCategoryByID(id),
                HttpStatus.OK
        );
    }

    @RequestMapping(params = "name", method = RequestMethod.GET)
    public ResponseEntity<Category> getCategoryByName(@RequestParam(value="name") String name) throws CategoryNotFoundException {
        Optional<Category> category = service.findCategoryByName(name);
        if (category.isEmpty())
            throw new CategoryNotFoundException(BalanceErrors.ERR_CATEGORY_NOT_FOUND_BY_NAME, name);
        return new ResponseEntity<>(
                category.get(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest request) throws CategoryAlreadyExistsException {
            return new ResponseEntity<>(
                    service.saveCategory(request.getCategory()),
                    HttpStatus.CREATED
            );
    }
}
