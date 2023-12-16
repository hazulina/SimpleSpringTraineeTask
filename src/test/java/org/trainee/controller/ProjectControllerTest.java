package org.trainee.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.trainee.dto.IncomingProjectDto;
import org.trainee.dto.OutGoingProjectDto;
import org.trainee.service.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class ProjectControllerTest {

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
    void findAll() throws Exception {
        List<OutGoingProjectDto> projects = new ArrayList<>();
        Mockito.when(projectService.findAll()).thenReturn(projects);

        mockMvc.perform(MockMvcRequestBuilders.get("/projects"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    void findById() throws Exception {
        UUID projectId = UUID.randomUUID();
        OutGoingProjectDto projectDto = new OutGoingProjectDto();
        projectDto.setProjectId(projectId.toString());

        Mockito.when(projectService.findById(projectId)).thenReturn(projectDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/projects/{uuid}", projectId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.projectId").value(projectId.toString()));
    }

    @Test
    void addNewProject() throws Exception {
        IncomingProjectDto incomingProjectDto = new IncomingProjectDto();
        OutGoingProjectDto savedProjectDto = new OutGoingProjectDto();
        Mockito.when(projectService.saveOrUpdate(ArgumentMatchers.any(IncomingProjectDto.class))).thenReturn(savedProjectDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(incomingProjectDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(savedProjectDto));
    }

    @Test
    void updateProject() throws Exception {
        IncomingProjectDto incomingProjectDto = new IncomingProjectDto();
        OutGoingProjectDto updatedProjectDto = new OutGoingProjectDto();
        Mockito.when(projectService.saveOrUpdate(ArgumentMatchers.any(IncomingProjectDto.class))).thenReturn(updatedProjectDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(incomingProjectDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(updatedProjectDto));
    }

    @Test
    void deleteProject() throws Exception {
        UUID projectId = UUID.randomUUID();
        Mockito.when(projectService.delete(projectId)).thenReturn("Project deleted successfully");

        mockMvc.perform(MockMvcRequestBuilders.delete("/projects/{uuid}", projectId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Project deleted successfully"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
