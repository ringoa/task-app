package com.example.taskapp.dao;

import com.example.taskapp.model.Task;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TaskDao extends CrudRepository<Task, Long> {

  @Override
  List<Task> findAll();
}
