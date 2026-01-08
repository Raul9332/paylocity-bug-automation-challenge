package com.raul.paylocity.pages;

import com.raul.paylocity.core.Waits;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DashboardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // TODO
    private final By dashboardHeader = By.xpath("TODO");
    private final By addEmployeeBtn  = By.xpath("TODO");


    private final By tableRows = By.xpath("TODO");

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isLoaded() {
        try {
            Waits.visible(wait, dashboardHeader);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public EmployeeModal clickAddEmployee() {
        Waits.clickable(wait, addEmployeeBtn).click();
        return new EmployeeModal(driver, wait);
    }

    public List<WebElement> rows() {
        return driver.findElements(tableRows);
    }

    public boolean hasEmployeeRow(String firstName, String lastName) {
        return rows().stream().anyMatch(r -> r.getText().contains(firstName) && r.getText().contains(lastName));
    }

    // TODO:
    public void clickEditFor(String firstName, String lastName) {
        WebElement row = rows().stream()
                .filter(r -> r.getText().contains(firstName) && r.getText().contains(lastName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Row not found for: " + firstName + " " + lastName));


        row.findElement(By.cssSelector("button:has-text('Edit'), a:has-text('Edit')")).click();
    }

    public void clickDeleteFor(String firstName, String lastName) {
        WebElement row = rows().stream()
                .filter(r -> r.getText().contains(firstName) && r.getText().contains(lastName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Row not found for: " + firstName + " " + lastName));

        row.findElement(By.cssSelector("button:has-text('Delete'), a:has-text('Delete')")).click();
    }
}

