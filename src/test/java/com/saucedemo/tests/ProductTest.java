package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.helpers.PropertiesHelper;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductPage;
import com.saucedemo.utils.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ProductTest extends BaseTest {
    LoginPage login;
    ProductPage productPage;
    private String username = PropertiesHelper.getValue("username");
    private String pass = PropertiesHelper.getValue("pass");

    @BeforeClass
    public void initPage(){
        productPage = new ProductPage(getDriver());
        login = new LoginPage(getDriver());
        login.Navigation(getURL());
        login.enterUserName(username);
        login.enterPass(pass);
        login.clickBTNLogin();

        Log.info("Đăng nhập thành công trước khi kiểm tra");
    }

    @Test(description = "Kiểm tra số lượng sản phẩm có bằng 6 không")
    public void verifyProductCount(){
        productPage.waitForPageLoaded();
        productPage.getProductCount();
    }

    @Test(description = "Sắp xếp từ A - Z")
    public void verifySortAZ(){
        productPage.waitForPageLoaded();
        productPage.selectSort("Name (A to Z)");
        productPage.checkSortName("Name (A to Z)","Sắp xếp không theo A - Z");
    }

    @Test(description = "Sắp xếp từ Z - A")
    public void verifySortZA(){
        productPage.waitForPageLoaded();
        productPage.selectSort("Name (Z to A)");
        productPage.checkSortName("Name (Z to A)","Sắp xếp không theo Z - A");
    }

    @Test(description = "Sắp xếp giá từ thấp đến cao")
    public void verifySortPriceLowHigh(){
        productPage.waitForPageLoaded();
        productPage.selectSort("Price (low to high)");
        productPage.checkSortPrice("Price (low to high)","Sắp xếp không theo giá từ thấp đến cao");
    }

    @Test(description = "Sắp xếp giá từ cao đến thấp")
    public void verifySortPriceHighLow(){
        productPage.waitForPageLoaded();
        productPage.selectSort("Price (high to low)");
        productPage.checkSortPrice("Price (high to low)","Sắp xếp không theo giá từ cao đến thấp");
    }



}
