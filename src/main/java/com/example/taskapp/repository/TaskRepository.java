package com.example.taskapp.repository;

import com.example.taskapp.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// タスクの保存と取得を行うインターフェース
public interface TaskRepository {

  void saveTask(Task task);

  List<Task> getTaskList();

  Optional<Task> getTaskById(long id);

  void deleteTask(Task task);

  void updateTask(Task task, Task existingTask);

  long getNextId();
}
