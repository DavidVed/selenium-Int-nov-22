package org.testcafe;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class WebpageSampleHomework {
    public static void main(String[] args) throws InterruptedException {
        Faker fakeDataGenerator = new Faker();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://devexpress.github.io/testcafe/example/");

//populate
        driver.findElement(By.id("populate")).click();
        driver.switchTo().alert().accept();
        driver.findElement(By.id("submit-button")).click();
        String peterParkerMessage = driver.findElement(By.id("article-header")).getText();
        System.out.println(peterParkerMessage);
        if (peterParkerMessage.contains("Peter Parker")) {
            System.out.println("The name is exactly as populated; the name is Peter Parker."
                    + "\n ----------------");
        } else {
            System.out.println("The name doesn't match." +
                    "The name that needs to be shown is Peter Parker." + "\n ----------------");
        }
        driver.navigate().back();
        WebElement nameTextField = driver.findElement(By.cssSelector("[name=\"name\"]"));
        String peterParker = nameTextField.getAttribute("value");
        if (peterParker.equals("Peter Parker")) {
            System.out.println("Populate button:" + "\n" + "Works." + "\n ----------------");
        } else {
            System.out.println("Populate button:" + "\n" + "don't work." + "\n ----------------");
        }
//your name
        nameTextField.clear();
        String name = fakeDataGenerator.superhero().name();
        nameTextField.sendKeys(name);

//primary Operating System choosing
        driver.findElement(By.id("linux")).click();
        driver.findElement(By.id("macos")).click();
        driver.findElement(By.id("windows")).click();

//TestCafe interface choosing
        Select testCafeInterface = new Select(driver.findElement(
                By.cssSelector("[name=\"preferred-interface\"]")));
        testCafeInterface.selectByVisibleText("Both");

//select all checkboxes
        WebElement checkbox1 = driver.findElement(By.cssSelector("[name=\"remote\"]"));
        WebElement checkbox2 = driver.findElement(By.cssSelector("[name=\"re-using\"]"));
        WebElement checkbox3 = driver.findElement(By.cssSelector("[name=\"background\"]"));
        WebElement checkbox4 = driver.findElement(By.cssSelector("[name=\"CI\"]"));
        WebElement checkbox5 = driver.findElement(By.cssSelector("[name=\"analysis\"]"));
        WebElement testCafeCheckbox = driver.findElement(By.cssSelector("[name=\"tried-test-cafe\"]"));
        if (!checkbox1.isSelected() || !checkbox2.isSelected() || !checkbox3.isSelected() ||
                !checkbox4.isSelected() || !checkbox5.isSelected() || !testCafeCheckbox.isSelected()) {
            checkbox1.click();
            checkbox2.click();
            checkbox3.click();
            checkbox4.click();
            checkbox5.click();
            testCafeCheckbox.click();
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

//move slider
        if (testCafeCheckbox.isSelected()) {
            WebElement scale = driver.findElement(By.cssSelector(
                    "[class=\"ui-slider-handle ui-corner-all ui-state-default\"]"));
            Actions actions = new Actions(driver);
            actions.dragAndDropBy(scale, 600, 0).perform();
//let us know
            WebElement letUsKnowTextField = driver.findElement(By.cssSelector("[name=\"comments\"]"));
            letUsKnowTextField.clear();
            letUsKnowTextField.sendKeys(fakeDataGenerator.yoda().quote());
        }

//submit
        driver.findElement(By.id("submit-button")).click();

//thank you page
        driver.get("https://devexpress.github.io/testcafe/example/thank-you.html");
//text
        String message = driver.findElement(By.id("article-header")).getText();
        System.out.println(message);
        if (message.contains(name)) {
            System.out.println("Your name is indeed " + name + "\n ----------------");
        } else {
            System.out.println("The name doesn't match your name." +
                    "Your name is : " + name + "\n ----------------");
        }
//url
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Url = " + currentUrl);
        String expectedUrl = "https://devexpress.github.io/testcafe/example/thank-you.html";
        if (currentUrl.equals(expectedUrl)) {
            System.out.println("The URL page is correct." + "\n ----------------");
        } else {
            System.out.println("The expected URL is: " +
                    expectedUrl + ", but I got: " + driver.getCurrentUrl() + "\n ----------------");
        }
//title
        String title = driver.getTitle();
        System.out.println("title = " + title);


        //Thread.sleep(3000);
        driver.quit();
    }
}
