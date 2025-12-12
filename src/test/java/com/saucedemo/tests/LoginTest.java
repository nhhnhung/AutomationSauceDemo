package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.helpers.PropertiesHelper;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage login;
    private String username = PropertiesHelper.getValue("username");
    private String pass = PropertiesHelper.getValue("pass");


    @BeforeClass
    public void initPages() {
        // Sau khi driver được tạo, ta mới khởi tạo LoginPage
        login = new LoginPage(getDriver());

    }

    //Positive testcase
    @Test(description = "Đăng nhập thành công, dữ liệu hợp lý")
    public void testLoginWithValidCredentials() {
        Log.info("Nhập thông tin đăng nhập hợp lý");
        login.waitForPageLoaded();
        login.navigationToLogin(getURL());
        login.enterUserName(username);
        login.enterPass(pass);
        login.clickBTNLogin();
        String welcome = login.getSuccessMessage();
        Assert.assertTrue(welcome.contains("Swag Labs"), "Đăng nhập thất bại!!!");
        Log.info("Đăng nhập thành công!");
    }


    //Negative testcase
    @Test(description = "Đăng nhập thất bại khi bỏ trống")
    public void testLoginWithoutAll() {
        Log.info("Không nhập thông tin khi đăng nhập");
        login.waitForPageLoaded();
        login.navigationToLogin(getURL());
        login.clickBTNLogin();
        login.checkErrorMessage();
    }

    @Test(description = "Đăng nhập thất bại tài khoản bị khóa")
    public void testLoginWithLockUser() {
        Log.info("Nhập tài khoản bị khóa");
        login.waitForPageLoaded();
        login.navigationToLogin(getURL());
        login.enterUserName("locked_out_user");
        login.enterPass(pass);
        login.clickBTNLogin();
        login.checkErrorMessage();
    }


    @Test(description = "Đăng nhập thất bại, dữ liệu sai")
    public void loginWithIncorrectEmail() {
        Log.info("Nhập thông tin đăng nhập");
        login.waitForPageLoaded();
        login.navigationToLogin(getURL());
        login.enterUserName("demo123");
        login.enterPass(pass);
        login.clickBTNLogin();
        String error = login.getError();
        Assert.assertTrue(error.contains("Username and password do not match"), "Testcase false");
        Log.info("Testcase pass");
    }


}
