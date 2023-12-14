package org.trainee.dto;


import org.trainee.dto.projection.ProjectProjectionDto;
import org.trainee.dto.projection.TaskProjectionDto;

import java.util.List;
import java.util.Objects;

public class OutGoingPerformerDto {
    private String performerId;
    private String name;
    private String email;
    private String role;
    private List<ProjectProjectionDto> performerProjects;
    private List<TaskProjectionDto> performerTasks;

    public String getPerformerId() {
        return performerId;
    }

    public void setPerformerId(String performerId) {
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

    public List<ProjectProjectionDto> getPerformerProjects() {
        return performerProjects;
    }

    public void setPerformerProjects(List<ProjectProjectionDto> performerProjects) {
        this.performerProjects = performerProjects;
    }

    public List<TaskProjectionDto> getPerformerTasks() {
        return performerTasks;
    }

    public void setPerformerTasks(List<TaskProjectionDto> performerTasks) {
        this.performerTasks = performerTasks;
    }

    @Override
    public String toString() {
        return "OutGoingPerformerDto{" +
                "performerId='" + performerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", performerProjects=" + performerProjects +
                ", performerTasks=" + performerTasks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutGoingPerformerDto)) return false;
        OutGoingPerformerDto that = (OutGoingPerformerDto) o;
        return Objects.equals(performerId, that.performerId) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(role, that.role) && Objects.equals(performerProjects, that.performerProjects) && Objects.equals(performerTasks, that.performerTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(performerId, name, email, role, performerProjects, performerTasks);
    }
}
