package it.peluso.balance.controller;

import it.peluso.balance.entity.Category;
import it.peluso.balance.model.request.CategoryRequest;
import it.peluso.balance.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("get id category from name")
    public void getIdFromCategoryName() throws Exception {
        Category newCategory = categoryService.saveCategory(new CategoryRequest("TestGetID"));
        MvcResult result = this.mockMvc.perform(get("/api/v1/categories?name=" + newCategory.getCategory()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        assertEquals(newCategory.getId().toString(), result.getResponse().getContentAsString());
        categoryService.deleteCategory(newCategory.getId());
    }

    @Test
    @DisplayName("throw exception if no category exists")
    public void throwExceptionIfNotExists() throws Exception {
        String categoryName = "abcdefg";
        this.mockMvc.perform(get("/api/v1/categories?name=" + categoryName))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Nessuna categoria trovata con nome: " + categoryName));
    }

    @Test
    @DisplayName("Throw exception if category already exists")
    public void throwExceptionIfAlreadyExists() throws Exception {
        String category = "Test";
        this.mockMvc.perform(post("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"category\":\""+ category + "\"}"))
                            .andDo(print())
                            .andExpect(status().isConflict())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("La categoria " + category + " è già esistente"));
    }
}
