package com.example.taskapp.service;

import com.example.taskapp.controller.CategoryNotFoundException;
import com.example.taskapp.model.Category;
import com.example.taskapp.repository.CategoryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  @Transactional(readOnly = true)
  public List<Category> getCategories() {
    return categoryRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Category getCategory(long categoryId) {
    return categoryRepository.findById(categoryId)
        .orElseThrow(() -> new CategoryNotFoundException("not found category"));
  }
}
