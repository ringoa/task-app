package com.example.taskapp.dto;

import com.example.taskapp.model.Status;
import com.example.taskapp.model.Task;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TaskResponse {

  private Long id;
  private Long categoryId;
  private String title;
  private LocalDate dueDate;
  private Status currentStatus;

  public static TaskResponse from(Task task) {
    return new TaskResponse(
        task.getId(),
        task.getCategoryId(),
        task.getTitle(),
        task.getDueDate(),
        task.getCurrentStatus()
    );
  }
}
