package org.trainee.controller;

import org.springframework.web.bind.annotation.*;
import org.trainee.dto.IncomingProjectDto;
import org.trainee.dto.OutGoingProjectDto;
import org.trainee.service.ProjectService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public List<OutGoingProjectDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{uuid}")
    public OutGoingProjectDto findById(@PathVariable(name = "uuid") UUID uuid) {
        return service.findById(uuid);
    }

    @PostMapping
    public OutGoingProjectDto addNewTask(@RequestBody IncomingProjectDto projectDto) {
        return service.saveOrUpdate(projectDto);
    }

    @PutMapping
    public OutGoingProjectDto updateTask(@RequestBody IncomingProjectDto projectDto) {
        return service.saveOrUpdate(projectDto);
    }

    @DeleteMapping("/{uuid}")
    public String deleteTask(@PathVariable(name = "uuid") UUID uuid) {
        return service.delete(uuid);
    }
}
