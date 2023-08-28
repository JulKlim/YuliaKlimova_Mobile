package pageObjects.nativePageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    private WebElement registerNewAccountButton;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_form")
    private WebElement loginForm;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    private WebElement loginEmailField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    private WebElement loginPwdField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    private WebElement signInBtn;

    public LoginPage(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public enum ElementType {
        REGISTER_NEW_ACCOUNT_BUTTON,
        LOGIN_FORM,
        LOGIN_EMAIL_FIELD,
        LOGIN_PASSWORD_FIELD,
        SIGN_IN_BUTTON
    }

    public boolean isElementDisplayed(ElementType elementType) {
        boolean isElementDisplayed = true;
        switch (elementType) {
            case REGISTER_NEW_ACCOUNT_BUTTON:
                isElementDisplayed = registerNewAccountButton.isDisplayed();
                break;
            case LOGIN_FORM:
                isElementDisplayed = loginForm.isDisplayed();
                break;
            case LOGIN_EMAIL_FIELD:
                isElementDisplayed = loginEmailField.isDisplayed();
                break;
            case LOGIN_PASSWORD_FIELD:
                isElementDisplayed = loginPwdField.isDisplayed();
                break;
                case SIGN_IN_BUTTON:
                isElementDisplayed = signInBtn.isDisplayed();
                break;
            default:
                System.out.println("Element is not found");
        }
        return isElementDisplayed;
    }

    public void clickRegisterNewAccountBtn() {
        registerNewAccountButton.click();
    }

    public void performLogin(String email, String password) {
        loginEmailField.sendKeys(email);
        loginPwdField.sendKeys(password);
        signInBtn.click();
    }
}
