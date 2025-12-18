package com.example.taskapp.model;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

// taskを表すクラス
@Data
public class Task {

  private long id;

  @NotBlank(message = "必須です")
  @Size(max = 50, message = "50文字までです")
  private String title;

  @NotNull(message = "選択してください")
  private Category category;

  @NotNull(message = "必須です")
  @FutureOrPresent(message = "本日以降の日付を入力してください")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dueDate;

}
