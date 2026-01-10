package com.raul.paylocity.pages;

import com.raul.paylocity.core.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeeModal {

    private final WebDriver driver;
    private final WebDriverWait wait;


    private final By modalRoot = By.id("employeeModal");
    private final By firstName = By.cssSelector("#employeeModal input[name='firstName']");
    private final By lastName  = By.cssSelector("#employeeModal input[name='lastName']");


    private final By dependentsPrimary  = By.cssSelector("#employeeModal input[name='dependants']");
    private final By dependentsFallback = By.cssSelector("#employeeModal input[name='dependents']");


    private final By addBtnPrimary      = By.id("addEmployee");
    private final By addBtnFallback     = By.id("add"); // por si el id cambia
    private final By updateBtn          = By.id("updateEmployee");

    private final By cancelBtn = By.xpath("//div[@id='employeeModal']//button[contains(@class,'btn-secondary') or normalize-space(.)='Cancel']");


    private final By validationError = By.cssSelector("#employeeModal .text-danger, #employeeModal .invalid-feedback, #employeeModal .validation-summary-errors");

    public EmployeeModal(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        Waits.visible(wait, modalRoot);
    }

    public boolean isOpen() {
        return !driver.findElements(modalRoot).isEmpty();
    }

    public EmployeeModal setFirstName(String v) {
        WebElement el = Waits.visible(wait, firstName);
        el.clear();
        el.sendKeys(v);
        return this;
    }

    public EmployeeModal setLastName(String v) {
        WebElement el = Waits.visible(wait, lastName);
        el.clear();
        el.sendKeys(v);
        return this;
    }

    public EmployeeModal setDependents(int v) {
        WebElement el = getDependentsInput();
        el.clear();
        el.sendKeys(String.valueOf(v));
        return this;
    }

    public void clickAdd() {

        try {
            Waits.clickable(wait, addBtnPrimary).click();
        } catch (TimeoutException e) {
            Waits.clickable(wait, addBtnFallback).click();
        }
        waitUntilClosed();
    }

    public void clickUpdate() {
        Waits.clickable(wait, updateBtn).click();
        waitUntilClosed();
    }

    public void clickCancel() {
        Waits.clickable(wait, cancelBtn).click();
        waitUntilClosed();
    }

    public boolean hasAnyValidationError() {
        return !driver.findElements(validationError).isEmpty();
    }

    // -------- helpers --------

    private WebElement getDependentsInput() {

        if (!driver.findElements(dependentsPrimary).isEmpty()) {
            return Waits.visible(wait, dependentsPrimary);
        }
        return Waits.visible(wait, dependentsFallback);
    }

    private void waitUntilClosed() {

        try {
            Waits.invisible(wait, modalRoot);
        } catch (Exception ignored) {

            if (isOpen()) {
                System.out.println("⚠️ Modal still open after action. Possible validation errors: " + hasAnyValidationError());
            }
        }
    }
}
