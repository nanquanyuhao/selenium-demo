package net.nanquanyuhao.selenium;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

@Slf4j
public class ITPubWorker {

    public void execute() {
        // 设置驱动地址
        // System.setProperty("webdriver.chrome.driver", "D:\\code\\github\\selenium-demo\\lib\\chromedriver_win32\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver_win32/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        // 默认只允许本地操作，解决 403 出错问题
        chromeOptions.addArguments("--remote-allow-origins=*");
        // 初始化 web 测试驱动
        WebDriver driver = new ChromeDriver(chromeOptions);
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
            List<WebElement> titleList = driver.findElements(By.cssSelector("div.right-box h4 a"));
            for (WebElement title : titleList) {
                log.info(String.format("%s\n\t->%s", title.getText(), title.getAttribute("href")));
            }

            // 定位到“下一页”按钮
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
