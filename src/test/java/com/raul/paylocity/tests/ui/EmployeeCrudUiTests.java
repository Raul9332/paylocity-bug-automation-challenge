package com.raul.paylocity.tests.ui;

import com.raul.paylocity.core.BaseUiTest;
import com.raul.paylocity.pages.DashboardPage;
import com.raul.paylocity.pages.EmployeeModal;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class EmployeeCrudUiTests extends BaseUiTest {

    @Test
    public void addEditDeleteEmployee_happyPath() {

        openLogin();

        loginPage.loginWithDefaultUser();

        DashboardPage dashboard = new DashboardPage(driver, wait);
        Assert.assertTrue(dashboard.isLoaded(), "Dashboard did not load. Check locators or login.");

        String first = "RaulUI3";
        String last  = "DeLeonUI3";

        // ADD
        EmployeeModal modal = dashboard.clickAddEmployee();
        modal.setFirstName(first).setLastName(last).setDependents(2).clickAdd();

        dashboard.waitForEmployeeRow(first, last, Duration.ofSeconds(10));

        Assert.assertTrue(dashboard.hasEmployeeRow(first, last),
                "Expected employee row after Add. If not found, adjust table locators / wait for refresh.");


        // EDIT
        dashboard.clickEditFor(first, last);
        EmployeeModal editModal = new EmployeeModal(driver, wait);
        editModal.setDependents(3).clickUpdate();

        // DELETE
        dashboard.clickDeleteFor(first, last);

    }
}
