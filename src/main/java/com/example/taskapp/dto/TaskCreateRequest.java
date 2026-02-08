package com.example.taskapp.dto;

import com.example.taskapp.model.Status;
import com.example.taskapp.model.TaskForm;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@Getter
public class TaskCreateRequest {

  @NotBlank(message = "必須です")
  @Size(max = 50, message = "50文字までです")
  private String title;

  @NotNull(message = "選択してください")
  private Long categoryId;

  @NotNull(message = "必須です")
  @FutureOrPresent(message = "本日以降の日付を入力してください")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dueDate;

  @NotNull(message = "必須です")
  private Status currentStatus;

  public static TaskForm newTaskForm(TaskCreateRequest task) {
    return new TaskForm(
        task.getTitle(),
        task.getCategoryId(),
        task.getDueDate(),
        task.getCurrentStatus()
    );
  }
}
