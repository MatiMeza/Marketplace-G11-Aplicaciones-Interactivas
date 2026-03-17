package com.uade.tpo.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.demo.entity.Category;
import com.uade.tpo.demo.exceptions.CategoryDuplicateException;

public interface CategoryService {
    public ArrayList<Category> getCategories();

    public Optional<Category> getCategoryById(int categoryId);

    public Category createCategory(int newCategoryId, String description) throws CategoryDuplicateException;
    
}
