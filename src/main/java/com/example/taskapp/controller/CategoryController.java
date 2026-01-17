package com.example.taskapp.controller;

import com.example.taskapp.model.Category;
import com.example.taskapp.model.Task;
import com.example.taskapp.service.CategoryService;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @ModelAttribute("categories")
  public List<Category> getCategories() {
    return categoryService.getCategories();
  }

  @GetMapping
  public String showCategories() {
    return "categories";
  }

  @GetMapping("/{categoryId}/details")
  public String showDetails(
      @PathVariable long categoryId,
      Model model
  ) {
    Category category = categoryService.getCategory(categoryId);
    Set<Task> tasks = category.getTasks();

    model.addAttribute("tasks", tasks);
    return "task-list-details";
  }
}
