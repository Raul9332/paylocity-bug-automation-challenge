package com.raul.paylocity.tests.ui;

import com.raul.paylocity.core.BaseUiTest;
import com.raul.paylocity.pages.DashboardPage;
import com.raul.paylocity.pages.EmployeeModal;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.UUID;

public class EmployeeCreateUiTests extends BaseUiTest {

    @Test
    public void createEmployee_happyPath() {

        // Login
        openLogin();
        loginPage.loginWithDefaultUser();

        DashboardPage dashboard = new DashboardPage(driver, wait);
        Assert.assertTrue(dashboard.isLoaded(), "Dashboard did not load. Check locators or login.");

        String first = "CreateUI_" + UUID.randomUUID().toString().substring(0, 6);
        String last  = "DeLeon";

        // Add
        EmployeeModal modal = dashboard.clickAddEmployee();
        modal.setFirstName(first)
                .setLastName(last)
                .setDependents(2)
                .clickAdd();

        dashboard.waitForEmployeeRow(first, last, Duration.ofSeconds(10));

        // Assert
        Assert.assertTrue(
                dashboard.hasEmployeeRow(first, last),
                "Expected employee row after Create. If not found, adjust table locators / wait for refresh."
        );
    }
}
