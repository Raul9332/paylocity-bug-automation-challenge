package com.raul.paylocity.core;

import com.raul.paylocity.config.Config;
import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public abstract class BaseUiTest {
    protected WebDriver driver;
    protected org.openqa.selenium.support.ui.WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.create();
        wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        try {
            if (!result.isSuccess() && driver != null) takeScreenshot(result.getName());
        } catch (Exception ignored) {
        } finally {
            if (driver != null) driver.quit();
        }
    }

    protected void openLogin() {
        driver.get(Config.get("ui.baseUrl"));
        Waits.domReady(driver, Duration.ofSeconds(10));
    }

    private void takeScreenshot(String testName) throws Exception {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path outDir = Path.of("target", "screenshots");
        Files.createDirectories(outDir);
        Files.copy(src.toPath(), outDir.resolve(testName + ".png"));
    }
}
