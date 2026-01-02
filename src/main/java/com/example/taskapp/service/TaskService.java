package com.example.taskapp.service;

import com.example.taskapp.model.Task;
import com.example.taskapp.model.TaskForm;
import com.example.taskapp.repository.TaskRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// タスクの登録、更新、削除、検索などのビジネスロジックを書く
// TaskControllerから呼び出される
@Service
@AllArgsConstructor
public class TaskService {

  private final TaskRepository repository;

  @Transactional
  public void createTask(TaskForm form) {
    Task task = form.toNewTask();
    repository.saveTask(task);
  }

  public List<Task> getTaskList() {
    return repository.findAll();
  }

  @Transactional
  public void deleteTask(long id) {
    repository.deleteTask(id);
  }

  @Transactional
  public void updateTask(Long id, TaskForm form) {
    Task existingTask = repository.getTaskById(id)
        .orElseThrow(() -> new IllegalArgumentException("Task not found"));
    Task updatedTask = form.toUpdatedTask(existingTask);

    repository.saveTask(updatedTask);
  }

  public Task getTask(long id) {
    return repository.getTaskById(id)
        .orElseThrow(() -> new IllegalArgumentException("Task not found"));
  }

  @Transactional(readOnly = true)
  public Page<Task> getPage(int page, int size){
    return repository.findPageOrderedByIdDesc(page, size);
  }
}
