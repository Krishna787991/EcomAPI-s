package com.ecommerce.ecombackend.Services;

import java.util.List;

import com.ecommerce.ecombackend.Exception.APIException;
import com.ecommerce.ecombackend.Exception.ResourceNotFoundException;
import com.ecommerce.ecombackend.Repository.CatgoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecombackend.Model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CatgoryRepository categoryRepo;

	@Override
	public List<Category> getAllCategory() {
		List<Category> categories=categoryRepo.findAll();
		if(categories.isEmpty()){
			throw new APIException("No Category found till  now");
		}
		return categories;
	}

	@Override
	public String saveCategory(Category category) {
		Category savedCategory=categoryRepo.findByCategoryName(category.getCategoryName());
		if(savedCategory!=null){
			throw new APIException("Category with this name %s is already exist".formatted(category.getCategoryName()));
		}
		categoryRepo.save(category);
		return "category added successfully";
	}
	
	
	public String deleteCategory(Long categoryId) {
		Category existingCategory=categoryRepo.findById(categoryId).
				orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
		categoryRepo.delete(existingCategory);
		return "Category deleted succesfully";
	}

	@Override
	public String updateCategory(Category category, Long categoryId) {
		// TODO Auto-generated method stub
		Category existingCategory=categoryRepo.findById(categoryId).
				orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
		existingCategory.setCategoryName(category.getCategoryName());
		categoryRepo.save(existingCategory);
		return "Category Updated Successfully";
	}

}
