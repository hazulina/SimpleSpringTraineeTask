package org.trainee.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


import javax.sql.DataSource;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppConfigTest {

    @Mock
    private DataSource dataSourceMock;

    @Mock
    private EntityManagerFactory entityManagerFactoryMock;

    private AppConfig appConfig;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        appConfig = new AppConfig();
    }

    @Test
    public void testJsonMessageConverter() {
        assertNotNull(appConfig.jsonMessageConverter());
    }

    @Test
    public void testDataSource() {
        DriverManagerDataSource dataSource = (DriverManagerDataSource) appConfig.dataSource();

        assertNotNull(dataSource);
    }

    @Test
    public void testEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = appConfig.entityManagerFactory(dataSourceMock);

        assertNotNull(entityManagerFactory);
        assertNotNull(entityManagerFactory.getDataSource());
        assertNotNull(entityManagerFactory.getJpaVendorAdapter());


    }

    @Test
    public void testTransactionManager() {
        when(entityManagerFactoryMock.createEntityManager()).thenReturn(mock(EntityManager.class));
        when(entityManagerFactoryMock.getProperties()).thenReturn(mock(Map.class));

        assertNotNull(appConfig.transactionManager(entityManagerFactoryMock));
    }

    @Test
    public void testExceptionTranslation() {
        assertNotNull(appConfig.exceptionTranslation());
    }
}
