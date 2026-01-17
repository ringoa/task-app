package com.example.taskapp.service;

import com.example.taskapp.controller.CategoryNotFoundException;
import com.example.taskapp.controller.PageNotFoundException;
import com.example.taskapp.model.Task;
import com.example.taskapp.model.TaskForm;
import com.example.taskapp.repository.CategoryRepository;
import com.example.taskapp.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// タスクの登録、更新、削除、検索などのビジネスロジックを書く
// TaskControllerから呼び出される
@Service
@AllArgsConstructor
public class TaskService {

  private final TaskRepository TaskRepository;
  private final CategoryRepository categoryRepository;

  @Transactional
  public void create(TaskForm form) {
    categoryRepository.findById(form.getCategoryId())
        .orElseThrow(() -> new CategoryNotFoundException("not found category"));
    Task task = form.toNewTask();
    TaskRepository.save(task);
  }

  @Transactional
  public void update(Long id, TaskForm form) {
    Task existingTask = TaskRepository.getTaskById(id)
        .orElseThrow(() -> new IllegalArgumentException("Task not found"));

    categoryRepository.findById(form.getCategoryId())
        .orElseThrow(() -> new CategoryNotFoundException("not found category"));

    Task updatedTask = form.toUpdatedTask(existingTask);

    TaskRepository.save(updatedTask);
  }

  @Transactional
  public void delete(long id) {
    TaskRepository.delete(id);
  }

  @Transactional(readOnly = true)
  public Task getTask(long id) {
    return TaskRepository.getTaskById(id)
        .orElseThrow(() -> new IllegalArgumentException("Task not found"));
  }

  @Transactional(readOnly = true)
  public Page<Task> getPage(int page, int size) {
    Page<Task> taskPage = TaskRepository.findPageOrderedByIdDesc(page, size);
    if (page >= taskPage.getTotalPages()) {
      throw new PageNotFoundException("ページがありません");
    }
    return taskPage;
  }
}
