package org.trainee.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.trainee.config.WebConfig;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WebConfigTest {

    @Test
    public void testConfigureDefaultServletHandling() {
        WebConfig webConfig = new WebConfig();
        DefaultServletHandlerConfigurer configurer = mock(DefaultServletHandlerConfigurer.class);

        webConfig.configureDefaultServletHandling(configurer);

        verify(configurer).enable();
    }
}
