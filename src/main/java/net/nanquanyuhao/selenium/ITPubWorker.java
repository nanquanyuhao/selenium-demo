package net.nanquanyuhao.selenium;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;

@Slf4j
public class ITPubWorker {

    /**
     * 反馈 chrome 浏览器驱动
     *
     * @return
     */
    private WebDriver makeChromeDriver(){
        // 设置驱动地址，32 及 64 位均可
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver-win64/chromedriver.exe");
        // System.setProperty("webdriver.chrome.driver", "lib/chromedriver-win32/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        // chrome  内核浏览器下默认只允许本地操作，解决 403 出错问题
        chromeOptions.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(chromeOptions);
    }

    private WebDriver makeFirefoxDriver(){
        // 设置驱动地址
        // windows
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver-v0.33.0-win64/geckodriver.exe");
        // linux
        // System.setProperty("webdriver.gecko.driver", "lib/geckodriver-v0.33.0-linux64/geckodriver");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        // chrome 浏览器下默认只允许本地操作，解决 403 出错问题
        // chromeOptions.addArguments("--remote-allow-origins=*");
        return new FirefoxDriver(firefoxOptions);
    }

    private WebDriver makeEdgeDriver(){
        // 设置驱动地址
        System.setProperty("webdriver.edge.driver", "lib/edgedriver_win64/msedgedriver.exe");
        EdgeOptions edgeOptions = new EdgeOptions();
        // chrome 内核浏览器下默认只允许本地操作，解决 403 出错问题
        edgeOptions.addArguments("--remote-allow-origins=*");
        return new EdgeDriver(edgeOptions);
    }

    public void execute() {

        // 初始化 web 测试驱动
        // WebDriver driver = this.makeChromeDriver();
        // WebDriver driver = this.makeFirefoxDriver();
        WebDriver driver = this.makeEdgeDriver();
        // 打开入口页
        driver.get("http://itpub.net/");

        try {
            // 阻塞 5 秒，确保页面加载成功
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int p = 1;
        // 获取官网前十页最新消息
        while (p <= 10) {
            // 获取当前页面新闻列表
            // 样式如：<div class="right-box"><h4><a target="_blank" href="http://blog.itpub.net/">文本</a></h4></div>
            List<WebElement> titleList = driver.findElements(By.cssSelector("div.right-box h4 a"));
            for (WebElement title : titleList) {
                log.info(String.format("%s\n\t->%s", title.getText(), title.getAttribute("href")));
            }

            // 定位到“下一页”按钮：<div class="page"><a href="?page=5" target="_self">文本</a></div>
            List<WebElement> pages = driver.findElements(By.cssSelector("div.page a"));
            WebElement nextPage = pages.get(pages.size() - 1);
            // 触发下一页按钮单击事件
            nextPage.click();
            p++;

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        driver.quit();
    }
}
