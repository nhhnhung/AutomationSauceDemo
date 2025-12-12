package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import com.saucedemo.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class AddToCartPage extends BasePage {

    public AddToCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    @FindBy(id="add-to-cart-sauce-labs-backpack")
    WebElement btn_addFirst;
    @FindBy(xpath = "//div[normalize-space()='Sauce Labs Backpack']")
    WebElement firstProduct;
    @FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    WebElement alertMessage;
    @FindBy(id="remove-sauce-labs-backpack")
    WebElement btn_removeFirst;
    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    WebElement iconNumberCart;
    @FindBy(id = "item_2_title_link")
    WebElement secProduct;
    @FindBy(id="add-to-cart-sauce-labs-onesie")
    WebElement btn_addSec;
    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    WebElement Cart;

    @FindBys(
            @FindBy(xpath = "//div[@class='cart_item']")
    )
    List<WebElement> cartItem;

    public void pressEnter() throws AWTException {
        try {
            Log.info("Nhấn Enter");
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            Log.error("Không nhấn Enter được");
        }
    }


    public void moveToFirstProduct(){
        Actions ac = new Actions(driver);
        ac.moveToElement(this.firstProduct).perform();
    }
    public void moveToSecProduct(){
        Actions ac = new Actions(driver);
        ac.moveToElement(this.secProduct).perform();
    }
    public void clickCart(){
        scrollToElement(Cart);
        clickElement(Cart);
        Log.info("Vào giỏ hàng");
    }

    public void clickAddFirstProductInCart(){
        assertEquals(this.btn_addFirst.getText(),"Add to cart","Không phải nút Add to cart");
        Log.info("Nhấn nút thêm vào giỏ hàng");
        clickElement(this.btn_addFirst);
    }
    public void clickAddSecProductInCart(){
        assertEquals(this.btn_addSec.getText(),"Add to cart","Không phải nút Add to cart");
        Log.info("Nhấn nút thêm vào giỏ hàng");
        clickElement(this.btn_addSec);
    }

    public void checkIconCart(int number){
        assertEquals(this.iconNumberCart.getText(),String.valueOf(number),"Giỏ hàng không đúng số lượng");
        Log.info("Kiểm tra số lượng đơn hàng sao khi thêm sản phẩm");
    }

    public void checkNumberOnCart(){
        assertEquals(this.cartItem.size(),1,"Số lượng sản phẩm trong giỏ hàng "+" "+ this.cartItem.size());
        Log.info("Giỏ hàng còn "+" "+this.cartItem.size()+" sản phẩm");
    }

    public void removeFirstProduct(){
        Log.info("Xóa sản phẩm đầu tiên");
        clickElement(this.btn_removeFirst);
    }

}
