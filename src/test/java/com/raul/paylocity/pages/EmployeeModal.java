package com.raul.paylocity.pages;

import com.raul.paylocity.core.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeeModal {
    private final WebDriver driver;
    private final WebDriverWait wait;

    //TODO
    private final By modalRoot = By.xpath("TODO");
    private final By firstName = By.xpath("TODO");
    private final By lastName  = By.xpath("TODO");
    private final By dependents= By.xpath("TODO");

    private final By addBtn    = By.xpath("TODO");
    private final By updateBtn = By.xpath("TODO");
    private final By cancelBtn = By.xpath("TODO");


    private final By validationError = By.xpath("TODO");

    public EmployeeModal(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        Waits.visible(wait, modalRoot);
    }

    public EmployeeModal setFirstName(String v) {
        Waits.visible(wait, firstName).clear();
        driver.findElement(firstName).sendKeys(v);
        return this;
    }

    public EmployeeModal setLastName(String v) {
        Waits.visible(wait, lastName).clear();
        driver.findElement(lastName).sendKeys(v);
        return this;
    }

    public EmployeeModal setDependents(int v) {
        Waits.visible(wait, dependents).clear();
        driver.findElement(dependents).sendKeys(String.valueOf(v));
        return this;
    }

    public void clickAdd() {
        Waits.clickable(wait, addBtn).click();

    }

    public void clickUpdate() {
        Waits.clickable(wait, updateBtn).click();

    }

    public void clickCancel() {
        Waits.clickable(wait, cancelBtn).click();

    }

    public boolean hasAnyValidationError() {
        try {
            return Waits.visible(wait, validationError).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

