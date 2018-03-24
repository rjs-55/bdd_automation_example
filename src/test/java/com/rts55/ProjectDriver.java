package com.rts55;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
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

    @Value("${chromedriver}")
    private String chromeDriver;

    @PostConstruct
    public void setUpWebDriver() throws IllegalStateException {
        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", geckoDriver);
                webDriver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", chromeDriver);
                webDriver = new ChromeDriver();
                break;
            default:
                String errorMessage = String.format(
                        "%s is not a recognised option.", browser);
                throw new IllegalStateException(errorMessage);
        }
        log.info(String.format("Browser is set to %s", browser));

    }

    public final WebDriver getWebDriver() {
        return webDriver;
    }
}
