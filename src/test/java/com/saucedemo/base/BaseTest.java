package com.saucedemo.base;

import com.saucedemo.helpers.PropertiesHelper;
import com.saucedemo.utils.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    private WebDriver driver;
    private static int PAGE_LOAD_TIMEOUT = 10;
    private static String URL = "https://www.saucedemo.com/";

    @BeforeClass
    public void setup() {
        //Load cấu hình
        PropertiesHelper.loadAllFiles();
        String browser = PropertiesHelper.getValue("browser");
        Log.info("Khởi tạo trình duyệt: " + browser);
        //Khởi tạo driver
        switch (browser.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                //Tạo ChromeOption => tắt thông báo
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
        driver.get(URL);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            Log.info("Đóng trình duyệt.");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    //Lấy link trang web
    public String getURL() {
        return URL;
    }


}
