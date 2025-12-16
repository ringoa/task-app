package com.example.taskapp.controller;

import com.example.taskapp.model.Category;
import com.example.taskapp.model.Task;
import com.example.taskapp.service.TaskService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping("/tasks")
  public String showTasks(Model model) {
    List<Task> tasklList = taskService.getTaskList();
    model.addAttribute("taskList", tasklList);

    return "tasks";
  }

  @GetMapping("/tasks/new")
  public String showTaskForm(Model model) {
    model.addAttribute("task", new Task());

    model.addAttribute("categories", Category.values());

    return "new";
  }

  @PostMapping("/tasks/register")
  public String createTask(@ModelAttribute Task task, RedirectAttributes ra) {
    taskService.createTask(task);

    ra.addFlashAttribute("message", "新しいタスクを登録しました");

    return "redirect:/tasks";
  }

  @GetMapping("/tasks/edit")
  public String showEditTask(Model model, @RequestParam long id) {
    Task task = taskService.getTask(id)
        .orElseThrow(() -> new IllegalArgumentException("idが存在しません"));

    model.addAttribute("task", task);
    model.addAttribute("categories", Category.values());

    return "edit";
  }

  @PostMapping("/tasks/edit")
  public String editTask(@ModelAttribute Task updaetedTask, RedirectAttributes ra) {
    taskService.updateTask(updaetedTask);

    ra.addFlashAttribute("message", "タスクを更新しました");
    return "redirect:/tasks";
  }

  @GetMapping("/tasks/delete")
  public String getDelete(@RequestParam long id, Model mode, RedirectAttributes ra) {
    Task task = taskService.getTask(id)
        .orElseThrow(() -> new IllegalArgumentException("idが見つかりません"));
    taskService.deleteTask(task);

    ra.addFlashAttribute("message", "タスクを削除しました");

    return "redirect:/tasks";
  }
}
