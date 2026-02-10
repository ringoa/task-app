package com.example.taskapp.dto;

import java.util.List;

public record ApiValidationErrorResponse(
    int status,
    String error,
    String message,
    List<FieldErrorResponse> fieldErrors
) {

}
