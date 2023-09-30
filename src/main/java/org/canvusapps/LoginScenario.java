package org.canvusapps;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginScenario {
    public static void main(String[] args) throws InterruptedException {
        Faker fakeDataGenerator = new Faker();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://prd.canvusapps.com/login");

        driver.findElement(By.id("email")).sendKeys(fakeDataGenerator.internet().emailAddress());

        WebElement passwordTextField = driver.findElement(By.cssSelector("[name=\"password\"]"));
        passwordTextField.clear();
        passwordTextField.sendKeys(fakeDataGenerator.internet().password());

        driver.findElement(By.xpath("//*[@name='remember_me']")).click();

        driver.findElement(By.cssSelector("[class=\"btn btn-primary large\"]")).click();

        String errorMessage = driver.findElement(By.cssSelector("[class=\"alert alert-notice alert-block notice\"]")).getText();

        System.out.println(errorMessage);
        if (errorMessage.equals("Invalid email or password")) {
            System.out.println("Error message is correct!");
        }
        else {
            System.out.println("Error message is not correct!");
        }

        Thread.sleep(3000);
        driver.quit();
    }
}