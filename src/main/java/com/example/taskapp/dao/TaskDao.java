package com.example.taskapp.dao;

import com.example.taskapp.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskDao extends CrudRepository<Task, Long> {

}
