package com.example.taskapp.client;

import com.example.taskapp.dto.TaskCreateRequest;
import com.example.taskapp.dto.TaskDetailsResponse;
import com.example.taskapp.dto.TaskPageResponse;
import com.example.taskapp.dto.TaskResponse;
import com.example.taskapp.dto.TaskUpdateRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class TaskApiClient {

  private final RestTemplate restTemplate;

  @Value("${external-api.base-url}")
  private String baseUrl;

  public ResponseEntity<TaskPageResponse> getTaskPage(int page, int size) {
    URI uri = UriComponentsBuilder
        .fromUriString(baseUrl)
        .path("/api/tasks")
        .queryParam("page", page)
        .queryParam("size", size)
        .build()
        .toUri();
    try {
      return restTemplate.getForEntity(uri, TaskPageResponse.class);
    } catch (HttpClientErrorException e) {
      throw new ResponseStatusException(
          e.getStatusCode(),
          e.getResponseBodyAsString()
      );
    }
  }

  public ResponseEntity<TaskDetailsResponse> getTaskDetails(long id) {
    String url = baseUrl + "/api/tasks/" + id;
    try {
      return restTemplate.getForEntity(url, TaskDetailsResponse.class);
    } catch (HttpClientErrorException e) {
      throw new ResponseStatusException(
          e.getStatusCode(),
          e.getResponseBodyAsString()
      );
    }
  }

  public ResponseEntity<TaskResponse> createTask(
      TaskCreateRequest request
  ) {
    String url = baseUrl + "/api/tasks";
    try {
      return restTemplate.postForEntity(url, request, TaskResponse.class);
    } catch (HttpClientErrorException e) {
      throw new ResponseStatusException(
          e.getStatusCode(),
          e.getResponseBodyAsString()
      );
    }
  }

  public ResponseEntity<TaskResponse> updateTask(long id, TaskUpdateRequest request) {
    String url = baseUrl + "/api/tasks/" + id;

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<TaskUpdateRequest> httpEntity = new HttpEntity<>(request, headers);

    try {
      return restTemplate.exchange(
          url,
          HttpMethod.PUT,
          httpEntity,
          TaskResponse.class
      );
    } catch (HttpClientErrorException e) {
      throw new ResponseStatusException(
          e.getStatusCode(),
          e.getResponseBodyAsString()
      );
    }
  }

  public void deleteTask(long id) {
    String url = baseUrl + "/api/tasks/" + id;

    try {
      restTemplate.exchange(
          url,
          HttpMethod.DELETE,
          null,
          Void.class
      );
    } catch (HttpClientErrorException e) {
      throw new ResponseStatusException(
          e.getStatusCode(),
          e.getResponseBodyAsString()
      );
    }
  }
}

