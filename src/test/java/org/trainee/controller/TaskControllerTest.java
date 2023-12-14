package org.trainee.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.trainee.dto.IncomingTaskDto;
import org.trainee.dto.OutGoingTaskDto;
import org.trainee.service.TaskService;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void findAllTasks() throws Exception {
        OutGoingTaskDto taskDto = new OutGoingTaskDto();
        when(taskService.findAll()).thenReturn(Arrays.asList(taskDto));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").value(taskDto)); // Замените на реальные ожидаемые значения
    }

    @Test
    void findTaskById() throws Exception {
        UUID taskId = UUID.randomUUID();
        OutGoingTaskDto taskDto = new OutGoingTaskDto();
        taskDto.setTaskId(taskId.toString());
        when(taskService.findById(taskId)).thenReturn(taskDto);

        mockMvc.perform(get("/tasks/{uuid}", taskId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.taskId").value(taskDto.getTaskId()));
    }

    @Test
    void addNewTask() throws Exception {
        IncomingTaskDto incomingTaskDto = new IncomingTaskDto();
        OutGoingTaskDto savedTaskDto = new OutGoingTaskDto();
        when(taskService.saveOrUpdate(any(IncomingTaskDto.class))).thenReturn(savedTaskDto);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(incomingTaskDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(savedTaskDto));
    }

    @Test
    void updateTask() throws Exception {
        IncomingTaskDto incomingTaskDto = new IncomingTaskDto();
        OutGoingTaskDto updatedTaskDto = new OutGoingTaskDto();
        when(taskService.saveOrUpdate(any(IncomingTaskDto.class))).thenReturn(updatedTaskDto);

        mockMvc.perform(put("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(incomingTaskDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(updatedTaskDto));
    }

    @Test
    void deleteTask() throws Exception {
        UUID taskId = UUID.randomUUID();
        when(taskService.delete(taskId)).thenReturn("Task deleted successfully");

        mockMvc.perform(delete("/tasks/{uuid}", taskId))
                .andExpect(status().isOk())
                .andExpect(content().string("Task deleted successfully"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
