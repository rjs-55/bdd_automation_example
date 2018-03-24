package com.rts55;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ProjectDriver {

    private static WebDriver webDriver;

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                webDriver.quit();
            }
        });
    }

    @Value("${browser}")
    private String browser;

    @Value("${geckodriver}")
    private String geckoDriver;

    @PostConstruct
    public void setUpWebDriver() throws IllegalStateException {
        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", geckoDriver);
            webDriver = new FirefoxDriver();
        } else {
            String errorMessage = browser + " is not a recognised option.";
            throw new IllegalStateException(errorMessage);
        }

    }

    public final WebDriver getWebDriver() {
        return webDriver;
    }
}
