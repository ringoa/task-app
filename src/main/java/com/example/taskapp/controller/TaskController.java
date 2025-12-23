package com.example.taskapp.controller;

import com.example.taskapp.model.Category;
import com.example.taskapp.model.Task;
import com.example.taskapp.service.TaskService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @ModelAttribute("categories")
  public Category[] getCategories() {
    return Category.values();
  }

  @GetMapping("/tasks")
  public String showTasks(Model model) {
    List<Task> taskList = taskService.getTaskList();
    model.addAttribute("taskList", taskList);

    return "tasks";
  }

  @GetMapping("/tasks/new")
  public String showTaskForm(Model model) {
    model.addAttribute("task", new Task());

    return "new";
  }

  @PostMapping("/tasks/register")
  public String createTask(
      @Valid @ModelAttribute Task task,
      BindingResult br,
      RedirectAttributes ra,
      Model model
  ) {
    if (br.hasErrors()) {
      return "new";
    }
    taskService.createTask(task);

    ra.addFlashAttribute("message", "新しいタスクを登録しました");

    return "redirect:/tasks";
  }

  @GetMapping("/tasks/edit")
  public String showEditTask(Model model, @RequestParam long id) {
    Task task = taskService.getTask(id);

    model.addAttribute("task", task);

    return "edit";
  }

  @PostMapping("/tasks/edit")
  public String editTask(
      @Valid @ModelAttribute Task updatedTask,
      BindingResult br,
      RedirectAttributes ra,
      Model model
  ) {
    if (br.hasErrors()) {
      return "edit";
    }

    taskService.updateTask(updatedTask);

    ra.addFlashAttribute("message", "タスクを更新しました");

    return "redirect:/tasks";
  }

  @PostMapping("/tasks/delete")
  public String getDelete(@RequestParam long id, RedirectAttributes ra) {
    taskService.deleteTask(id);

    ra.addFlashAttribute("message", "タスクを削除しました");

    return "redirect:/tasks";
  }
}
