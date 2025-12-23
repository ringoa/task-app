package com.example.taskapp.repository;

import com.example.taskapp.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

// TaskRepositoryの実装クラス
// TaskServiceから呼び出される
@Repository
public class InMemoryTaskRepository implements TaskRepository {

  List<Task> taskList = new ArrayList<>();

  @Override
  public void saveTask(Task task) {
    taskList.add(task);
  }

  @Override
  public List<Task> getTaskList() {

    return taskList;
  }

  @Override
  public Optional<Task> getTaskById(long id) {

    return taskList.stream()
        .filter(list -> list.getId() == id)
        .findFirst();
  }

  @Override
  public void deleteTask(Task task) {
    List<Task> taskList = getTaskList();
    taskList.remove(task);
  }

  @Override
  public void updateTask(Task updaetedTask, Task existingTask) {

    deleteTask(existingTask);

    saveTask(updaetedTask);
  }

  @Override
  public long getNextId() {

    long maxId = taskList.stream()
        .mapToLong(Task::getId)
        .max()
        .orElse(0);

    return maxId + 1;
  }

}
