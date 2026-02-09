package com.example.taskapp.controller;

import com.example.taskapp.model.Category;
import com.example.taskapp.model.Status;
import com.example.taskapp.model.Task;
import com.example.taskapp.model.TaskForm;
import com.example.taskapp.service.CategoryService;
import com.example.taskapp.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;
  private final CategoryService categoryService;

  @ModelAttribute("categories")
  public List<Category> getCategories() {
    return categoryService.getCategories();
  }

  @ModelAttribute("statuses")
  public Status[] getStatuses() {
    return Status.values();
  }

  @GetMapping
  public String showTasks(
      @RequestParam(defaultValue = "0")
      @Min(value = 0, message = "ページがありません") int page,
      @RequestParam(defaultValue = "5") int size,
      Model model
  ) {
    Page<Task> taskPage = taskService.getPage(page, size);

    model.addAttribute("taskPage", taskPage);
    model.addAttribute("currentPage", page);
    model.addAttribute("size", size);

    return "tasks";
  }

  @PostMapping("/{id}/updateStatus")
  public String updateStatus(
      @RequestParam Status status,
      @RequestParam int currentPage,
      @RequestParam int size,
      @PathVariable long id,
      RedirectAttributes ra
  ) {
    taskService.updateStatus(id, status);
    ra.addAttribute("page", currentPage);
    ra.addAttribute("size", size);
    return "redirect:/tasks";
  }

  @GetMapping("/new")
  public String showTaskForm(Model model) {
    model.addAttribute("form", new TaskForm());

    return "new";
  }

  @PostMapping("/register")
  public String createTask(
      @Valid @ModelAttribute("form") TaskForm task,
      BindingResult br,
      RedirectAttributes ra
  ) {
    if (br.hasErrors()) {
      return "new";
    }

    taskService.create(task);

    ra.addFlashAttribute("message", "新しいタスクを登録しました");

    return "redirect:/tasks";
  }

  @GetMapping("/{id}/edit")
  public String showEditTask(Model model, @PathVariable long id) {
    Task task = taskService.getTask(id);

    TaskForm form = new TaskForm(
        task.getTitle(),
        task.getCategoryId(),
        task.getDueDate(),
        task.getCurrentStatus()
    );

    model.addAttribute("form", form);
    model.addAttribute("id", id);

    return "edit";
  }

  @PostMapping("/{id}/edit")
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

  @PostMapping("/{id}/delete")
  public String getDelete(@PathVariable long id, RedirectAttributes ra) {
    taskService.delete(id);

    ra.addFlashAttribute("message", "タスクを削除しました");

    return "redirect:/tasks";
  }
}
