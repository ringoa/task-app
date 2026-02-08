package com.example.taskapp.controller.api;

import com.example.taskapp.dto.TaskCreateRequest;
import com.example.taskapp.dto.TaskDetailsResponse;
import com.example.taskapp.dto.TaskPageResponse;
import com.example.taskapp.dto.TaskResponse;
import com.example.taskapp.dto.TaskUpdateRequest;
import com.example.taskapp.service.TaskService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest-template/tasks")
public class TestTemplateTaskApiController {

  private final TaskService taskService;

  @GetMapping
  public TaskPageResponse getTasks(
      @RequestParam(defaultValue = "0")
      @Min(value = 0, message = "ページがありません") int page,
      @RequestParam(defaultValue = "5") int size) {
    return taskService.getTasksByApi(page, size);
  }

  @GetMapping("/{id}")
  public TaskDetailsResponse getTaskDetails(@PathVariable long id) {
    return taskService.getTaskDetailsByApi(id);
  }

  @PostMapping
  public TaskResponse createTask(@RequestBody TaskCreateRequest request) {
    return taskService.createTaskByApi(request);
  }

  @PutMapping("/{id}")
  public TaskResponse updateTask(@PathVariable long id, @RequestBody TaskUpdateRequest request) {
    return taskService.updateTaskByApi(id, request);
  }

  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable long id) {
    taskService.deleteTaskByApi(id);
  }
}
