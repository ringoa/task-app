package com.example.taskapp.repository;

import com.example.taskapp.dao.CategoryDao;
import com.example.taskapp.model.Category;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CategoryRepository {

  private final CategoryDao dao;

  public Optional<Category> findById(long categoryId){
    return dao.findById(categoryId);
  };

  public List<Category> findAll(){
    return dao.findAll();
  }

  public void save(Category category){
    dao.save(category);
  }
}
