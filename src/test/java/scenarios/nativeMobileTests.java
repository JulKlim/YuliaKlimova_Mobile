package scenarios;

import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.testng.annotations.Test;
import pageObjects.nativePageObject.BudgetActivityPage;
import pageObjects.nativePageObject.LoginPage;
import pageObjects.nativePageObject.RegistrationPage;
import pageObjects.nativePageObject.RegistrationPage.ElementType;
import setup.BaseTest;
import testData.TestDataProvider;

public class nativeMobileTests extends BaseTest {
    SoftAssertions softAssertions = new SoftAssertions();

    @Test(groups = {"native"}, description = "Check if user is on registration page",
          dataProvider = "registrationData", dataProviderClass = TestDataProvider.class)
    public void performRegistrationAndLoginToEPAMTestApp(JSONObject testData)
        throws IllegalAccessException, NoSuchFieldException,
        InstantiationException {
        //Create instances of page classes
        LoginPage loginPage = new LoginPage(getDriver());
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        BudgetActivityPage budgetActivityPage = new BudgetActivityPage(getDriver());

        //Check if user can proceed to registration page from login page
        softAssertions.assertThat(loginPage.isElementDisplayed(LoginPage.ElementType.REGISTER_NEW_ACCOUNT_BUTTON))
                      .as("Button for registration of a new account is displayed").isTrue();
        loginPage.clickRegisterNewAccountBtn();
        System.out.println("'Register new account' button is displayed and can be clicked");

        //Check if user is on registration page
        softAssertions.assertThat(registrationPage.isElementDisplayed(ElementType.REGISTRATION_FORM))
                      .as("Registration form is displayed").isTrue();
        System.out.println("User is on registration page");

        //Check all fields for registration are displayed
        softAssertions.assertThat(registrationPage.isElementDisplayed(ElementType.EMAIL_FIELD))
                      .as("Email field is displayed").isTrue();
        System.out.println("Email field is displayed");
        softAssertions.assertThat(registrationPage.isElementDisplayed(ElementType.USERNAME_FIELD))
                      .as("Username field is displayed").isTrue();
        System.out.println("Username field is displayed");
        softAssertions.assertThat(registrationPage.isElementDisplayed(ElementType.PASSWORD_FIELD))
                      .as("Password field is displayed").isTrue();
        System.out.println("Password field is displayed");
        softAssertions.assertThat(registrationPage.isElementDisplayed(ElementType.CONFIRM_PASSWORD_FIELD))
                      .as("Field for password confirmation is displayed").isTrue();
        System.out.println("Field for password confirmation is displayed");
        softAssertions.assertThat(registrationPage.isElementDisplayed(ElementType.AGREE_CHECKBOX))
                      .as("Agreement checkbox is displayed").isTrue();
        System.out.println("Agreement checkbox is displayed");
        //Fill the form with registration details
        registrationPage.enterRegistrationDetails(testData.getString("email"), testData.getString("username"),
            testData.getString("password"));
        System.out.println("Registration form is filled in");

        //Check registration button is displayed and confirm registration of a new account
        softAssertions.assertThat(registrationPage.isElementDisplayed(ElementType.REGISTER_NEW_ACCOUNT_BUTTON))
                      .as("Button 'Register new account' is displayed").isTrue();
        System.out.println("Button 'Register new account' is displayed");
        registrationPage.registerNewAccount();
        System.out.println("Button 'Register new account' is clicked on");

        //Check all login fields are displayed
        softAssertions.assertThat(loginPage.isElementDisplayed(LoginPage.ElementType.LOGIN_EMAIL_FIELD))
                      .as("Login field is displayed").isTrue();
        System.out.println("Login field is displayed and can be filled in");
        softAssertions.assertThat(loginPage.isElementDisplayed(LoginPage.ElementType.LOGIN_PASSWORD_FIELD))
                      .as("Login password field is displayed").isTrue();
        System.out.println("Login password field is displayed");

        //Check "Sign in" button is displayed and perform login
        softAssertions.assertThat(loginPage.isElementDisplayed(LoginPage.ElementType.SIGN_IN_BUTTON))
                      .as("Login password field is displayed").isTrue();
        System.out.println("'Sign in' button is displayed");
        loginPage.performLogin(testData.getString("email"), testData.getString("password"));
        System.out.println("Login is performed");

        //Check user is on BudgetActivity page
        softAssertions.assertThat(budgetActivityPage.getBudgetActivityPageHeader())
                      .as("BudgetActivity page is opened").contains("Budget");
        System.out.println("User is on BudgetActivity page");
        System.out.println("BudgetActivity page is opened");

        softAssertions.assertAll();
    }
}
