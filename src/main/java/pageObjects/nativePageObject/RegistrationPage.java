package pageObjects.nativePageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_form")
    private WebElement registrationForm;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    private WebElement emailField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    private WebElement usernameField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    private WebElement passwordField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    private WebElement confirmPassField;
    @AndroidFindBy(className = "android.widget.CheckedTextView")
    private WebElement agreementCheckbox;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    private WebElement registerNewAccountBtn;

    public RegistrationPage(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }
    public enum ElementType {
        REGISTRATION_FORM,
        EMAIL_FIELD,
        USERNAME_FIELD,
        PASSWORD_FIELD,
        CONFIRM_PASSWORD_FIELD,
        AGREE_CHECKBOX,
        REGISTER_NEW_ACCOUNT_BUTTON
    }
    public boolean isElementDisplayed(ElementType elementType) {
        boolean isElementDisplayed = true;
        switch (elementType) {
            case REGISTRATION_FORM:
                isElementDisplayed = registrationForm.isDisplayed();
                break;
            case EMAIL_FIELD:
                isElementDisplayed = emailField.isDisplayed();
                break;
            case USERNAME_FIELD:
                isElementDisplayed = usernameField.isDisplayed();
                break;
            case PASSWORD_FIELD:
                isElementDisplayed = passwordField.isDisplayed();
                break;
            case CONFIRM_PASSWORD_FIELD:
                isElementDisplayed = confirmPassField.isDisplayed();
                break;
            case AGREE_CHECKBOX:
                isElementDisplayed = agreementCheckbox.isDisplayed();
                break;
            case REGISTER_NEW_ACCOUNT_BUTTON:
                isElementDisplayed = registerNewAccountBtn.isDisplayed();
                break;
            default:
                System.out.println("Element is not found");
        }
        return isElementDisplayed;
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
