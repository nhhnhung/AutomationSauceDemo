package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.helpers.PropertiesHelper;
import com.saucedemo.pages.AddToCartPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;

public class AddToCartTest extends BaseTest {
    AddToCartPage addToCartPage;
    LoginPage login;
    private String username = PropertiesHelper.getValue("username");
    private String pass = PropertiesHelper.getValue("pass");

    @BeforeClass
    public void initPages() {
        // Sau khi driver được tạo
        addToCartPage = new AddToCartPage(getDriver());
        login = new LoginPage(getDriver());
        login.Navigation(getURL());
        login.enterUserName(username);
        login.enterPass(pass);
        login.clickBTNLogin();

        Log.info("Đăng nhập thành công trước khi thêm giỏ hàng!");

    }

    @Test(description = "Thêm một sản phẩm thành công, giỏ hàng cập nhật")
    public void addToCartWithValidData(){
        Log.info("Thêm sản phẩm thành công");
        addToCartPage.waitForPageLoaded();
        addToCartPage.moveToFirstProduct();
        addToCartPage.clickAddFirstProductInCart();
        addToCartPage.checkIconCart(1);
        Log.info("Thêm sản phẩm thành công\nTestcase pass");
    }

    @Test(description = "Thêm 2 sản phẩm thành công, giỏ hàng cập nhật")
    public void addToCartWith2Product(){
        Log.info("Thêm sản phẩm thành công");
        addToCartPage.waitForPageLoaded();
        addToCartPage.moveToFirstProduct();
        addToCartPage.clickAddFirstProductInCart();
        addToCartPage.moveToSecProduct();
        addToCartPage.clickAddSecProductInCart();
        addToCartPage.checkIconCart(2);
        Log.info("Thêm 2 sản phẩm thành công\nTestcase pass");
    }

    @Test(description = "Vào giỏ hàng bỏ sản phẩm 1 ra ngoài")
    public void removeFirstProduct(){
        Log.info("Thêm sản phẩm thành công");
        addToCartPage.waitForPageLoaded();
        addToCartPage.moveToFirstProduct();
        addToCartPage.clickAddFirstProductInCart();
        addToCartPage.moveToSecProduct();
        addToCartPage.clickAddSecProductInCart();
        addToCartPage.checkIconCart(2);
        addToCartPage.clickCart();
        addToCartPage.removeFirstProduct();
        addToCartPage.checkNumberOnCart();
        Log.info("Xóa sản phẩm 1 thành công\nTestcase pass");
    }




}
