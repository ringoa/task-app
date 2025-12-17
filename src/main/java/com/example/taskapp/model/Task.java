package com.example.taskapp.model;

import io.micrometer.common.util.StringUtils;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

// taskを表すクラス
@Data
public class Task {

  private long id;
  private String title;
  private Category category;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dueDate;

  public boolean isValid(){
    if(StringUtils.isEmpty(this.title) || this.category == null
        || this.dueDate == null)
    {
      return false;
    }
    return true;
  }

}
