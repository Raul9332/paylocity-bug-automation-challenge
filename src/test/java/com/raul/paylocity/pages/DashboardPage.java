package com.raul.paylocity.pages;

import com.raul.paylocity.core.Waits;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private final By dashboardHeader = By.xpath("//table[@id='employeesTable']/thead");
    private final By addEmployeeBtn  = By.id("add");
    private final By tableRows = By.xpath("//table[@id='employeesTable']//tbody//tr");


    public boolean isLoaded() {
        try {
            Waits.visible(wait, dashboardHeader);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    public void waitForEmployeeRow(String firstName, String lastName, Duration timeout) {
        WebDriverWait w = new WebDriverWait(driver, timeout);
        w.until(d -> hasEmployeeRow(firstName, lastName));
    }

    public EmployeeModal clickAddEmployee() {
        Waits.clickable(wait, addEmployeeBtn).click();
        return new EmployeeModal(driver, wait);
    }

    public List<WebElement> rows() {
        return driver.findElements(tableRows);
    }

    public boolean hasEmployeeRow(String firstName, String lastName) {
        return rows().stream()
                .anyMatch(r -> r.getText().contains(firstName) && r.getText().contains(lastName));
    }

    public void clickEditFor(String firstName, String lastName) {
        WebElement row = findRow(firstName, lastName);
        WebElement edit = findActionInRow(row, Action.EDIT);
        edit.click();
    }

    public void clickDeleteFor(String firstName, String lastName) {
        WebElement row = findRow(firstName, lastName);
        WebElement del = findActionInRow(row, Action.DELETE);
        del.click();
    }

    private WebElement findRow(String firstName, String lastName) {
        return rows().stream()
                .filter(r -> r.getText().contains(firstName) && r.getText().contains(lastName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Row not found for: " + firstName + " " + lastName));
    }

    private enum Action { EDIT, DELETE }


    private WebElement findActionInRow(WebElement row, Action action) {

        String label = (action == Action.EDIT) ? "Edit" : "Delete";

        List<By> candidates = List.of(

                By.xpath(".//button[contains(normalize-space(.),'" + label + "')]"),
                By.xpath(".//a[contains(normalize-space(.),'" + label + "')]"),


                (action == Action.EDIT)
                        ? By.xpath(".//i[contains(@class,'edit') or contains(@class,'fa-edit') or contains(@class,'pencil')]")
                        : By.xpath(".//i[contains(@class,'delete') or contains(@class,'fa-trash') or contains(@class,'trash') or contains(@class,'remove')]"),


                (action == Action.EDIT)
                        ? By.xpath(".//i[1]")
                        : By.xpath(".//i[2]")
        );

        for (By by : candidates) {
            try {
                WebElement el = row.findElement(by);

                return el;
            } catch (NoSuchElementException ignored) {
            }
        }

        throw new NoSuchElementException("Could not find " + action + " action in row: " + row.getText());
    }
}
