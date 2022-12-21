package it.dreamteam.balance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.dreamteam.balance.entity.Category;
import it.dreamteam.balance.exception.BalanceErrors;
import it.dreamteam.balance.service.CategoryService;
import org.junit.jupiter.api.AfterAll;
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

import java.text.MessageFormat;

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
    private static AutoCloseable closeable;
    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterAll
    public static void close() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("get id category from name")
    public void getIdFromCategoryName() throws Exception {
        Category newCategory = categoryService.saveCategory("TestGetID");
        MvcResult result = this.mockMvc.perform(get("/api/v1/categories?name=" + newCategory.getCategory()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        categoryService.deleteCategory(newCategory.getId());
        Category response = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Category.class);
        assertEquals(response.getCategory(), newCategory.getCategory());
    }

    @Test
    @DisplayName("throw exception if no category exists")
    public void throwExceptionIfNotExists() throws Exception {
        String categoryName = "abcdefg";
        this.mockMvc.perform(get("/api/v1/categories?name=" + categoryName))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(MessageFormat.format(BalanceErrors.ERR_CATEGORY_NOT_FOUND_BY_NAME.message,categoryName)));
    }

    @Test
    @DisplayName("Throw exception if category already exists")
    public void throwExceptionIfAlreadyExists() throws Exception {
        Category test = categoryService.saveCategory("Test");
        String category = "Test";
        this.mockMvc.perform(post("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"category\":\""+ category + "\"}"))
                            .andDo(print())
                            .andExpect(status().isConflict())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(MessageFormat.format(BalanceErrors.ERR_CATEGORY_ALREADY_EXISTS.message, category)));
        categoryService.deleteCategory(test.getId());
    }
}