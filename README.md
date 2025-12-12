# AutomationSauceDemo

**AutomationSauceDemo** là project tự động hóa kiểm thử cho website [Sauce Demo](https://www.saucedemo.com/) sử dụng **Java, Selenium WebDriver, TestNG** và **Page Object Model (POM)**. Project kiểm tra các chức năng chính như đăng nhập, thêm sản phẩm vào giỏ hàng, và quy trình thanh toán với các scenario hợp lệ và không hợp lệ, đồng thời phát hiện bug như cho phép checkout khi giỏ hàng rỗng.

## Features
- Kiểm tra đăng nhập hợp lệ và không hợp lệ.
- Thêm sản phẩm vào giỏ hàng, kiểm tra tên và giá sản phẩm.
- Thanh toán (Checkout) với các scenario:
  - Thanh toán thành công với dữ liệu hợp lệ.
  - Thanh toán thất bại khi không điền thông tin.
  - Thanh toán thất bại khi giỏ hàng rỗng (bug detection).
- Logging và báo cáo kết quả test bằng **TestNG + Log4j**.
- Áp dụng **Page Object Model (POM)** giúp code dễ bảo trì.

## Prerequisites
- Java JDK 8 trở lên
- Maven
- IDE: IntelliJ IDEA / Eclipse
- Git

## Installation
1. Clone repository:
   ```bash
   git clone https://github.com/nhhnhung/AutomationSauceDemo.git
