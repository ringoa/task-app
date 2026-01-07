package com.example.taskapp.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryForm {
  //これどこで使うの？
  @NotBlank(message = "カテゴリを選択してください")
  private String name;
}
