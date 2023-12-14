package org.trainee.dto;

import org.trainee.entity.ProjectEntity;
import org.trainee.entity.TaskEntity;

import java.util.List;
import java.util.UUID;

public class IncomingPerformerDto {
    private UUID performerId;
    private String name;
    private String email;
    private String role;
    private List<ProjectEntity> performerProjects;
    private List<TaskEntity> performerTasks;

    public UUID getPerformerId() {
        return performerId;
    }

    public void setPerformerId(UUID performerId) {
        this.performerId = performerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<ProjectEntity> getPerformerProjects() {
        return performerProjects;
    }

    public void setPerformerProjects(List<ProjectEntity> performerProjects) {
        this.performerProjects = performerProjects;
    }

    public List<TaskEntity> getPerformerTasks() {
        return performerTasks;
    }

    public void setPerformerTasks(List<TaskEntity> performerTasks) {
        this.performerTasks = performerTasks;
    }

    @Override
    public String toString() {
        return "IncomingPerformerDto{" +
                "performerId=" + performerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", performerProjects=" + performerProjects +
                ", performerTasks=" + performerTasks +
                '}';
    }
}
