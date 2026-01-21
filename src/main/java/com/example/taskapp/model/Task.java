package com.example.taskapp.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

// taskを表すクラス
@AllArgsConstructor
@Data
@Table("tasks")
public class Task {

  @Id
  private Long id;
  private Long categoryId;
  private String title;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dueDate;

  private Status currentStatus;
}
