package com.ecommerce.ecombackend.Services;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.ecombackend.Exception.APIException;
import com.ecommerce.ecombackend.Exception.ResourceNotFoundException;
import com.ecommerce.ecombackend.Payload.CategoryDTO;
import com.ecommerce.ecombackend.Payload.CategoryResponse;
import com.ecommerce.ecombackend.Repository.CatgoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecombackend.Model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CatgoryRepository categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryResponse getAllCategory(Integer pageNumber,Integer pageSize,String sortBy,String sortorder) {


		Sort sortByAndOrder= sortorder.equalsIgnoreCase("asc")
				? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();


		Pageable pageDetails= PageRequest.of(pageNumber,pageSize,sortByAndOrder);
		Page<Category> categoryPage=categoryRepo.findAll(pageDetails);

		List<Category> categories = categoryPage.getContent();
		if(categories.isEmpty()){
			throw new APIException("No Category found till  now");
		}
		List<CategoryDTO>categoryDTOS=categories.stream().
				map(category -> modelMapper.map(category, CategoryDTO.class))
				.toList();

		CategoryResponse categoryResponse=new CategoryResponse();
		categoryResponse.setContent(categoryDTOS);
		categoryResponse.setPageNumber(categoryPage.getNumber());
		categoryResponse.setPageSize(categoryPage.getSize());
		categoryResponse.setTotalElements(categoryPage.getTotalElements());
		categoryResponse.setTotalPages(categoryPage.getTotalPages());
		categoryResponse.setLastPage(categoryPage.isLast());

		return categoryResponse;
	}

	@Override
	public CategoryDTO saveCategory(CategoryDTO categoryDTO) {

		Category category=modelMapper.map(categoryDTO,Category.class);

		Category categoryFromDB=categoryRepo.findByCategoryName(category.getCategoryName());
		if(categoryFromDB!=null){
			throw new APIException("Category with this name %s is already exist".formatted(category.getCategoryName()));
		}
		Category savedCategory=categoryRepo.save(category);

		return modelMapper.map(savedCategory, CategoryDTO.class);
	}
	
	
	public CategoryDTO deleteCategory(Long categoryId) {
		Category existingCategory=categoryRepo.findById(categoryId).
				orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
		categoryRepo.delete(existingCategory);
		return modelMapper.map(existingCategory,CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
		// TODO Auto-generated method stub
		Category existingCategory=categoryRepo.findById(categoryId).
				orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
		existingCategory.setCategoryName(categoryDTO.getCategoryName());
		categoryRepo.save(existingCategory);

		return modelMapper.map(existingCategory,CategoryDTO.class);
	}

}
