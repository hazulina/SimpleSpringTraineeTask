package org.trainee.dto.projection;

import java.util.Objects;

public class ProjectProjectionDto {
    private String projectId;
    private String projectName;
    private String projectStartDate;
    private String projectDeadlineDate;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public String getProjectDeadlineDate() {
        return projectDeadlineDate;
    }

    public void setProjectDeadlineDate(String projectDeadlineDate) {
        this.projectDeadlineDate = projectDeadlineDate;
    }

    public ProjectProjectionDto(String projectId, String projectName, String projectStartDate, String projectDeadlineDate) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectStartDate = projectStartDate;
        this.projectDeadlineDate = projectDeadlineDate;
    }

    @Override
    public String toString() {
        return "ProjectProjectionDto{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectStartDate='" + projectStartDate + '\'' +
                ", projectDeadlineDate='" + projectDeadlineDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectProjectionDto)) return false;
        ProjectProjectionDto that = (ProjectProjectionDto) o;
        return Objects.equals(projectId, that.projectId) && Objects.equals(projectName, that.projectName) && Objects.equals(projectStartDate, that.projectStartDate) && Objects.equals(projectDeadlineDate, that.projectDeadlineDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, projectStartDate, projectDeadlineDate);
    }
}
