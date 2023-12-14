package org.trainee.controller;

import org.springframework.web.bind.annotation.*;
import org.trainee.dto.IncomingTaskDto;
import org.trainee.dto.OutGoingTaskDto;
import org.trainee.service.TaskService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
   public List<OutGoingTaskDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{uuid}")
    public OutGoingTaskDto findById(@PathVariable(name = "uuid") UUID uuid) {
        return service.findById(uuid);
    }

    @PostMapping
    public OutGoingTaskDto addNewTask(@RequestBody IncomingTaskDto taskDto) {
        return service.saveOrUpdate(taskDto);
    }

    @PutMapping
    public OutGoingTaskDto updateTask(@RequestBody IncomingTaskDto taskDto) {
        return service.saveOrUpdate(taskDto);
    }

    @DeleteMapping("/{uuid}")
    public String deleteTask(@PathVariable(name = "uuid") UUID uuid){
        return service.delete(uuid);
    }
}
