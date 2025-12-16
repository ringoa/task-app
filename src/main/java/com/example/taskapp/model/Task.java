package com.example.taskapp.model;

import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

//taskを表すクラス
@Data
public class Task {
  private static long idCounter = 0;

  private long id;
  private String title;
  private Category category;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dueDate;

  public Task(){
    this.id = ++idCounter;
  }

}
