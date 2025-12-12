package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import com.saucedemo.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }
    @FindBy(id = "checkout")
    WebElement btnCheckout;

    @FindBy(id = "first-name")
    WebElement firstNameInput;

    @FindBy(id = "last-name")
    WebElement lastNameInput;

    @FindBy(id = "postal-code")
    WebElement postalCodeInput;

    @FindBy(id = "continue")
    WebElement btnContinue;

    @FindBy(id = "back-to-products")
    WebElement btnBackHome;

    @FindBy(id = "react-burger-menu-btn")
    WebElement btnMenu;

    @FindBy(id = "inventory_sidebar_link")
    WebElement btnAllItem;

    @FindBy(xpath = "//h3[contains(@data-test,'error')]")
    WebElement errorMessage;

    @FindBy(xpath = "//h2[normalize-space()='Thank you for your order!']")
    WebElement successMessage;

    @FindBy(className = "inventory_item_name")
    WebElement productName;

    @FindBy(className = "inventory_item_price")
    WebElement productPrice;

    @FindBy(className = "summary_tax_label")
    WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    WebElement totalLabel;

    @FindBy(id = "finish")
    WebElement btnFinish;

    @FindBy(id="add-to-cart-sauce-labs-backpack")
    WebElement btn_addFirst;
    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    WebElement Cart;


    public void clickAddFirstProductInCart(){
        assertEquals(this.btn_addFirst.getText(),"Add to cart","Không phải nút Add to cart");
        Log.info("Nhấn nút thêm vào giỏ hàng");
        clickElement(this.btn_addFirst);
    }

    public void clickCart(){
        scrollToElement(Cart);
        clickElement(Cart);
        Log.info("Vào giỏ hàng");
    }

    public void checkName(){
        assertEquals(this.productName.getText(),"Sauce Labs Backpack","Tên sản phẩm không đúng");
        Log.info("Tên sản phẩm đã đúng");
    }

    public void checkPrice(){
        String priceText = this.productPrice.getText().replace("$","");
        Double aPrice = Double.parseDouble(priceText);
        assertEquals(aPrice,29.99,"Giá sản phẩm không đúng");
        Log.info("Giá sản phẩm đã đúng");
    }

    public void checkTax(){
        scrollToElement(this.taxLabel);
        String priceText = this.taxLabel.getText().replace("Tax: $","");
        Double aPrice = Double.parseDouble(priceText);
        assertEquals(aPrice,2.40,"Giá sản phẩm không đúng");
        Log.info("Giá sản phẩm đã đúng");
    }

    public void checkTotal(){
        scrollToElement(this.totalLabel);
        String priceText = this.totalLabel.getText().replace("Total: $","");
        Double aPrice = Double.parseDouble(priceText);
        assertEquals(aPrice,32.39,"Giá sản phẩm không đúng");
        Log.info("Giá sản phẩm đã đúng");
    }



    public void clickCheckout(){
        clickElement(btnCheckout);
    }
    public void clickContinue(){
        scrollToElement(this.btnContinue);
        clickElement(this.btnContinue);
    }
    public void clickBackHome(){
        scrollToElement(this.btnBackHome);
        clickElement(this.btnBackHome);
    }
    public void clickMenu(){
        scrollToElement(this.btnMenu);
        clickElement(this.btnMenu);
    }
    public void clickAllItem(){
        scrollToElement(this.btnAllItem);
        clickElement(this.btnAllItem);
    }
    public void clickFinish(){
        scrollToElement(this.btnFinish);
        clickElement(this.btnFinish);
    }

    public void senkeyFirstName(String name){
        scrollToElement(this.firstNameInput);
        this.firstNameInput.sendKeys(name);
        Log.info("Nhập vào firstname: "+name);
    }

    public void senkeyLastName(String name){
        scrollToElement(this.lastNameInput);
        this.lastNameInput.sendKeys(name);
        Log.info("Nhập vào lastname: "+name);
    }
    public void senkeyCode(String code){
        scrollToElement(this.postalCodeInput);
        this.postalCodeInput.sendKeys(code);
        Log.info("Nhập vào code: "+code);
    }

    public void MessError(){
        assertContains(this.errorMessage.getText(),"First Name is required","Không có thông báo lỗi");
        Log.info("Yêu câu nhập firstname");
    }
    public void MessSuccess(){
        assertContains(this.successMessage.getText(),"Thank you for your order!","Không có thông báo lỗi");
        Log.info("Đã thanh toán thành công");
    }

    public void isSuccessMessageDisplayed() {
        assertTrue(!successMessage.getText().contains("Thank you for your order!"),"Web thực hiện sai khi không có sản phẩm vẫn thanh toán");
    }
}
