package com.example.taskapp.service;

import com.example.taskapp.controller.CategoryNotFoundException;
import com.example.taskapp.model.Category;
import com.example.taskapp.model.Task;
import com.example.taskapp.model.TaskForm;
import com.example.taskapp.repository.CategoryRepository;
import com.example.taskapp.repository.TaskRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final TaskRepository taskRepository;

  @Transactional
  public void addTaskByCategory(TaskForm taskForm) {
    Category category = categoryRepository.findById(taskForm.getCategoryId())
        .orElseThrow(() -> new CategoryNotFoundException("not found category"));

    Task task = taskForm.toNewTask();

    taskRepository.saveTask(task);
    category.addTask(task);
    categoryRepository.save(category);
  }

  @Transactional
  public void updateCategoryTask(Long taskId, TaskForm taskForm) {
    Category category = categoryRepository.findById(taskForm.getCategoryId())
        .orElseThrow(() -> new CategoryNotFoundException("not found category"));

    Task existingTask = taskRepository.getTaskById(taskId)
        .orElseThrow(() -> new IllegalArgumentException("Task not found"));
    Task updatedTask = taskForm.toUpdatedTask(existingTask);

    taskRepository.saveTask(updatedTask);
    categoryRepository.save(category);
  }

  public List<Category> getCategoriesList() {
    return categoryRepository.findAll();
  }

  public Category getCategory(long categoryId) {
    return categoryRepository.findById(categoryId)
        .orElseThrow(() -> new CategoryNotFoundException("not found category"));
  }
}
