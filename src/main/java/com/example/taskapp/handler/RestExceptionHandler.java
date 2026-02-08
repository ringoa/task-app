package com.example.taskapp.handler;

import com.example.taskapp.controller.ResourceNotFoundException;
import com.example.taskapp.controller.api.TaskApiController;
import com.example.taskapp.dto.ApiNotFoundErrorResponse;
import com.example.taskapp.dto.ApiValidationErrorResponse;
import com.example.taskapp.dto.FieldErrorResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = TaskApiController.class)
public class RestExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiNotFoundErrorResponse> handleApiNotFound(
      ResourceNotFoundException ex
  ) {
    ApiNotFoundErrorResponse body = new ApiNotFoundErrorResponse(
        HttpStatus.NOT_FOUND.value(),
        "NOT_FOUND",
        ex.getMessage()
    );

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
  public ResponseEntity<ApiValidationErrorResponse> handleValidationError(
      Exception ex
  ) {
    List<FieldErrorResponse> fieldErrors = new ArrayList<>();

    if (ex instanceof MethodArgumentNotValidException validEx) {
      validEx
          .getBindingResult()
          .getFieldErrors()
          .forEach(e -> fieldErrors.add(
              new FieldErrorResponse(e.getField(), e.getDefaultMessage())
          ));
    }
    if (ex instanceof HttpMessageNotReadableException) {
      fieldErrors.add(new FieldErrorResponse(
          "status",
          "不正な値です"
      ));
    }

    ApiValidationErrorResponse body = new ApiValidationErrorResponse(
        400,
        "BAD_REQUEST",
        "入力値が不正です",
        fieldErrors
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiNotFoundErrorResponse> handleException(
      Exception ex
  ) {
    ApiNotFoundErrorResponse body = new ApiNotFoundErrorResponse(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "INTERNAL_SERVER_ERROR",
        "予期しないエラーが発生しました。"
    );

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
  }
}
