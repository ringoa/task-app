package com.example.taskapp.service;

import com.example.taskapp.model.Task;
import com.example.taskapp.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

// タスクの登録、更新、削除、検索などのビジネスロジックを書く
// TaskControllerから呼び出される
@Service
public class TaskService {

  private final TaskRepository repository;

  public TaskService(TaskRepository repository) {
    this.repository = repository;
  }

  public void createTask(Task task) {
    long id = repository.getNextId();
    task.setId(id);
    repository.saveTask(task);
  }

  public List<Task> getTaskList() {
    return repository.getTaskList();
  }

  public void deleteTask(long id) {
    Optional<Task> task = repository.getTaskById(id);
    task.ifPresent(repository::deleteTask);
  }

  public void updateTask(Task updatedTask) {
    long id = updatedTask.getId();
    Task existingTask = getTask(id);

    repository.updateTask(updatedTask, existingTask);
  }

  public Task getTask(long id) {
    return repository.getTaskById(id)
        .orElseThrow(() -> new IllegalArgumentException("idが存在しません"));
  }

}
