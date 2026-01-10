package com.raul.paylocity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver, WebDriverWait wait) {
        if (driver == null) throw new IllegalArgumentException("driver cannot be null");
        if (wait == null) throw new IllegalArgumentException("wait cannot be null");
        this.driver = driver;
        this.wait = wait;
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected void click(By locator) {
        find(locator).click();
    }

    protected void set(By locator, String text) {
        WebElement el = find(locator);
        el.clear();
        if (text != null) {
            el.sendKeys(text);
        }
    }
}
