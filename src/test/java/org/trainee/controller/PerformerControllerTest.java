package org.trainee.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.trainee.dto.IncomingPerformerDto;
import org.trainee.dto.OutGoingPerformerDto;
import org.trainee.service.PerformerService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class PerformerControllerTest {

    private MockMvc mockMvc;

    @Mock
    PerformerService performerService;

    @InjectMocks
    PerformerController performerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(performerController).build();
    }

    @Test
    void findAll() throws Exception {
        List<OutGoingPerformerDto> performer = new ArrayList<>();
        Mockito.when(performerService.findAll()).thenReturn(performer);

        mockMvc.perform(MockMvcRequestBuilders.get("/performers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    void findById() throws Exception {
        UUID performerId = UUID.randomUUID();
        OutGoingPerformerDto performerDto = new OutGoingPerformerDto();
        performerDto.setPerformerId(performerId.toString());

        Mockito.when(performerService.findById(performerId)).thenReturn(performerDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/performers/{uuid}", performerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.performerId").value(performerId.toString()));
    }

    @Test
    void addNewPerformer() throws Exception {
        IncomingPerformerDto incomingPerformerDto = new IncomingPerformerDto();
        OutGoingPerformerDto savedPerformerDto = new OutGoingPerformerDto();
        Mockito.when(performerService.saveOrUpdate(ArgumentMatchers.any(IncomingPerformerDto.class))).thenReturn(savedPerformerDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/performers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(incomingPerformerDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(savedPerformerDto));
    }

    @Test
    void updatePerformer() throws Exception {
        IncomingPerformerDto incomingPerformerDto = new IncomingPerformerDto();
        OutGoingPerformerDto updatedPerformerDto = new OutGoingPerformerDto();
        Mockito.when(performerService.saveOrUpdate(ArgumentMatchers.any(IncomingPerformerDto.class))).thenReturn(updatedPerformerDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/performers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(incomingPerformerDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(updatedPerformerDto));
    }

    @Test
    void deletePerformer() throws Exception {
        UUID performerId = UUID.randomUUID();
        Mockito.when(performerService.delete(performerId)).thenReturn("Performer deleted successfully");

        mockMvc.perform(MockMvcRequestBuilders.delete("/performers/{uuid}", performerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Performer deleted successfully"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
