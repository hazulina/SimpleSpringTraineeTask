package org.trainee.dto;

import java.sql.Timestamp;
import java.util.UUID;

public class IncomingProjectDto {
    private UUID projectId;
    private String projectName;
    private Timestamp projectStartDate;
    private Timestamp projectDeadlineDate;

    public UUID getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public Timestamp getProjectStartDate() {
        return projectStartDate;
    }

    public Timestamp getProjectDeadlineDate() {
        return projectDeadlineDate;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectStartDate(Timestamp projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public void setProjectDeadlineDate(Timestamp projectDeadlineDate) {
        this.projectDeadlineDate = projectDeadlineDate;
    }

    @Override
    public String toString() {
        return "IncomingProjectDto{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectStartDate=" + projectStartDate +
                ", projectDeadlineDate=" + projectDeadlineDate +
                '}';
    }
}
