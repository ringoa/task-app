package com.example.taskapp.controller;

import com.example.taskapp.model.Category;
import com.example.taskapp.model.Task;
import com.example.taskapp.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @GetMapping("/tasks")
  public String showTasks(Model model) {
    List<Task> taskList = taskService.getTaskList();
    model.addAttribute("taskList", taskList);

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
    if (!task.isValid()) {
      throw new IllegalArgumentException("すべて入力してください");
    }
    taskService.createTask(task);

    ra.addFlashAttribute("message", "新しいタスクを登録しました");

    return "redirect:/tasks";
  }

  @GetMapping("/tasks/edit")
  public String showEditTask(Model model, @RequestParam long id) {
    Task task = taskService.getTask(id);

    model.addAttribute("task", task);
    model.addAttribute("categories", Category.values());

    return "edit";
  }

  @PostMapping("/tasks/edit")
  public String editTask(@ModelAttribute Task updatedTask, RedirectAttributes ra) {
    if (!updatedTask.isValid()) {
      throw new IllegalArgumentException("すべて入力してください");
    }

    taskService.updateTask(updatedTask);

    ra.addFlashAttribute("message", "タスクを更新しました");

    return "redirect:/tasks";
  }

  @PostMapping("/tasks/delete")
  public String getDelete(@RequestParam long id, Model model, RedirectAttributes ra) {
    Task task = taskService.getTask(id);

    taskService.deleteTask(task);

    ra.addFlashAttribute("message", "タスクを削除しました");

    return "redirect:/tasks";
  }
}
