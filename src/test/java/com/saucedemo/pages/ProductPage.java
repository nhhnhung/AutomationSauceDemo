package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import com.saucedemo.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    @FindBy(className = "inventory_item")
    List<WebElement> productItem;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productName;

    @FindBy(className = "inventory_item_price")
    List<WebElement> productPrice;


    @FindBy(xpath = "//select[@class='product_sort_container']")
    WebElement dropDownSort;



    public void getProductCount() {
        assertEquals(this.productItem.size(),6,"Số sản phẩm không bằng 6");
        Log.info("Số sản phẩm bằng 6");
    }

    public void selectSort(String value) {
        new Select(this.dropDownSort).selectByVisibleText(value);
        Log.info("Chọn sắp xếp theo: " + value);
    }

    public List<String> getProductName() {
        return this.productName.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getProductPrice() {
        return productPrice.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }


    public void checkSortName(String sort, String messFail){
        this.selectSort(sort);
        List<String> ListSort;
        if(sort.equals("Name (A to Z)")){
            ListSort = this.getProductName().stream()
                    .sorted().collect(Collectors.toList());
        }else{
            ListSort = this.getProductName().stream()
                    .sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        }

        assertEquals(this.getProductName(),ListSort, messFail);
        Log.info("So sánh sắp xếp theo tên thành công");
    }

    public void checkSortPrice(String sort, String messFail){
        this.selectSort(sort);
        List<Double> ListSort;
        if(sort.equals("Price (low to high)")){
            ListSort = this.getProductPrice().stream()
                    .sorted().collect(Collectors.toList());
        }else{
            ListSort = this.getProductPrice().stream()
                    .sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        }

        assertEquals(this.getProductPrice(),ListSort, messFail);
        Log.info("So sánh sắp xếp theo giá thành công");
    }

}
