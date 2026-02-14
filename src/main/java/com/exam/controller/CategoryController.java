package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exam.model.exam.Category;
import com.exam.servise.CategoryService;

@RestController

@RequestMapping("/category")
public class CategoryController {  

    private static final Logger logger= LoggerFactory.getLogger(CategoryController.class);
    
    @Autowired
    private CategoryService categoryService;
    
    //add catagory
    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category)
    {
        logger.info("Received request to add category with title: {}",category.getTitle());
        Category category1=this.categoryService.addCategory(category);
        logger.info("Category added successfully with ID: {}", category.getCid());
        return ResponseEntity.ok(category1);
    }

    //get category
    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") Long categoryId)
    {
        logger.info("fetching category with id:{}",categoryId);
        return this.categoryService.getCategory(categoryId);
    }


    //all category
    @GetMapping("/")
    public ResponseEntity<?>getCatagories(){
        logger.info("fetching all categories");
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    //update
    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category)
    {
        logger.info("Updating category with ID: {}", category.getCid());
        return this.categoryService.updateCategory(category);
    }
    
    //delete
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId)
    {
        logger.warn("Deleting category with ID: {}", categoryId);
        this.categoryService.deleteCategory(categoryId);
        logger.info("Category deleted successfully with ID: {}", categoryId);
    }

}
