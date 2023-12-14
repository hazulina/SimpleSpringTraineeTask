package org.trainee.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.trainee.dto.IncomingPerformerDto;
import org.trainee.dto.OutGoingPerformerDto;
import org.trainee.service.PerformerService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    public void findAll() throws Exception {
        List<OutGoingPerformerDto> performer = new ArrayList<>();
        when(performerService.findAll()).thenReturn(performer);

        mockMvc.perform(get("/performers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void findById() throws Exception {
        UUID performerId = UUID.randomUUID();
        OutGoingPerformerDto performerDto = new OutGoingPerformerDto();
        performerDto.setPerformerId(performerId.toString());

        when(performerService.findById(performerId)).thenReturn(performerDto);

        mockMvc.perform(get("/performers/{uuid}", performerId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.performerId").value(performerId.toString()));
    }

    @Test
    public void addNewPerformer() throws Exception {
        IncomingPerformerDto incomingPerformerDto = new IncomingPerformerDto();
        OutGoingPerformerDto savedPerformerDto = new OutGoingPerformerDto();
        when(performerService.saveOrUpdate(any(IncomingPerformerDto.class))).thenReturn(savedPerformerDto);

        mockMvc.perform(post("/performers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(incomingPerformerDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(savedPerformerDto));
    }

    @Test
    public void updatePerformer() throws Exception {
        IncomingPerformerDto incomingPerformerDto = new IncomingPerformerDto();
        OutGoingPerformerDto updatedPerformerDto = new OutGoingPerformerDto();
        when(performerService.saveOrUpdate(any(IncomingPerformerDto.class))).thenReturn(updatedPerformerDto);

        mockMvc.perform(put("/performers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(incomingPerformerDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(updatedPerformerDto));
    }

    @Test
    public void deletePerformer() throws Exception {
        UUID performerId = UUID.randomUUID();
        when(performerService.delete(performerId)).thenReturn("Performer deleted successfully");

        mockMvc.perform(delete("/performers/{uuid}", performerId))
                .andExpect(status().isOk())
                .andExpect(content().string("Performer deleted successfully"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
