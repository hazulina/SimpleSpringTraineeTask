package org.trainee.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.trainee.dto.IncomingProjectDto;
import org.trainee.dto.OutGoingProjectDto;
import org.trainee.service.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProjectControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    @Test
    public void findAll() throws Exception {
        List<OutGoingProjectDto> projects = new ArrayList<>();
        when(projectService.findAll()).thenReturn(projects);

        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void findById() throws Exception {
        UUID projectId = UUID.randomUUID();
        OutGoingProjectDto projectDto = new OutGoingProjectDto();
        projectDto.setProjectId(projectId.toString());

        when(projectService.findById(projectId)).thenReturn(projectDto);

        mockMvc.perform(get("/projects/{uuid}", projectId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.projectId").value(projectId.toString()));
    }

    @Test
    public void addNewProject() throws Exception {
        IncomingProjectDto incomingProjectDto = new IncomingProjectDto();
        OutGoingProjectDto savedProjectDto = new OutGoingProjectDto();
        when(projectService.saveOrUpdate(any(IncomingProjectDto.class))).thenReturn(savedProjectDto);

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(incomingProjectDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(savedProjectDto));
    }

    @Test
    public void updateProject() throws Exception {
        IncomingProjectDto incomingProjectDto = new IncomingProjectDto();
        OutGoingProjectDto updatedProjectDto = new OutGoingProjectDto();
        when(projectService.saveOrUpdate(any(IncomingProjectDto.class))).thenReturn(updatedProjectDto);

        mockMvc.perform(put("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(incomingProjectDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(updatedProjectDto));
    }

    @Test
    void deleteProject() throws Exception {
        UUID projectId = UUID.randomUUID();
        when(projectService.delete(projectId)).thenReturn("Project deleted successfully");

        mockMvc.perform(delete("/projects/{uuid}", projectId))
                .andExpect(status().isOk())
                .andExpect(content().string("Project deleted successfully"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
