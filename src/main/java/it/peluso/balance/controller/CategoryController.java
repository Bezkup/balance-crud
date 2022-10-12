package it.peluso.balance.controller;

import it.peluso.balance.entity.Category;
import it.peluso.balance.exception.CategoryNotFoundException;
import it.peluso.balance.model.request.CategoryRequest;
import it.peluso.balance.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: this is just a draft to allow postman to add new categories
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
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
    public ResponseEntity<Long> getCategoryIDByName(@RequestParam(value="name") String name) throws CategoryNotFoundException {
        return new ResponseEntity<>(
                service.findCategoryIDByName(name),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest request) {
        try{
            return new ResponseEntity<>(
                    service.saveCategory(request),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return null;
        }
    }
}
