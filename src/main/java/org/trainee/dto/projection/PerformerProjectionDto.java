package org.trainee.dto.projection;

import java.util.Objects;

public class PerformerProjectionDto {
    private String performerId;
    private String name;
    private String email;
    private String role;

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

    public PerformerProjectionDto(String performerId, String name, String email, String role) {
        this.performerId = performerId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString() {
        return "PerformerProjectionDto{" +
                "performerId='" + performerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PerformerProjectionDto)) return false;
        PerformerProjectionDto that = (PerformerProjectionDto) o;
        return Objects.equals(performerId, that.performerId) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(performerId, name, email, role);
    }
}
