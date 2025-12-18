package com.example.taskapp.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public enum Category {
  JAVA("Java"),
  SPRING("Spring"),
  OTHERS("その他");

  private final String label;

  Category(String label) {
    this.label = label;
  }

}
