package ui.view;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


import static org.junit.Assert.assertEquals;

public class VerwijderTest {
    WebDriver driver;

    @Before
    public void setupDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void verwijder_MetConfirmation_VerwijdertObject() throws InterruptedException {
        driver.get("localhost:8080/Assignment2_war/Controller?command=overview");
        int aantal = driver.findElements(By.tagName("tr")).size();
        WebElement deleteKnop = driver.findElement(By.xpath("/html/body/main/article/table/tbody/tr[2]/td[8]/a/img"));
        deleteKnop.click();

        WebElement jaKnop = driver.findElement(By.id("ja"));
        jaKnop.click();

        assertEquals("Overzicht", driver.findElement(By.tagName("h2")).getText());
        assertEquals(aantal - 1, driver.findElements(By.tagName("tr")).size());
    }

    @Test
    public void verwijder_ZonderConfirmation_VerwijdertObjectNiet() {
        driver.get("localhost:8080/Assignment2_war/Controller?command=overview");
        int aantal = driver.findElements(By.tagName("tr")).size();
        WebElement deleteKnop = driver.findElement(By.xpath("/html/body/main/article/table/tbody/tr[2]/td[8]/a/img"));
        deleteKnop.click();

        WebElement neeKnop = driver.findElement(By.id("nee"));
        neeKnop.click();

        assertEquals("Overzicht", driver.findElement(By.tagName("h2")).getText());
        assertEquals(aantal, driver.findElements(By.tagName("tr")).size());
    }

}
