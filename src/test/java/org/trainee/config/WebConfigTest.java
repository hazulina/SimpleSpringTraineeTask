package org.trainee.config;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;

class WebConfigTest {

    @Test
    void testConfigureDefaultServletHandling() {
        WebConfig webConfig = new WebConfig();
        DefaultServletHandlerConfigurer configurer = Mockito.mock(DefaultServletHandlerConfigurer.class);

        webConfig.configureDefaultServletHandling(configurer);

        Mockito.verify(configurer).enable();
    }
}
