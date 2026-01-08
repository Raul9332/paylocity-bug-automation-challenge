package com.raul.paylocity.pages;

import com.raul.paylocity.config.Config;
import com.raul.paylocity.core.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // TODO
    private final By usernameInput = By.cssSelector("input[name='username'], input#username, input[type='text']");
    private final By passwordInput = By.cssSelector("input[name='password'], input#password, input[type='password']");
    private final By loginButton   = By.cssSelector("button[type='submit'], button:has-text('Login')");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public LoginPage loginWithDefaultUser() {
        return login(Config.get("ui.username"), Config.get("ui.password"));
    }

    public LoginPage login(String username, String password) {
        Waits.visible(wait, usernameInput).clear();
        driver.findElement(usernameInput).sendKeys(username);

        Waits.visible(wait, passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);

        Waits.clickable(wait, loginButton).click();
        return this;
    }
}

