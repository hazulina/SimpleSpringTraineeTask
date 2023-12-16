package org.trainee.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * {@link PerformerEntity} represents database table.
 * It is entity class.
 */
@Entity
@Table(name = "performers")
public class PerformerEntity {
    @Id
    @Column(name = "performer_id")
    private UUID performerId;
    @Column(name = "performer_name")
    private String name;
    @Column(name = "performer_email")
    private String email;
    @Column(name = "performer_role")
    private String role;
    @ManyToMany(mappedBy = "projectPerformers", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ProjectEntity> performerProjects;
    @OneToMany(mappedBy = "taskPerformer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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

    public PerformerEntity(UUID uuid) {
        this.performerId = uuid;
    }

    public PerformerEntity() {
    }

    public PerformerEntity(UUID performerId, String name, String email, String role) {
        this.performerId = performerId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString() {
        return "PerformerEntity{" +
                "performerId=" + performerId +
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
        if (!(o instanceof PerformerEntity)) return false;
        PerformerEntity that = (PerformerEntity) o;
        return Objects.equals(performerId, that.performerId) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(role, that.role) && Objects.equals(performerProjects, that.performerProjects) && Objects.equals(performerTasks, that.performerTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(performerId, name, email, role, performerProjects, performerTasks);
    }
}
