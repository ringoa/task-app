package com.example.taskapp.repository;

import com.example.taskapp.model.Task;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

//TaskRepositoryの実装クラス
//TaskServiceから呼び出される
@Repository
public class InMemoryTaskRepository implements TaskRepository {

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
  public void updateTask(Task updaetedTask) {
    long id = updaetedTask.getId();

    Task existingTask = getTaskById(id)
        .orElseThrow(() -> new IllegalStateException("idが見つかりません"));

    deleteTask(existingTask);

    saveTask(updaetedTask);
  }

}
