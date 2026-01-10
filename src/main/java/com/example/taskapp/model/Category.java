package com.example.taskapp.model;

import java.util.Set;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("categories")
public class Category {

  @Id
  private long id;
  private String name;

  @MappedCollection(idColumn = "category_id")
  private Set<Task> tasks;
}
