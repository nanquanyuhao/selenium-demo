package net.nanquanyuhao.selenium;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeleniumDemoApplicationTests {

    @Test
    public void contextLoads() {
        new ITPubWorker().execute();
    }
}
