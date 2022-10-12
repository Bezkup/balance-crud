package it.peluso.balance.controller;

import it.peluso.balance.entity.Category;
import it.peluso.balance.model.request.CategoryRequest;
import it.peluso.balance.service.CategoryService;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
        Category newCategory = categoryService.saveCategory(new CategoryRequest("Test"));
        MvcResult result = this.mockMvc.perform(get("/api/v1/categories?name=Test"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        assertEquals(newCategory.getId().toString(), result.getResponse().getContentAsString());
        categoryService.deleteCategory(newCategory.getId());
    }

    @Test
    @DisplayName("throw exception if no category exists")
    public void throwExceptionIfNotExists() throws Exception{

        MvcResult result = this.mockMvc.perform(get("/api/v1/categories?name=abcdefg"))
                .andExpect(status().isNotFound())
                .andReturn();
        JSONObject resultJson = new JSONObject(result.getResponse().getContentAsString());
        assertEquals(404, resultJson.getInt("code"));
        assertEquals("Nessuna categoria trovata con nome: abcdefg", resultJson.getString("message"));
    }
}
