package com.raul.paylocity.pages;

import com.raul.paylocity.config.Config;
import com.raul.paylocity.core.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("Username");
    private final By passwordInput = By.id("Password");
    private final By loginButton   = By.xpath("//button[@type='submit']");
    private final By errorMessage         = By.xpath("//div[@class='text-danger validation-summary-errors']/span");
    private final By errorPasswordMessage = By.xpath("//div[@class='text-danger validation-summary-errors']//li");
    private final By userReqErrorMes      = By.xpath("//div[@class='text-danger validation-summary-errors']//li[1]");
    private final By passReqErrorMes      = By.xpath("//div[@class='text-danger validation-summary-errors']//li[2]");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public LoginPage loginWithDefaultUser() {
        return login(Config.get("ui.username"), Config.get("ui.password"));
    }

    public LoginPage loginWithInvalidPassword() {

        String invalidPassword = Config.get("ui.invalidPassword");
        if (invalidPassword == null || invalidPassword.isBlank()) {
            invalidPassword = "InvalidPassword123!";
        }
        return login(Config.get("ui.username"), invalidPassword);
    }

    public LoginPage login(String username, String password) {
        Waits.visible(wait, usernameInput).clear();
        set(usernameInput, username);

        Waits.visible(wait, passwordInput).clear();
        set(passwordInput, password);

        Waits.clickable(wait, loginButton).click();
        return this;
    }

    public void setUserName(String username){
        Waits.visible(wait, usernameInput);
        set(usernameInput, username);
    }

    public void setPassword(String password){
        Waits.visible(wait, passwordInput);
        set(passwordInput, password);
    }

    public DashboardPage clickLoginButton() {
        Waits.clickable(wait, loginButton).click();
        return new DashboardPage(this.driver, this.wait);
    }

    public String getValidationSummaryText() {
        return find(errorPasswordMessage).getText();
    }

    public String getFullValidationText() {
        return wait.until(d -> driver.findElement(By.cssSelector("div.validation-summary-errors"))).getText();
    }




    public String getErrorMessage(){
        return Waits.visible(wait, errorMessage).getText();
    }

    public String getErrorMessagePassword(){
        return Waits.visible(wait, errorPasswordMessage).getText();
    }

    public String getErrorUserReq(){
        return Waits.visible(wait, userReqErrorMes).getText();
    }

    public String getErrorPassReq(){
        return Waits.visible(wait, passReqErrorMes).getText();
    }
}
