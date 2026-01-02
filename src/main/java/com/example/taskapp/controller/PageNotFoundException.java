package com.example.taskapp.controller;

public class PageNotFoundException extends RuntimeException {

  public PageNotFoundException(String message){
    super(message);
  }
}
