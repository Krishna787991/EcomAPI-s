package com.ecommerce.ecombackend.Services;

import java.util.List;

import com.ecommerce.ecombackend.Model.Category;

public interface CategoryService {

	List<Category> getAllCategory();
	String saveCategory(Category category);
	String deleteCategory(Long categoryId);
	String updateCategory(Category category,Long categoryId);
}
