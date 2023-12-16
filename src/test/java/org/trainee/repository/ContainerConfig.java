package org.trainee.repository;

import org.testcontainers.containers.PostgreSQLContainer;


class ContainerConfig {

     static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:latest")
            .withInitScript("testscript.sql");

}
