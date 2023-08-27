package pageObjects.nativePageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_form")
    WebElement registrationForm;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    public static WebElement emailField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    WebElement usernameField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    WebElement passwordField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    WebElement confirmPassField;
    @AndroidFindBy(className = "android.widget.CheckedTextView")
    WebElement agreementCheckbox;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    WebElement registerNewAccountBtn;

    public RegistrationPage(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public boolean isRegistrationFormDisplayed() {
        boolean isRegistrationFormDisplayed = true;
        if (!registrationForm.isDisplayed()) {
            isRegistrationFormDisplayed = false;
        }
        return isRegistrationFormDisplayed;
    }

    public boolean isEmailFieldDisplayed() {
        boolean isEmailFieldDisplayed = true;
        if (!emailField.isDisplayed()) {
            isEmailFieldDisplayed = false;
        }
        return isEmailFieldDisplayed;
    }

    public boolean isUsernameFieldDisplayed() {
        boolean isUsernameFieldDisplayed = true;
        if (!usernameField.isDisplayed()) {
            isUsernameFieldDisplayed = false;
        }
        return isUsernameFieldDisplayed;
    }

    public boolean isPasswordFieldDisplayed() {
        boolean isPassFieldDisplayed = true;
        if (!passwordField.isDisplayed()) {
            isPassFieldDisplayed = false;
        }
        return isPassFieldDisplayed;
    }

    public boolean isConfirmPasswordFieldDisplayed() {
        boolean isConfirmPassFieldDisplayed = true;
        if (!confirmPassField.isDisplayed()) {
            isConfirmPassFieldDisplayed = false;
        }
        return isConfirmPassFieldDisplayed;
    }

    public boolean isAgreeCheckboxDisplayed() {
        boolean isAgreeCheckboxDisplayed = true;
        if (!agreementCheckbox.isDisplayed()) {
            isAgreeCheckboxDisplayed = false;
        }
        return isAgreeCheckboxDisplayed;
    }

    public boolean isRegisterNewAccountDisplayed() {
        boolean isRegisterNewAccountDisplayed = true;
        if (!registerNewAccountBtn.isDisplayed()) {
            isRegisterNewAccountDisplayed = false;
        }
        return isRegisterNewAccountDisplayed;
    }

    public void enterRegistrationDetails(String email, String username, String password) {
        emailField.sendKeys(email);
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        confirmPassField.sendKeys(password);
        agreementCheckbox.click();
    }

    public void registerNewAccount() {
        registerNewAccountBtn.click();
    }
}
