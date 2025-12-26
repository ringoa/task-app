package com.example.taskapp.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String notFoundTaskException(
      IllegalArgumentException ex,
      Model model,
      HttpServletResponse res
  ) {
    res.setStatus(HttpServletResponse.SC_NOT_FOUND);
    model.addAttribute("exception", ex.getClass().getSimpleName());
    model.addAttribute("message", ex.getMessage());
    return "error/not-found-task";
  }
}
