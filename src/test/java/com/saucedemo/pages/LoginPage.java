package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import com.saucedemo.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import java.util.List;

public class LoginPage extends BasePage {
    @FindBy(id = "user-name")
    private WebElement usernameInput;
    @FindBy(id = "password")
    private WebElement passInput;

    @FindBy(id = "login-button")
    private WebElement submit_Login;

    @FindBy(xpath = "//span[@class='base']]")
    private WebElement titleLogin;

    @FindBy(xpath = "//h3[normalize-space()='Epic sadface: Username is required']")
    List<WebElement> errorMassages;

    @FindBy(id = "email-error")
    WebElement errorInvalidEmailFormat;

    @FindBy(xpath = "//div[@class='app_logo']")
    private WebElement successMessage;
    @FindBy(xpath = "//h3[contains(text(),'Epic sadface: Username and password do not match a')]")
    private WebElement error;
    @FindBy(xpath = "//h3[contains(text(),'Epic sadface: Sorry, this user has been locked out')]")
    private WebElement msgLock;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }


    public void navigationToLogin(String Url) {
        Navigation(Url);
    }

    public void enterUserName(String username) {
        enterText(this.usernameInput, username);
    }

    public void enterPass(String password) {
        enterText(this.passInput, password);
    }

    public  void clickBTNLogin(){
        clickElement(submit_Login);
    }

    // Thông báo đúng
    public String getSuccessMessage() {
        waitForElementVisible(this.successMessage);
        return getText(this.successMessage);
    }
    //Thông báo sai thông tin
     public String getError() {
        waitForElementVisible(this.error);
        return getText(this.error);
    }

    public String getErrorInvalidEmailFormat() {
        waitForElementVisible(this.errorInvalidEmailFormat);
        return getText(this.errorInvalidEmailFormat);
    }

    public void checkErrorMessage() {
        for (WebElement e : this.errorMassages) {
            Assert.assertTrue(getText(e).contains("Username is required"), "Test false");
        }
        Log.info("Testcase pass");
    }
    public void checkMessageLock() {
        Assert.assertTrue(getText(this.msgLock).contains("Sorry, this user has been locked out."), "Test false");
        Log.info("Testcase pass");
    }

}
