package com.example.taskapp.repository;

import com.example.taskapp.dao.TaskDao;
import com.example.taskapp.model.Task;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

// TaskRepositoryの実装クラス
// TaskServiceから呼び出される
@Repository
@AllArgsConstructor
public class TaskRepository {

  private final TaskDao dao;

  public List<Task> findAll() {
    return dao.findAll();
  }

  public void saveTask(Task task) {
    dao.save(task);
  }

  public Optional<Task> getTaskById(long id) {
    return dao.findById(id);
  }

  public void deleteTask(Long id) {
    dao.deleteById(id);
  }

  public Page<Task> findPageOrderedByIdDesc(int page, int size) {
    int offset = page * size;
    List<Task> content = dao.findPageOrderByIdDesc(size, offset);
    long total = dao.countAll();
    return new PageImpl<>(
        content,
        PageRequest.of(page, size),
        total
    );
  }
}