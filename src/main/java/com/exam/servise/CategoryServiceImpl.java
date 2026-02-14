package com.exam.servise;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.exam.Category;
import com.exam.repo.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CategoryServiceImpl implements CategoryService{

    private static final Logger logger= LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        
        logger.info("Category saved successfully with ID: {}", category.getCid());
        return this.categoryRepository.save(category);
          

    }

    @Override
    public Category updateCategory(Category category) {
        logger.info("Updating category with ID: {}", category.getCid());
        return this.categoryRepository.save(category);    }

    @Override
    public Set<Category> getCategories() {
         logger.info("Fetching all categories from database");
        return new LinkedHashSet<>( this.categoryRepository.findAll());
    }

    @Override
    public Category getCategory(Long categoryId) {
        logger.info("Fetching category with ID: {}", categoryId);
        return this.categoryRepository.findById(categoryId).get();
    }

    @Override
    public void deleteCategory(Long categoryId) {
        // TODO Auto-generated method stub
        Category category = new Category();
        category.setCid(categoryId);
        this.categoryRepository.delete(category);
        logger.info("Category deleted successfully with ID: {}", categoryId);

    }

  

   

}
