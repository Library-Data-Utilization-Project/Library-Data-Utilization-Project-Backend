package com.contest.rest.util;
 
import java.time.Duration;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import jakarta.annotation.PostConstruct;
 
@Component
public class WebDriverUtil {

    private static String WEB_DRIVER_PATH; // WebDriver 경로

    @Value("${driver.chrome.driver_path}")
    private String webDriverPath;

    @PostConstruct
    private void init() {
        if (webDriverPath == null || webDriverPath.isEmpty()) {
            throw new IllegalStateException("WebDriver path must be configured.");
        }
        WEB_DRIVER_PATH = webDriverPath;
        System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
    }

    public static WebDriver getChromeDriver() {
        if (ObjectUtils.isEmpty(System.getProperty("webdriver.chrome.driver"))) {
            if (WEB_DRIVER_PATH == null) {
                throw new IllegalStateException("WebDriver path is not set.");
            }
            System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
        }

        // WebDriver 옵션 설정
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--lang=ko");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        return driver;
    }

    public static void quit(WebDriver driver) {
        if (!ObjectUtils.isEmpty(driver)) {
            driver.quit();
        }
    }

    public static void close(WebDriver driver) {
        if (!ObjectUtils.isEmpty(driver)) {
            driver.close();
        }
    }
}
