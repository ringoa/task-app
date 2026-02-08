package com.example.taskapp.controller.api;

import com.example.taskapp.dto.TaskCreateRequest;
import com.example.taskapp.dto.TaskDetailsResponse;
import com.example.taskapp.dto.TaskPageResponse;
import com.example.taskapp.dto.TaskResponse;
import com.example.taskapp.dto.TaskUpdateRequest;
import com.example.taskapp.model.Task;
import com.example.taskapp.model.TaskForm;
import com.example.taskapp.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tasks")
public class TaskApiController {

  private final TaskService taskService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public TaskPageResponse showTasks(
      @RequestParam(defaultValue = "0")
      @Min(value = 0, message = "ページがありません") int page,
      @RequestParam(defaultValue = "5") int size
  ) {
    Page<Task> taskPage = taskService.getPage(page, size);
    return TaskPageResponse.from(taskPage);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public TaskDetailsResponse showTaskDetails(
      @PathVariable long id
  ) {
    Task task = taskService.getTask(id);
    return TaskDetailsResponse.from(task);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TaskResponse createTask(
      @Valid @RequestBody TaskCreateRequest request
  ) {
    TaskForm form = TaskCreateRequest.newTaskForm(request);
    Task task = taskService.create(form);
    return TaskResponse.from(task);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public TaskResponse updateRequest(
      @PathVariable long id,
      @Valid @RequestBody TaskUpdateRequest request
  ) {
    TaskForm form = TaskUpdateRequest.updateTaskForm(request);
    Task task = taskService.updateTask(id, form);
    return TaskResponse.from(task);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteTask(
      @PathVariable long id
  ) {
    taskService.delete(id);
  }
}
