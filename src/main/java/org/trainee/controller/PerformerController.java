package org.trainee.controller;

import org.springframework.web.bind.annotation.*;
import org.trainee.dto.IncomingPerformerDto;
import org.trainee.dto.OutGoingPerformerDto;
import org.trainee.service.PerformerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/performers")
public class PerformerController {
    private final PerformerService service;

    public PerformerController(PerformerService service) {
        this.service = service;
    }

    @GetMapping
    public List<OutGoingPerformerDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{uuid}")
    public OutGoingPerformerDto findById(@PathVariable(name = "uuid") UUID uuid) {
        return service.findById(uuid);
    }

    @PostMapping
    public OutGoingPerformerDto addNewTask(@RequestBody IncomingPerformerDto performerDto) {
        return service.saveOrUpdate(performerDto);
    }

    @PutMapping
    public OutGoingPerformerDto updateTask(@RequestBody IncomingPerformerDto performerDto) {
        return service.saveOrUpdate(performerDto);
    }

    @DeleteMapping("/{uuid}")
    public String deleteTask(@PathVariable(name = "uuid") UUID uuid) {
        return service.delete(uuid);
    }
}
