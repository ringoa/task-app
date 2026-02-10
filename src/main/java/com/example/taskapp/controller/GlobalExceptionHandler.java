package com.example.taskapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice(assignableTypes = {TaskController.class, CategoryController.class})
public class GlobalExceptionHandler {

  @ExceptionHandler({
      MethodArgumentTypeMismatchException.class,
      HandlerMethodValidationException.class,
      ResourceNotFoundException.class
  })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handleResourceNotFound(
      Model model,
      Exception ex
  ) {
    model.addAttribute("exception", ex.getClass().getSimpleName());
    model.addAttribute("message", ex.getMessage());
    return "error/resource-not-found-exception";
  }
}
