package com.example.taskapp.controller;

import com.example.taskapp.model.Category;
import com.example.taskapp.model.Task;
import com.example.taskapp.service.CategoryService;
import com.example.taskapp.service.TaskService;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;
  private final TaskService taskService;

  @ModelAttribute("categoryList")
  public List<Category> getCategories() {
    return categoryService.getCategoriesList();
  }

  @GetMapping("/categoryList")
  public String showCategoryList(Model model) {
    return "categories";
  }

  @GetMapping("categoryList/{categoryId}/details")
  public String showDetails(
      @PathVariable long categoryId,
      Model model
  ) {
    Category category = categoryService.getCategory(categoryId);
    Set<Task> taskList = category.getTasks();

    model.addAttribute("taskList", taskList);
    return "taskList-details";
  }
}
