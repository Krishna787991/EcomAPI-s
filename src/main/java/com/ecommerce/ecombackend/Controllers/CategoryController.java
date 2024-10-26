package com.ecommerce.ecombackend.Controllers;

import java.util.List;

import com.ecommerce.ecombackend.Config.AppConstants;
import com.ecommerce.ecombackend.Payload.CategoryDTO;
import com.ecommerce.ecombackend.Payload.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecombackend.Services.CategoryService;
import com.ecommerce.ecombackend.Model.Category;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	public CategoryService categoryService;


	@GetMapping("/public/categories")
	public ResponseEntity<CategoryResponse> getAllCategory(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
														   @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
														   @RequestParam(value="sortBy",defaultValue = AppConstants.SORT_CATEGORIES_BY,required = false) String sortBy,
														   @RequestParam(value="sortOrder",defaultValue = AppConstants.SORT_DIR,required = false) String sortOrder){
		CategoryResponse categoryResponse=categoryService.getAllCategory(pageNumber,pageSize,sortBy,sortOrder);
		return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
	}

	@PostMapping("/public/categories")
	public ResponseEntity<CategoryDTO> saveCategory( @Valid @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO savedcategoryDTO= categoryService.saveCategory(categoryDTO);
		return new ResponseEntity<>(savedcategoryDTO,HttpStatus.OK);
	}

	@DeleteMapping("/public/categories/{categoryId}")
	public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
			CategoryDTO deletedCategoryDTO=categoryService.deleteCategory(categoryId);
			return new ResponseEntity<>(deletedCategoryDTO,HttpStatus.OK);
	}

	@PutMapping("/public/categories/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable Long categoryId) {

		CategoryDTO updatedDTO=categoryService.updateCategory(categoryDTO, categoryId);
		return new ResponseEntity<>(updatedDTO,HttpStatus.OK);
	}

}
