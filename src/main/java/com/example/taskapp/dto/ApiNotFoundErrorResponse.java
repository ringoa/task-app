package com.example.taskapp.dto;

public record ApiNotFoundErrorResponse(
    int status,
    String error,
    String message
) {

}
