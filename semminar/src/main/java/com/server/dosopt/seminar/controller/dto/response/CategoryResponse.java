package com.server.dosopt.seminar.controller.dto.response;


import com.server.dosopt.seminar.domain.Category;
import com.server.dosopt.seminar.domain.CategoryId;

public record CategoryResponse(
        Long id,
        String name
        ) {
    public static CategoryResponse of(Category category){
        CategoryId categoryId = category.getCategoryId();
        return new CategoryResponse(
                categoryId.getCategoryId(),
                category.getName()
        );
    }
}
