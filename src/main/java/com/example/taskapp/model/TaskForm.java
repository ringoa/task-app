package com.example.taskapp.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskForm {

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
  private Status currentStatus = Status.TODO;

  public Task toNewTask() {
    Task task = new Task(null, this.categoryId, this.title, this.dueDate, this.currentStatus);
    return task;
  }

  public Task toUpdatedTask(Task existingTask) {
    return new Task(existingTask.getId(), this.categoryId, this.title, this.dueDate,
        this.currentStatus);
  }
}
