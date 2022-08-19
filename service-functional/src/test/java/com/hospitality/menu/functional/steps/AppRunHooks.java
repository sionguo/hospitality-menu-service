package com.hospitality.menu.functional.steps;

import com.hospitality.menu.functional.AppRunner;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class AppRunHooks {

  @Autowired private AppRunner appRunner;

  @Before
  public void startServiceInTest() {
    appRunner.startApp();
  }
}
