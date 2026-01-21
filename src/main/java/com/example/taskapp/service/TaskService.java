package com.example.taskapp.service;

import com.example.taskapp.controller.ResourceNotFoundException;
import com.example.taskapp.model.Status;
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

  private final TaskRepository taskRepository;
  private final CategoryRepository categoryRepository;

  @Transactional
  public void create(TaskForm form) {
    categoryRepository.findById(form.getCategoryId())
        .orElseThrow(() -> new ResourceNotFoundException("カテゴリが見つかりません"));
    Task task = form.toNewTask();
    taskRepository.save(task);
  }

  @Transactional
  public void updateTask(Long id, TaskForm form) {
    Task existingTask = taskRepository.getTaskById(id)
        .orElseThrow(() -> new ResourceNotFoundException("タスクが見つかりません"));

    categoryRepository.findById(form.getCategoryId())
        .orElseThrow(() -> new ResourceNotFoundException("カテゴリが見つかりません"));

    Task updatedTask = form.toUpdatedTask(existingTask);

    taskRepository.save(updatedTask);
  }

  @Transactional
  public void updateStatus(Long id, Status newStatus) {
    Task task = taskRepository.getTaskByIdForUpdate(id)
        .orElseThrow(() -> new ResourceNotFoundException("タスクが見つかりません"));
    task.setCurrentStatus(newStatus);
    taskRepository.save(task);
  }

  @Transactional
  public void delete(long id) {
    taskRepository.delete(id);
  }

  @Transactional(readOnly = true)
  public Task getTask(long id) {
    return taskRepository.getTaskById(id)
        .orElseThrow(() -> new ResourceNotFoundException("タスクが見つかりません"));
  }

  @Transactional(readOnly = true)
  public Page<Task> getPage(int page, int size) {
    Page<Task> taskPage = taskRepository.findPageOrderedByIdDesc(page, size);
    if (page >= taskPage.getTotalPages()) {
      throw new ResourceNotFoundException("ページがありません");
    }
    return taskPage;
  }
}
