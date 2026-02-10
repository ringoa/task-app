package com.example.taskapp.dto;

public record FieldErrorResponse(
    String field,
    String message
) {

}
