package com.hospitality.menu;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApplicationTest {
    @Test
    public void shouldBeAnnotatedWithExpectedSpringBootApplicationAnnotation() {
        assertThat(Application.class).hasAnnotation(SpringBootApplication.class);
    }

    @Test
    public void main_shouldCallRunSpringApplicationWithAnEmptyListOfArguments() {
        try (MockedStatic<SpringApplication> springApplication = Mockito.mockStatic(SpringApplication.class)) {
            Application.main(new String[]{});
            springApplication.verify(() -> SpringApplication.run(Application.class, new String[]{}));
        }
    }

    @Test
    public void main_shouldCallRunSpringApplicationWithPassedArguments() {
        try (MockedStatic<SpringApplication> springApplication = Mockito.mockStatic(SpringApplication.class)) {
            Application.main(new String[]{"arg1", "arg2"});
            springApplication.verify(() -> SpringApplication.run(Application.class, new String[]{"arg1", "arg2"}));
        }
    }
}
