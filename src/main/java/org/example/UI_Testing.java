package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UI_Testing {
    private void loadURL(WebDriver driver, String URL) {
        System.out.println("Loading the URL");
        driver.manage().window().maximize();
        driver.get(URL);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(driver.getCurrentUrl(), URL);
        takeScreenshot(driver);
    }

    private void takeScreenshot(WebDriver driver) {
        System.out.println("Taking screenshots in different dimensions");
        Dimension[] resolutions = {
                new Dimension(1920, 1080),
                new Dimension(1366, 768),
                new Dimension(1536, 864)
        };
        for (Dimension resolution : resolutions) {
            driver.manage().window().setSize(resolution);
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File folder = new File("src/main/resources/screenshots/" + resolution.getWidth() + "x" + resolution.getHeight());
            folder.mkdirs();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String fileName = "Screenshot-" + dateFormat.format(new Date()) + ".png";
            String filePath = folder.getPath() + "/" + fileName;
            try {
                FileHandler.copy(screenshotFile, new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void loadURLInChrome() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        loadURL(driver, "https://www.getcalley.com/");
        loadURL(driver, "https://www.getcalley.com/calley-call-from-browser/");
        loadURL(driver, "https://www.getcalley.com/calley-pro-features/");
        loadURL(driver, "https://www.getcalley.com/best-auto-dialer-app/");
        loadURL(driver, "https://www.getcalley.com/how-calley-auto-dialer-app-works/");
        driver.quit();
    }
    @Test
    public void loadURLInFirefox() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        loadURL(driver, "https://www.getcalley.com/");
        loadURL(driver, "https://www.getcalley.com/calley-call-from-browser/");
        loadURL(driver, "https://www.getcalley.com/calley-pro-features/");
        loadURL(driver, "https://www.getcalley.com/best-auto-dialer-app/");
        loadURL(driver, "https://www.getcalley.com/how-calley-auto-dialer-app-works/");
        driver.quit();
    }
    @Test
    public void loadURLInSafari() {
        WebDriverManager.safaridriver().setup();
        WebDriver driver = new SafariDriver();
        loadURL(driver, "https://www.getcalley.com/");
        loadURL(driver, "https://www.getcalley.com/calley-call-from-browser/");
        loadURL(driver, "https://www.getcalley.com/calley-pro-features/");
        loadURL(driver, "https://www.getcalley.com/best-auto-dialer-app/");
        loadURL(driver, "https://www.getcalley.com/how-calley-auto-dialer-app-works/");
        driver.quit();
    }
}
