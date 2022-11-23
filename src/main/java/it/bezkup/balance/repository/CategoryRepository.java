package it.peluso.balance.repository;

import it.peluso.balance.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//TODO: this is just a draft to allow postman to add new categories
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategory(String category);
    boolean existsByCategory(String category);
}
