package com.raul.paylocity.tests.ui;

import com.raul.paylocity.core.BaseUiTest;
import com.raul.paylocity.pages.DashboardPage;
import com.raul.paylocity.pages.EmployeeModal;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.UUID;

public class EmployeeUpdateUiTests extends BaseUiTest {

    @Test
    public void updateEmployee_dependents_happyPath() {

        // Login
        openLogin();
        loginPage.loginWithDefaultUser();

        DashboardPage dashboard = new DashboardPage(driver, wait);
        Assert.assertTrue(dashboard.isLoaded(), "Dashboard did not load. Check locators or login.");

        String first = "UpdateUI_" + UUID.randomUUID().toString().substring(0, 6);
        String last  = "DeLeon";

        // Precondition: create employee to update
        EmployeeModal modal = dashboard.clickAddEmployee();
        modal.setFirstName(first)
                .setLastName(last)
                .setDependents(1)
                .clickAdd();

        dashboard.waitForEmployeeRow(first, last, Duration.ofSeconds(10));
        Assert.assertTrue(dashboard.hasEmployeeRow(first, last), "Precondition failed: employee not created.");

        // Act: update dependents
        dashboard.clickEditFor(first, last);
        EmployeeModal editModal = new EmployeeModal(driver, wait);
        editModal.setDependents(3).clickUpdate();

        // Assert: at least verify row still exists after update
        dashboard.waitForEmployeeRow(first, last, Duration.ofSeconds(10));
        Assert.assertTrue(
                dashboard.hasEmployeeRow(first, last),
                "Expected employee row after Update. If not found, adjust locators/waits."
        );
    }
}
