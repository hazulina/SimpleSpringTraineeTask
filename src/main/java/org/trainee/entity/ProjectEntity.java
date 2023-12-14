package org.trainee.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * {@link ProjectEntity} represents database table.
 * It is entity class.
 */
@Entity
@Table(name = "projects")
public class ProjectEntity {
    @Id
    @Column(name = "project_id")
    private UUID projectId;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "project_start_date")
    private Timestamp projectStartDate;
    @Column(name = "project_deadline_date")
    private Timestamp projectDeadlineDate;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "project_performers",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "performer_id"))
    private List<PerformerEntity> projectPerformers;
    @OneToMany(mappedBy = "taskProject")
    private List<TaskEntity> projectTasks;

    public ProjectEntity() {

    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Timestamp getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Timestamp projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Timestamp getProjectDeadlineDate() {
        return projectDeadlineDate;
    }

    public void setProjectDeadlineDate(Timestamp projectDeadlineDate) {
        this.projectDeadlineDate = projectDeadlineDate;
    }

    public List<PerformerEntity> getProjectPerformers() {
        return projectPerformers;
    }

    public void setProjectPerformers(List<PerformerEntity> projectPerformers) {
        this.projectPerformers = projectPerformers;
    }

    public List<TaskEntity> getProjectTasks() {
        return projectTasks;
    }

    public void setProjectTasks(List<TaskEntity> projectTasks) {
        this.projectTasks = projectTasks;
    }

    public ProjectEntity(UUID projectId, String projectName, Timestamp projectStartDate, Timestamp projectDeadlineDate) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectStartDate = projectStartDate;
        this.projectDeadlineDate = projectDeadlineDate;
    }

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectStartDate=" + projectStartDate +
                ", projectDeadlineDate=" + projectDeadlineDate +
                ", projectPerformers=" + projectPerformers +
                ", projectTasks=" + projectTasks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectEntity)) return false;
        ProjectEntity entity = (ProjectEntity) o;
        return Objects.equals(projectId, entity.projectId) && Objects.equals(projectName, entity.projectName) && Objects.equals(projectStartDate, entity.projectStartDate) && Objects.equals(projectDeadlineDate, entity.projectDeadlineDate) && Objects.equals(projectPerformers, entity.projectPerformers) && Objects.equals(projectTasks, entity.projectTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, projectStartDate, projectDeadlineDate, projectPerformers, projectTasks);
    }
}
