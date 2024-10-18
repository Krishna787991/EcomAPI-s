package com.ecommerce.ecombackend.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ecommerce.ecombackend.Repository.CatgoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecombackend.Model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CatgoryRepository categoryRepo;

	@Override
	public List<Category> getAllCategory() {
		return categoryRepo.findAll();
	}

	@Override
	public String saveCategory(Category category) {
		categoryRepo.save(category);
		return "category added successfully";
	}
	
	
	public String deleteCategory(Long categoryId) {
		Category existingCategory=categoryRepo.findById(categoryId).
				orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Category ID"));
		categoryRepo.delete(existingCategory);
		return "Category deleted succesfully";
	}

	@Override
	public String updateCategory(Category category, Long categoryId) {
		// TODO Auto-generated method stub
		Category existingCategory=categoryRepo.findById(categoryId).
				orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Category ID"));
		existingCategory.setCategoryName(category.getCategoryName());
		categoryRepo.save(existingCategory);
		return "Category Updated Successfully";
	}

}
