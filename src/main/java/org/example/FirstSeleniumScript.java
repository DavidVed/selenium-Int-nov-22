package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class FirstSeleniumScript {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");

        String currentUrl = driver.getCurrentUrl();
        System.out.println("currentUrl = " + currentUrl);
        //בדיקה אם מתקבלת הכתובת הנכונה
        String expectedUrl = "https://www.google.com/";
        if (currentUrl.equals(expectedUrl)) {
            System.out.println("The page URL is correct.");
        }
        else {
            System.out.println("The expected URL is: " + expectedUrl + ", but I got: " + driver.getCurrentUrl());
        }

        //String pageSource = driver.getPageSource();
        //System.out.println("pageSource = " + pageSource);

        String title = driver.getTitle();
        System.out.println("title = " + title);

        driver.navigate().to("https://www.amazon.com");

        Thread.sleep(2000);

        title = driver.getTitle();
        System.out.println("title = " + title);

        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
        driver.quit();
    }
}
