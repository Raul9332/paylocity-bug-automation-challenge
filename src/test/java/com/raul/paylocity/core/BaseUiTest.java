package com.raul.paylocity.core;

import com.raul.paylocity.config.Config;
import com.raul.paylocity.pages.LoginPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class BaseUiTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected LoginPage loginPage;

    protected final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.create();
        wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);

        openLogin();
        loginPage = new LoginPage(driver, wait);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (driver == null) return;

        try {
            if (result != null && !result.isSuccess()) {
                takeScreenshot(result.getName());
            }
        } catch (Exception e) {
            System.err.println("⚠️ Screenshot failed: " + e.getMessage());
        } finally {
            try {
                driver.quit();
            } catch (WebDriverException e) {
                System.err.println("⚠️ Driver quit failed: " + e.getMessage());
            }
        }
    }

    protected void openLogin() {
        String baseUrl = Config.get("ui.baseUrl");
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException("Config ui.baseUrl is missing or empty. Check your config file/env.");
        }

        driver.get(baseUrl);
        Waits.domReady(driver, DEFAULT_TIMEOUT);
    }

    private void takeScreenshot(String testName) throws Exception {
        if (!(driver instanceof TakesScreenshot)) return;

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        Path outDir = Path.of("target", "screenshots");
        Files.createDirectories(outDir);

        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path dest = outDir.resolve(testName + "_" + ts + ".png");

        Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("📸 Screenshot saved: " + dest.toAbsolutePath());
    }


}
