package com.example.taskapp.service;

import com.example.taskapp.client.TaskApiClient;
import com.example.taskapp.controller.ResourceNotFoundException;
import com.example.taskapp.dto.TaskCreateRequest;
import com.example.taskapp.dto.TaskDetailsResponse;
import com.example.taskapp.dto.TaskPageResponse;
import com.example.taskapp.dto.TaskResponse;
import com.example.taskapp.dto.TaskUpdateRequest;
import com.example.taskapp.model.Status;
import com.example.taskapp.model.Task;
import com.example.taskapp.model.TaskForm;
import com.example.taskapp.repository.CategoryRepository;
import com.example.taskapp.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// タスクの登録、更新、削除、検索などのビジネスロジックを書く
// TaskControllerから呼び出される
@Slf4j
@Service
@AllArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;
  private final CategoryRepository categoryRepository;
  private final TaskApiClient taskApiClient;

  @Transactional
  public Task create(TaskForm form) {
    categoryRepository.findById(form.getCategoryId())
        .orElseThrow(() -> new ResourceNotFoundException(
            "カテゴリが見つかりません id=" + form.getCategoryId()));
    Task task = form.toNewTask();
    taskRepository.save(task);
    return task;
  }

  @Transactional
  public Task updateTask(Long id, TaskForm form) {
    Task existingTask = taskRepository.getTaskById(id)
        .orElseThrow(() -> new ResourceNotFoundException("タスクが見つかりません id=" + id));

    categoryRepository.findById(form.getCategoryId())
        .orElseThrow(() -> new ResourceNotFoundException(
            "カテゴリが見つかりません id=" + form.getCategoryId()));

    Task updatedTask = form.toUpdatedTask(existingTask);

    taskRepository.save(updatedTask);
    return existingTask;
  }

  @Transactional
  public void updateStatus(Long id, Status newStatus) {
    Task task = taskRepository.getTaskByIdForUpdate(id)
        .orElseThrow(() -> new ResourceNotFoundException("タスクが見つかりません id=" + id));
    task.setCurrentStatus(newStatus);
    taskRepository.save(task);
  }

  @Transactional
  public void delete(long id) {
    taskRepository.getTaskById(id)
        .orElseThrow(() -> new ResourceNotFoundException("タスクが見つかりません id=" + id));
    taskRepository.delete(id);
  }

  @Transactional(readOnly = true)
  public Task getTask(long id) {
    return taskRepository.getTaskById(id)
        .orElseThrow(() -> new ResourceNotFoundException("タスクが見つかりません id=" + id));
  }

  @Transactional(readOnly = true)
  public Page<Task> getPage(int page, int size) {
    Page<Task> taskPage = taskRepository.findPageOrderedByIdDesc(page, size);
    if (page >= taskPage.getTotalPages()) {
      throw new ResourceNotFoundException("ページが存在しません");
    }
    return taskPage;
  }

  //api用
  @Transactional(readOnly = true)
  public TaskPageResponse getTasksByApi(int page, int size) {
    ResponseEntity<TaskPageResponse> response = taskApiClient.getTaskPage(page, size);
    log.info("status code: {}", response.getStatusCode());

    return response.getBody();
  }

  @Transactional(readOnly = true)
  public TaskDetailsResponse getTaskDetailsByApi(long id) {
    ResponseEntity<TaskDetailsResponse> response = taskApiClient.getTaskDetails(id);
    log.info("status code: {}", response.getStatusCode());

    return response.getBody();
  }

  @Transactional
  public TaskResponse createTaskByApi(TaskCreateRequest request) {
    ResponseEntity<TaskResponse> response = taskApiClient.createTask(request);
    log.info("status code: {}", response.getStatusCode());

    return response.getBody();
  }

  @Transactional
  public TaskResponse updateTaskByApi(long id, TaskUpdateRequest request) {
    ResponseEntity<TaskResponse> response = taskApiClient.updateTask(id, request);
    log.info("status code: {}", response.getStatusCode());

    return response.getBody();
  }

  @Transactional
  public void deleteTaskByApi(long id) {
    taskApiClient.deleteTask(id);
  }
}
