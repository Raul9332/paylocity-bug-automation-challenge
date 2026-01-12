package com.raul.paylocity.tests.ui;

import com.raul.paylocity.core.BaseUiTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseUiTest {

    @Test
    public void testLoginErrorMessage() {
        loginPage.setUserName("TestUser866");
        loginPage.setPassword("sxz3400");
        loginPage.clickLoginButton();

        String actualMessage = loginPage.getErrorMessage();
        Assert.assertTrue(actualMessage.contains(
                "There were one or more problems that prevented you from logging in:"
        ));
    }

    @Test
    public void testPasswordIncorrect() {
        loginPage.setUserName("TestUser866");
        loginPage.setPassword("sxz3400");
        loginPage.clickLoginButton();

        String actualMessage = loginPage.getErrorMessagePassword();
        Assert.assertTrue(actualMessage.contains(
                "The specified username or password is incorrect."
        ));
    }
    @Test
    public void testMissingFieldsLogin(){
        loginPage.clickLoginButton();

        String actualMessage = loginPage.getErrorUserReq();
        Assert.assertTrue(actualMessage.contains(
                "The Username field is required."
        ));

        String actualMessage2 = loginPage.getErrorPassReq();
        Assert.assertTrue(actualMessage2.contains(
                "The Password field is required."
        ));

    }

}
