package com.raul.paylocity.pages;

import com.raul.paylocity.core.Waits;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class DashboardPage extends BasePage {

    private final By employeesTableRows = By.xpath("//table[@id='employeesTable']//tbody//tr");


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
    public void waitForEmployeeRow(String first, String last, Duration timeout) {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, timeout)
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> hasEmployeeRow(first, last));
    }


    public EmployeeModal clickAddEmployee() {
        Waits.clickable(wait, addEmployeeBtn).click();
        return new EmployeeModal(driver, wait);
    }

    public List<WebElement> rows() {
        return driver.findElements(tableRows);
    }

    public boolean hasEmployeeRow(String first, String last) {
        String expected = (first + " " + last).trim();

        // Retry loop for transient DOM refresh (stale)
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(employeesTableRows));

                List<WebElement> rows = driver.findElements(employeesTableRows);

                for (WebElement row : rows) {
                    String rowText = row.getText();
                    if (rowText != null && rowText.contains(expected)) {
                        return true;
                    }
                }
                return false;

            } catch (StaleElementReferenceException | NoSuchElementException e) {
                // DOM refreshed, retry quickly
                try { Thread.sleep(250); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
            }
        }
        return false;
    }
    private By employeeRowByName(String first, String last) {
        String full = (first + " " + last).trim();
        return By.xpath("//table[@id='employeesTable']//tbody//tr[.//*[contains(normalize-space(.),'" + full + "')]]");
    }

    private By editActionInRow(String first, String last) {
        String full = (first + " " + last).trim();

        return By.xpath(
                "//table[@id='employeesTable']//tbody//tr[.//*[contains(normalize-space(.),'" + full + "')]]" +
                        "//*[self::a or self::button]" +
                        "[contains(normalize-space(.),'Edit') or contains(@aria-label,'Edit') or contains(@title,'Edit')]"
        );
    }


    private By deleteActionInRow(String first, String last) {
        String full = (first + " " + last).trim();

        return By.xpath(
                "//table[@id='employeesTable']//tbody//tr[.//*[contains(normalize-space(.),'" + full + "')]]" +
                        "//*[self::a or self::button]" +
                        "[contains(normalize-space(.),'Delete') or contains(@aria-label,'Delete') or contains(@title,'Delete')]"
        );
    }
    //public void clickEdit(String first, String last){
       // By edit = By.xpath("//tbody//tr//td[text()='"+ first +"']//following::i[1]");
      //  click(edit);
   // }


    public void clickEditFor(String first, String last) {
        By edit = By.xpath("//tbody//tr//td[text()='"+ first +"']//following::i[1]");
          click(edit);
    }

    public void clickDeleteFor(String first, String last) {
        clickStable(deleteActionInRow(first, last));
    }

    private void clickWithRetry(By locator) {
        for (int i = 0; i < 3; i++) {
            try {
                wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(locator)).click();
                return;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                sleep(250);
            }
        }
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }


    private void clickStable(By locator) {
        for (int i = 0; i < 3; i++) {
            try {
                var el = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(locator));
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);

                wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(locator)).click();
                return;

            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.ElementClickInterceptedException e) {
                // JS click fallback when overlays or animations block the click
                try {
                    var el = driver.findElement(locator);
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
                    return;
                } catch (Exception ignore) {
                    sleep(250);
                }
            }
        }
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(locator)).click();
    }
    private WebElement findRow(String firstName, String lastName) {
        return rows().stream()
                .filter(r -> r.getText().contains(firstName) && r.getText().contains(lastName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Row not found for: " + firstName + " " + lastName));
    }

    public void waitUntilEmployeeRowIsGone(String first, String last, Duration timeout) {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, timeout)
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class)
                .until(d -> !hasEmployeeRow(first, last));
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
