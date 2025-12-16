package com.example.taskapp.service;

import com.example.taskapp.model.Task;
import com.example.taskapp.repository.InMemoryTaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

//タスクの登録、更新、削除、検索などのビジネスロジックを書く
//TaskControllerぁら呼び出される
@Service
public class TaskService {

  private final InMemoryTaskRepository repository;

  public TaskService(InMemoryTaskRepository repository) {
    this.repository = repository;
  }

  public void createTask(Task task) {
    if (task.getTitle() == null || task.getCategory() == null || task.getDueDate() == null) {
      throw new IllegalArgumentException("すべて入力してください");
    }
    repository.saveTask(task);
  }

  public List<Task> getTaskList() {

    return repository.getTaskList();
  }

  public void deleteTask(Task task) {

    repository.deleteTask(task);
  }

  public void updateTask(Task updaetedTask) {
    if (updaetedTask.getTitle() == null || updaetedTask.getCategory() == null
        || updaetedTask.getDueDate() == null) {
      throw new IllegalArgumentException("すべて入力してください");
    }

    repository.updateTask(updaetedTask);
  }

  public Optional<Task> getTask(long id) {
    return repository.getTaskById(id);
  }

}
