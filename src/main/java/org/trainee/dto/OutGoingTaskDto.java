package org.trainee.dto;


import org.trainee.dto.projection.PerformerProjectionDto;
import org.trainee.dto.projection.ProjectProjectionDto;

import java.util.Objects;

public class OutGoingTaskDto {

    private String taskId;
    private String taskName;
    private String taskDescription;
    private String taskPriority;
    private String taskStatus;
    private String taskDeadline;
    private PerformerProjectionDto taskPerformer;
    private ProjectProjectionDto taskProject;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
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

    public String getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(String taskDeadline) {
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

    @Override
    public String toString() {
        return "OutGoingTaskDto: {" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskPriority='" + taskPriority + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                ", taskDeadline='" + taskDeadline + '\'' +
                ", taskPerformer=" + taskPerformer +
                ", taskProject=" + taskProject +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutGoingTaskDto)) return false;
        OutGoingTaskDto that = (OutGoingTaskDto) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(taskName, that.taskName) && Objects.equals(taskDescription, that.taskDescription) && Objects.equals(taskPriority, that.taskPriority) && Objects.equals(taskStatus, that.taskStatus) && Objects.equals(taskDeadline, that.taskDeadline) && Objects.equals(taskPerformer, that.taskPerformer) && Objects.equals(taskProject, that.taskProject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, taskName, taskDescription, taskPriority, taskStatus, taskDeadline, taskPerformer, taskProject);
    }
}
