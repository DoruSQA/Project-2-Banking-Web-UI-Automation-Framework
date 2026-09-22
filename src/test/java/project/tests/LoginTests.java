package project.tests;

import org.testng.Assert;

import org.testng.annotations.Test;

import project.base.BaseTest;
import project.expected.DashboardExpected;
import static dorrusqa.z.Z.*;
import project.expected.LoginExpected;
import dorrusqa.testdata.TestDataProvider;
import dorrusqa.model.*;

public class LoginTests extends BaseTest {


    @Test(groups = {"Smoke", "Regression", "Authentication"},description = TC_LOGIN_001, 
    dataProvider = "userWithValidCredentials", dataProviderClass = TestDataProvider.class)
    public void loginWithValidCredentials(LoginUser userWIthValidCredentials) {
 
        // Authenticate with valid credentials
        loginPage.authentication(userWIthValidCredentials);

        // Verify successful authentication and dashboard details
        softAssertion.assertTrue(dashboardPage.isSideMenuDisplayed());
        softAssertion.assertEquals(dashboardPage.getUrl(), DashboardExpected.URL);
        softAssertion.assertEquals(dashboardPage.getPageHeading(), DashboardExpected.PAGE_HEADING);
        softAssertion.assertTrue(dashboardPage.isLogoutButtonDisplayed());
        softAssertion.assertAll();
    }


    @Test(groups = {"Regression", "Authentication"},description = TC_LOGIN_002, 
    dataProvider = "wrongPassword", dataProviderClass = TestDataProvider.class)
    public void loginWithValidUsernameAndWrongPassword(LoginUser userWIthWrongPassword) {  

        // Attempt authentication with an incorrect password
        loginPage.authentication(userWIthWrongPassword);

        // Verify login failure and expected error message
        softAssertion.assertEquals(loginPage.getFailedLoginErrorMessage(),LoginExpected.FAILED_MSG_WITH_WRONG_PASSWORD);
        softAssertion.assertEquals(loginPage.getUrl(),LoginExpected.URL);
        softAssertion.assertAll();
    }

    @Test(groups = {"Regression", "Authentication"},description = TC_LOGIN_003, 
    dataProvider = "wrongUsername", dataProviderClass = TestDataProvider.class)
    public void loginWithValidPasswordAndWrongUsername(LoginUser userWIthWrongUsername) {  

        // Attempt authentication with an incorrect username
        loginPage.authentication(userWIthWrongUsername);

        // Verify login failure and expected error message
        softAssertion.assertEquals(loginPage.getFailedLoginErrorMessage(),LoginExpected.FAILED_MSG_WITH_WRONG_USERNAME);
        softAssertion.assertEquals(loginPage.getUrl(), LoginExpected.URL);
        softAssertion.assertAll();
    }

    @Test(groups = {"Regression", "Authentication"},description = TC_LOGIN_004,
    dataProvider = "emptyUsername", dataProviderClass = TestDataProvider.class)
    public void loginWithEmptyUsernameAndValidPassword(LoginUser userWIthEmptyUsername) {

        // Attempt authentication with an empty username
        loginPage.authentication(userWIthEmptyUsername);

        // Verify login failure and expected error message
        softAssertion.assertEquals(loginPage.getFailedLoginErrorMessage(),LoginExpected.FAILED_MSG_WITH_EMPTY_USERNAME);
        softAssertion.assertEquals(loginPage.getUrl(),LoginExpected.URL);
        softAssertion.assertAll();
    }

    @Test(groups = {"Regression", "Authentication"},description = TC_LOGIN_005, 
    dataProvider = "emptyPassword", dataProviderClass = TestDataProvider.class)
    public void loginWithEmptyPasswordAndValidUsername(LoginUser userWIthEmptyPassword) {

        // Attempt authentication with an empty password
        loginPage.authentication(userWIthEmptyPassword);

        // Verify login failure and expected error message
        softAssertion.assertEquals(loginPage.getFailedLoginErrorMessage(),LoginExpected.FAILED_MSG_WITH_EMPTY_PASSWORD);
        softAssertion.assertEquals(loginPage.getUrl(),LoginExpected.URL);
        softAssertion.assertAll();
    }

    @Test(groups = {"Regression", "Authentication"},description = TC_LOGIN_006,
    dataProvider = "lockedOutUser", dataProviderClass = TestDataProvider.class)
    public void loginWithLockedOutUser(LoginUser lockedOutUser) {

        // Attempt authentication with a locked-out user
        loginPage.authentication(lockedOutUser);

        // Verify login failure with the expected locked-out account message and URL
        softAssertion.assertEquals(loginPage.getFailedLoginErrorMessage(),LoginExpected.FAILED_MSG_LOCKED_OUT_USER);
        softAssertion.assertEquals(loginPage.getUrl(),LoginExpected.URL);
        softAssertion.assertAll();
    }
    
    @Test(groups = {"Regression", "Authentication"},description = TC_LOGIN_007,
    dataProvider = "frozenUser", dataProviderClass = TestDataProvider.class)
    public void loginWithFrozenUser(LoginUser frozenUser) {

        // Attempt authentication with a frozen user account
        loginPage.authentication(frozenUser);

        // Verify the frozen account message and expected redirect URL
        softAssertion.assertTrue(dashboardPage.isFrozenAccountMsgDisplayed());
        softAssertion.assertEquals(loginPage.getUrl(),DashboardExpected.URL);
        softAssertion.assertAll();
    }

    @Test(groups = {"Smoke", "Regression", "Authentication"},description = TC_LOGIN_008, 
    dataProvider = "userWithValidCredentials", dataProviderClass = TestDataProvider.class)
    public void logoutSuccessfully(LoginUser userWIthValidCredentials) {

        // Authenticate with a standard user and log out
        loginPage.authentication(standardUser);
        dashboardPage.clickLogoutButton();
        
        // Verify logout redirects to the login page
        Assert.assertTrue(loginPage.isLoginButtonVisible());
        Assert.assertEquals(loginPage.getUrl(),LoginExpected.URL);
        
        // Attempt to access the dashboard directly after logout
        loginPage.navigateTo(DashboardExpected.URL);
        
        // Verify unauthenticated users remain on the login page
        softAssertion.assertTrue(loginPage.isLoginButtonVisible());
        softAssertion.assertEquals(loginPage.getUrl(),LoginExpected.URL);
        softAssertion.assertAll();
    }
}