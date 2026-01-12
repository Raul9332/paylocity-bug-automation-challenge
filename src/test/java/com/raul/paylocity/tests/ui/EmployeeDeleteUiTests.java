package com.raul.paylocity.tests.ui;

import com.raul.paylocity.core.BaseUiTest;
import com.raul.paylocity.pages.DashboardPage;
import com.raul.paylocity.pages.EmployeeModal;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.UUID;

public class EmployeeDeleteUiTests extends BaseUiTest {

    @Test
    public void deleteEmployee_happyPath() {

        // Login
        openLogin();
        loginPage.loginWithDefaultUser();

        DashboardPage dashboard = new DashboardPage(driver, wait);
        Assert.assertTrue(dashboard.isLoaded(), "Dashboard did not load. Check locators or login.");

        String first = "DeleteUI_" + UUID.randomUUID().toString().substring(0, 6);
        String last  = "DeLeon";

        // Precondition: create employee to delete
        EmployeeModal modal = dashboard.clickAddEmployee();
        modal.setFirstName(first)
                .setLastName(last)
                .setDependents(0)
                .clickAdd();

        dashboard.waitForEmployeeRow(first, last, Duration.ofSeconds(10));
        Assert.assertTrue(dashboard.hasEmployeeRow(first, last), "Precondition failed: employee not created.");

        // Act: delete
        dashboard.clickDeleteFor(first, last);

        // Assert: verify it disappears
        long end = System.currentTimeMillis() + Duration.ofSeconds(10).toMillis();
        boolean stillPresent = true;

        while (System.currentTimeMillis() < end) {
            stillPresent = dashboard.hasEmployeeRow(first, last);
            if (!stillPresent) break;
            try { Thread.sleep(250); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }

        Assert.assertFalse(
                stillPresent,
                "Expected employee row to be removed after Delete. If still present, adjust delete flow/locator/wait."
        );
    }
}

