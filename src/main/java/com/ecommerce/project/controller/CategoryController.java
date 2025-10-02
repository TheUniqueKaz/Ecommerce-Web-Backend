package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstrants;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private CategoryService categoryService;

    @GetMapping("/echo")
    public ResponseEntity<String> echoMessage(@RequestParam(name = "message") String message){
        return new ResponseEntity<>("Echos Message: " + message,HttpStatus.OK);
    }


    @GetMapping("/public/categories")
        public ResponseEntity <CategoryResponse> getCategoryList(
                @RequestParam(name = "pageNumber", defaultValue = AppConstrants.PAGE_NUMBER, required= false) Integer pageNumber,
                @RequestParam(name = "pageSize", defaultValue = AppConstrants.PAGE_SIZE, required= false) Integer pageSize,
                @RequestParam(name = "sortBy", defaultValue = AppConstrants.SORT_BY, required= false) String sortBy,
                @RequestParam(name = "sortOrder", defaultValue = AppConstrants.SORT_DIR, required= false) String sortOrder

                ) {
       CategoryResponse categories = categoryService.getAllCategories(pageNumber, pageSize , sortBy,sortOrder);
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO category) {
        CategoryDTO savedcategory = categoryService.createCategory(category);
        return new ResponseEntity<>(savedcategory,HttpStatus.OK);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {

            CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(deletedCategory, HttpStatus.OK);

    }

    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO category,
                                                 @PathVariable Long categoryId) {

            CategoryDTO savedCategory = categoryService.updateCategory(category,categoryId);
            return new ResponseEntity<>(savedCategory, HttpStatus.OK);
    }
}
