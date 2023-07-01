# 使用说明

目前已经适配`windows`以及`linux`系统下的`firefox`浏览器以及`windows`系统下的`chrome`浏览器

## 变更方法

### firefox

#### windows

1. 设置调用方法为`makeFirefoxDriver()`

   ```java
   WebDriver driver = this.makeFirefoxDriver();
   ```



2. 设置驱动为`geckodriver.exe`

   ```java
   System.setProperty("webdriver.gecko.driver", "lib/geckodriver-v0.33.0-win64/geckodriver.exe");
   ```

    - `webdriver.gecko.driver`：火狐浏览器的设置方式
    - `lib/geckodriver-v0.33.0-win64/geckodriver.exe`：需要注意目前适配的`64`位版本

#### linux

1. 设置调用方法为`makeFirefoxDriver()`

   ```java
   WebDriver driver = this.makeFirefoxDriver();
   ```



2. 设置驱动为`geckodriver`

   ```java
   System.setProperty("webdriver.gecko.driver", "lib/geckodriver-v0.33.0-linux64/geckodriver");
   ```

    - `lib/geckodriver-v0.33.0-linux64/geckodriver`：需要注意目前适配的`64`位版本

### chrome

#### windows

1. 设置调用方法为`makeChromeDriver()`

   ```java
   WebDriver driver = this.makeChromeDriver();
   ```



2. 设置驱动为`chromedriver.exe`

   ```java
   System.setProperty("webdriver.chrome.driver", "lib/chromedriver_win32/chromedriver.exe");
   ```

    - `webdriver.chrome.driver`：`chrome`浏览器的设置方式
    - `lib/chromedriver_win32/chromedriver.exe`：官方给出的版本依然可以适用`64`位系统

3. 设置`chrome`浏览器下默认只允许本地操作，解决`403`出错问题

   ```java
   chromeOptions.addArguments("--remote-allow-origins=*");
   ```

   