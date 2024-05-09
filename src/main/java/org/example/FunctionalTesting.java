package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class FunctionalTesting {
    @Test
    public void login() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        System.out.println("Logging to Home page");
        driver.get("https://demo.dealsdray.com/");
        driver.findElement(By.name("username")).sendKeys("prexo.mis@dealsdray.com");
        driver.findElement(By.name("password")).sendKeys("prexo.mis@dealsdray.com");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(3000);
        System.out.println("Bulk ordering");
        driver.findElement(By.xpath("//span[text()='Order']")).click();
        driver.findElement(By.xpath("//span[text()='Orders']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Add Bulk Orders']")).click();

        System.out.println("Uploading Test File");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\Users\\Soheb Atif\\Downloads\\UI-Automation Test\\UI-Automation Test\\src\\main\\resources\\demo-data.xlsx");
        driver.findElement(By.xpath("//button[text()='Import']")).click();

        System.out.println("Validating Test file Data uploaded");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[text()='Validate Data']")).click();
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        alert.accept();

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//button[text()='Next']"))).build().perform();
        Thread.sleep(2000);

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String folderPath = "src/main/resources";
        String filePath = folderPath + "/functionalTestScreenshot.png";
        try {
            FileHandler.copy(screenshotFile, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
