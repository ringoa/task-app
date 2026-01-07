package com.example.taskapp.controller;

public class CategoryNotFoundException extends RuntimeException {
  public CategoryNotFoundException(String message){
    super(message);
  }
}
