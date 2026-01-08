package com.raul.paylocity.core;
import com.raul.paylocity.config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverFactory {
    private DriverFactory() {}

    public static WebDriver create() {
        String browser = Config.get("browser").toLowerCase();
        boolean headless = Config.getBool("headless");

        if (!browser.equals("chrome")) {
            throw new IllegalArgumentException("Only chrome is configured in this skeleton. Got: " + browser);
        }

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        if (headless) options.addArguments("--headless=new");
        options.addArguments("--start-maximized");

        return new ChromeDriver(options);
    }
}
