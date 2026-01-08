package com.raul.paylocity.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class Waits {
    private Waits() {}

    public static WebElement visible(WebDriverWait wait, By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static WebElement clickable(WebDriverWait wait, By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static boolean gone(WebDriverWait wait, By by) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void domReady(WebDriver driver, Duration timeout) {
        new WebDriverWait(driver, timeout).until(d ->
                ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete")
        );
    }
}
