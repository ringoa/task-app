package com.example.taskapp.dao;

import com.example.taskapp.model.Category;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category, Long> {

  @Override
  List<Category> findAll();
}
