package com.example.taskapp.controller;

import com.example.taskapp.model.Category;
import com.example.taskapp.model.Task;
import com.example.taskapp.model.TaskForm;
import com.example.taskapp.service.TaskService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    model.addAttribute("form", new TaskForm());

    return "new";
  }

  @PostMapping("/tasks/register")
  public String createTask(
      @Valid @ModelAttribute("form") TaskForm task,
      BindingResult br,
      RedirectAttributes ra
  ) {
    if (br.hasErrors()) {
      return "new";
    }
    taskService.createTask(task);

    ra.addFlashAttribute("message", "新しいタスクを登録しました");

    return "redirect:/tasks";
  }

  @GetMapping("/tasks/{id}/edit")
  public String showEditTask(Model model, @PathVariable long id) {
    Task task = taskService.getTask(id);

    TaskForm form = new TaskForm(task.getTitle(),task.getCategory(),task.getDueDate());

    model.addAttribute("form", form);
    model.addAttribute("id", id);

    return "edit";
  }

  @PostMapping("/tasks/{id}/edit")
  public String editTask(
      @PathVariable Long id,
      @Valid @ModelAttribute("form") TaskForm updatedTask,
      BindingResult br,
      RedirectAttributes ra
  ) {
    if (br.hasErrors()) {
      return "edit";
    }

    taskService.updateTask(id, updatedTask);

    ra.addFlashAttribute("message", "タスクを更新しました");

    return "redirect:/tasks";
  }

  @PostMapping("/tasks/{id}/delete")
  public String getDelete(@PathVariable long id, RedirectAttributes ra) {
    taskService.deleteTask(id);

    ra.addFlashAttribute("message", "タスクを削除しました");

    return "redirect:/tasks";
  }
}
