package org.trainee.dto;



import org.trainee.dto.projection.PerformerProjectionDto;
import org.trainee.dto.projection.ProjectProjectionDto;

import java.sql.Timestamp;
import java.util.UUID;

public class IncomingTaskDto {
    private UUID taskId;
    private String taskName;
    private String taskDescription;
    private String taskPriority;
    private String taskStatus;
    private Timestamp taskDeadline;
    private PerformerProjectionDto taskPerformer;
    private ProjectProjectionDto taskProject;

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Timestamp getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(Timestamp taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public PerformerProjectionDto getTaskPerformer() {
        return taskPerformer;
    }

    public void setTaskPerformer(PerformerProjectionDto taskPerformer) {
        this.taskPerformer = taskPerformer;
    }

    public ProjectProjectionDto getTaskProject() {
        return taskProject;
    }

    public void setTaskProject(ProjectProjectionDto taskProject) {
        this.taskProject = taskProject;
    }
}
