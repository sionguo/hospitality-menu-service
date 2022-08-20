package com.hospitality.menu;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

class ApplicationTest {
  @Test
  void shouldBeAnnotatedWithExpectedSpringBootApplicationAnnotation() {
    assertThat(Application.class).hasAnnotation(SpringBootApplication.class);
  }

  @Test
  void main_shouldCallRunSpringApplicationWithAnEmptyListOfArguments() {
    try (MockedStatic<SpringApplication> springApplication =
        Mockito.mockStatic(SpringApplication.class)) {
      Application.main(new String[] {});
      springApplication.verify(() -> SpringApplication.run(Application.class, new String[] {}));
    }
  }

  @Test
  void main_shouldCallRunSpringApplicationWithPassedArguments() {
    try (MockedStatic<SpringApplication> springApplication =
        Mockito.mockStatic(SpringApplication.class)) {
      Application.main(new String[] {"arg1", "arg2"});
      springApplication.verify(
          () -> SpringApplication.run(Application.class, new String[] {"arg1", "arg2"}));
    }
  }
}
