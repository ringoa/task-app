package com.example.taskapp.dto;

import com.example.taskapp.model.Task;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@Getter
public class TaskPageResponse {

  private List<TaskDetailsResponse> tasks;
  private int page;
  private int size;
  private int totalPages;
  private long totalElements;

  public static TaskPageResponse from(Page<Task> taskPage) {
    List<TaskDetailsResponse> tasks = taskPage.getContent()
        .stream()
        .map(TaskDetailsResponse::from)
        .toList();

    return new TaskPageResponse(
        tasks,
        taskPage.getNumber(),
        taskPage.getSize(),
        taskPage.getTotalPages(),
        taskPage.getTotalElements()
    );
  }
}
