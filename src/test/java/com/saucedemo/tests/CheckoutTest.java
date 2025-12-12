package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.helpers.PropertiesHelper;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductPage;
import com.saucedemo.utils.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {
    CheckoutPage checkoutPage;
    LoginPage login;
    private String username = PropertiesHelper.getValue("username");
    private String pass = PropertiesHelper.getValue("pass");

    @BeforeClass
    public void initPage(){
        checkoutPage = new CheckoutPage(getDriver());
        login = new LoginPage(getDriver());
        login.Navigation(getURL());
        login.enterUserName(username);
        login.enterPass(pass);
        login.clickBTNLogin();

        Log.info("Đăng nhập thành công trước khi kiểm tra");

    }

    public void chooseProduct(){
        checkoutPage.clickAddFirstProductInCart();
        checkoutPage.clickCart();
        checkoutPage.checkName();
        checkoutPage.checkPrice();
    }

    @Test(priority = 0,description = "Thanh toán thành công với dữ liệu hợp lý")
    public void checkoutValidInput(){
        checkoutPage.waitForPageLoaded();
        chooseProduct();
        checkoutPage.clickCheckout();
        checkoutPage.senkeyFirstName("Hanh");
        checkoutPage.senkeyLastName("Nguyen");
        checkoutPage.senkeyCode(String.valueOf(9000));
        checkoutPage.clickContinue();
        checkoutPage.checkTax();
        checkoutPage.checkTotal();
        checkoutPage.clickFinish();
        checkoutPage.MessSuccess();
        checkoutPage.clickBackHome();
    }

    @Test(priority = 1, description = "Thanh toán thất bại khi không nhập form + có sản phẩm trong giỏ")
    public void checkoutFail(){
        checkoutPage.waitForPageLoaded();
        chooseProduct();
        checkoutPage.clickCheckout();
        checkoutPage.clickContinue();
        checkoutPage.MessError();
        checkoutPage.clickMenu();
        checkoutPage.clickAllItem();

    }
    @Test(priority = 2,description = "Thanh toán thất bại khi nhập đúng form nhưng không có sản phẩm trong giỏ")
    public void checkoutFailWithoutProduct(){
        checkoutPage.waitForPageLoaded();
        checkoutPage.clickCart();
        checkoutPage.clickCheckout();
        checkoutPage.senkeyFirstName("Hanh");
        checkoutPage.senkeyLastName("Nguyen");
        checkoutPage.senkeyCode(String.valueOf(9000));
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
        checkoutPage.isSuccessMessageDisplayed();
    }

}
