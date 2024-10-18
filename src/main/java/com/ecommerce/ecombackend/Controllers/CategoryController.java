package com.ecommerce.ecombackend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecombackend.Services.CategoryService;
import com.ecommerce.ecombackend.Model.Category;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	public CategoryService categoryService;
	
	
	@GetMapping("/public/categories")
	public List<Category> getAllCategory(){
		return categoryService.getAllCategory();
	}
	
	@PostMapping("/public/categories")
	public String saveCategory(@RequestBody Category category) {

		return categoryService.saveCategory(category);
	}
	
	@DeleteMapping("/public/categories/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
		try {			
			String status=categoryService.deleteCategory(categoryId);
			return new ResponseEntity<>(status,HttpStatus.OK);
		}
		catch(ResponseStatusException e) {
			return new ResponseEntity<>("categoryId "+ categoryId +" not found ",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/public/categories/{categoryId}")
	public ResponseEntity<String> updateCategory(@RequestBody Category category,@PathVariable Long categoryId) {
		try {			
			String status=categoryService.updateCategory(category, categoryId);
			return new ResponseEntity<>(status,HttpStatus.OK);
		}
		catch(ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(),HttpStatus.BAD_REQUEST);
		}
	}
	
}
