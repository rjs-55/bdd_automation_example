package com.rts55.steps;

import com.rts55.ProjectDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class Hooks {

    @Autowired
    private ProjectDriver projectDriver;

    @Value("${baseurl}")
    private String baseUrl;

    @Before
    public void startUp() {
        projectDriver.getWebDriver().get(baseUrl);
        projectDriver.getWebDriver().manage().window().maximize();

    }

    @After
    public void embedScreenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                scenario.write("Current page title is: " + projectDriver.getWebDriver().getTitle());
                byte[] screenshot = ((TakesScreenshot) projectDriver.getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "img/png");
            } catch (WebDriverException somePlatformsDontSupportScreenShots) {
                log.error(somePlatformsDontSupportScreenShots.getMessage());
            }
        }
        projectDriver.getWebDriver().manage().deleteAllCookies();
    }
}
