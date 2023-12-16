package org.trainee.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.trainee.dto.IncomingTaskDto;
import org.trainee.dto.OutGoingTaskDto;
import org.trainee.service.TaskService;

import java.util.Arrays;
import java.util.UUID;

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
        Mockito.when(taskService.findAll()).thenReturn(Arrays.asList(taskDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(taskDto)); // Замените на реальные ожидаемые значения
    }

    @Test
    void findTaskById() throws Exception {
        UUID taskId = UUID.randomUUID();
        OutGoingTaskDto taskDto = new OutGoingTaskDto();
        taskDto.setTaskId(taskId.toString());
        Mockito.when(taskService.findById(taskId)).thenReturn(taskDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/{uuid}", taskId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.taskId").value(taskDto.getTaskId()));
    }

    @Test
    void addNewTask() throws Exception {
        IncomingTaskDto incomingTaskDto = new IncomingTaskDto();
        OutGoingTaskDto savedTaskDto = new OutGoingTaskDto();
        Mockito.when(taskService.saveOrUpdate(ArgumentMatchers.any(IncomingTaskDto.class))).thenReturn(savedTaskDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(incomingTaskDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(savedTaskDto));
    }

    @Test
    void updateTask() throws Exception {
        IncomingTaskDto incomingTaskDto = new IncomingTaskDto();
        OutGoingTaskDto updatedTaskDto = new OutGoingTaskDto();
        Mockito.when(taskService.saveOrUpdate(ArgumentMatchers.any(IncomingTaskDto.class))).thenReturn(updatedTaskDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(incomingTaskDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(updatedTaskDto));
    }

    @Test
    void deleteTask() throws Exception {
        UUID taskId = UUID.randomUUID();
        Mockito.when(taskService.delete(taskId)).thenReturn("Task deleted successfully");

        mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/{uuid}", taskId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Task deleted successfully"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
