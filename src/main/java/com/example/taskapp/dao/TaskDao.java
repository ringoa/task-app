package com.example.taskapp.dao;

import com.example.taskapp.model.Task;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TaskDao extends CrudRepository<Task, Long> {

  @Override
  List<Task> findAll();

  @Query("SELECT * FROM tasks ORDER BY id DESC LIMIT :size OFFSET :offset")
  List<Task> findPageOrderByIdDesc(
      @Param("size") int size,
      @Param("offset") int offset
  );

  @Query("SELECT COUNT(*) FROM tasks")
  long countAll();

  @Query("SELECT * FROM tasks WHERE id = :id FOR UPDATE")
  Optional<Task> findByIdForUpdate(long id);
}
