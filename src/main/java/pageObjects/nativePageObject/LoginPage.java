package pageObjects.nativePageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    static WebElement registerNewAccountButton;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_form")
    WebElement loginForm;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    WebElement loginEmailField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    WebElement loginPwdField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    WebElement signInBtn;
    @AndroidFindBy(xpath = "//*[contains(@text, 'BudgetActivity')]")
    WebElement BudgetActivityHeader;

    public LoginPage(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public boolean isRegisterNewAccountBtnDisplayed() {
        boolean isRegisterNewAccountBtnDisplayed = true;
        if (!registerNewAccountButton.isDisplayed()) {
            isRegisterNewAccountBtnDisplayed = false;
        }
        return isRegisterNewAccountBtnDisplayed;
    }

    public void clickRegisterNewAccountBtn() {
        registerNewAccountButton.click();
    }

    public boolean isLoginFormDisplayed() {
        boolean isLoginFormDisplayed = true;
        if (!loginForm.isDisplayed()) {
            isLoginFormDisplayed = false;
        }
        return isLoginFormDisplayed;
    }

    public boolean isLoginFieldDisplayed() {
        boolean isLoginFieldDisplayed = true;
        if (!loginEmailField.isDisplayed()) {
            isLoginFieldDisplayed = false;
        }
        return isLoginFieldDisplayed;
    }

    public boolean isPassFieldDisplayed() {
        boolean isPassFieldDisplayed = true;
        if (!loginPwdField.isDisplayed()) {
            isPassFieldDisplayed = false;
        }
        return isPassFieldDisplayed;
    }

    public boolean isSignInBtnDisplayed() {
        boolean isSignInBtnDisplayed = true;
        if (!signInBtn.isDisplayed()) {
            isSignInBtnDisplayed = false;
        }
        return isSignInBtnDisplayed;
    }

    public void performLogin(String email, String password) {
        loginEmailField.sendKeys(email);
        loginPwdField.sendKeys(password);
        signInBtn.click();
    }
}
