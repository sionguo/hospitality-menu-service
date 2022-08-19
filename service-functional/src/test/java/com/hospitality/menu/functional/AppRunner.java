package com.hospitality.menu.functional;

import com.hospitality.menu.Application;
import javax.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class AppRunner {

  private ConfigurableApplicationContext application;

  public void startApp() {
    application = SpringApplication.run(Application.class);
  }

  @PreDestroy
  private void shutdown() {
    if (application != null) {
      this.application.close();
    }
  }

  public boolean isAppRunning() {
    return application != null && application.isRunning();
  }
}
