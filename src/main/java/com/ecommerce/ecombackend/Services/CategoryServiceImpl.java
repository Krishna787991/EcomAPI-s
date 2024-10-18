package com.ecommerce.ecombackend.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecombackend.Model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	private long nextval=1L;

	private List<Category> categories=new ArrayList<>();
	
	@Override
	public List<Category> getAllCategory() {
		return categories;
	}

	@Override
	public String saveCategory(Category category) {
		category.setCategoryId(nextval++);
		System.out.println(nextval);
		categories.add(category);
		return "category added successfully";
	}
	
	
	public String deleteCategory(Long categoryId) {
		Category foundCategory = categories.stream()
			    .filter(c -> c.getCategoryId() == categoryId)
			    .findFirst()
			    .orElseThrow(() -> new ResponseStatusException(
		                HttpStatus.NOT_FOUND, "Category not found"));
		categories.remove(foundCategory);
		return "Category deleted succesfully";
	}

	@Override
	public String updateCategory(Category category, Long categoryId) {
		// TODO Auto-generated method stub
		Category existingCategory=categories.stream().
		filter(c->c.getCategoryId()==categoryId)
		.findFirst()
		.orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Invalid categoryId"));
		existingCategory.setCategoryName(category.getCategoryName());
		
		return "Category Updated Successfully";

	}

}
