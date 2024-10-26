package com.ecommerce.ecombackend.Services;

import java.util.List;

import com.ecommerce.ecombackend.Model.Category;
import com.ecommerce.ecombackend.Payload.CategoryDTO;
import com.ecommerce.ecombackend.Payload.CategoryResponse;

public interface CategoryService {

	CategoryResponse getAllCategory(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder);
	CategoryDTO saveCategory(CategoryDTO categoryDTO);
	CategoryDTO deleteCategory(Long categoryId);
	CategoryDTO updateCategory(CategoryDTO categoryDTO,Long categoryId);
}
