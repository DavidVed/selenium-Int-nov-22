package org.canvusapps;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SignUpScenario {
    public static void main(String[] args) throws InterruptedException {
        Faker fakeDataGenerator = new Faker();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://prd.canvusapps.com/signup");

//full name
        WebElement fullNameTextField = driver.findElement(By.cssSelector("[name=\"user[name]\"]"));
        fullNameTextField.clear();
        fullNameTextField.sendKeys(fakeDataGenerator.gameOfThrones().character());
//email
        WebElement emailTextField = driver.findElement(By.cssSelector("[name=\"user[email]\"]"));
        emailTextField.clear();
        emailTextField.sendKeys(fakeDataGenerator.internet().emailAddress());
//password
        WebElement passwordTextField = driver.findElement(By.cssSelector("[name=\"user[password]\"]"));
        passwordTextField.clear();
        String password = fakeDataGenerator.internet().password();
        passwordTextField.sendKeys(password);
//confirmation password
        WebElement confirmationPasswordTextField = driver.findElement(By.cssSelector("[name=\"user[password_confirmation]\"]"));
        confirmationPasswordTextField.clear();
        confirmationPasswordTextField.sendKeys(password);
//company name
        WebElement companyNameTextField = driver.findElement(By.cssSelector("[name=\"company[name]\"]"));
        companyNameTextField.clear();
        companyNameTextField.sendKeys(fakeDataGenerator.gameOfThrones().house());
//sign up
        driver.findElement(By.cssSelector("[class=\"submit btn btn-primary\"]")).click();
//message check
        String message = driver.findElement(By.cssSelector("[class=\"lead\"]")).getText();
        System.out.println(message);
        if (message.equals("Thanks for signing up! Continue to login to start setting up your company.")) {
            System.out.println("Message is correct!");
        } else {
            System.out.println("Message is not correct!");
        }
//url check
        String currentUrl = driver.getCurrentUrl();
        System.out.println("currentUrl = " + currentUrl);
        String expectedUrl = "https://prd.canvusapps.com/register";
        if (currentUrl.equals(expectedUrl)) {
            System.out.println("The page URL is correct.");
        } else {
            System.out.println("The expected URL is: " + expectedUrl + ", but I got: " + driver.getCurrentUrl());
        }

        Thread.sleep(2000);
        driver.quit();
    }
}
