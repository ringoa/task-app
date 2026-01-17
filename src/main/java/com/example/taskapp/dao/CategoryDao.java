package com.example.taskapp.dao;

import com.example.taskapp.model.Category;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category, Long> {

  List<Category> findAllByOrderByIdAsc();
}
