package com.ecommerce.ecombackend.Payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDTO {
    private Long categoryID;
    private String categoryName;

}
