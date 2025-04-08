package com.recipeSite.webApp.repository;

import org.springframework.data.repository.CrudRepository;

import com.recipeSite.webApp.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
