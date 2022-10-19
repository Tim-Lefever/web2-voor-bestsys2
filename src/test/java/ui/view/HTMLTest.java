package ui.view;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HTMLTest {

    WebDriver driver;

    @Before
    public void setupDriver() {
        WebDriverManager.firefoxdriver().setup();
        this.driver = new FirefoxDriver();
    }

    @After
    public void teardown() {
       driver.quit();
    }

    @Test
    public void Test_HtmlHomePagina_IsValide() {
        this.validateHtml("localhost:8080/Assignment2_war/Controller?command=home");
    }

    @Test
    public void Test_HtmlVoegToePagina_IsValide() {
        this.validateHtml("localhost:8080/Assignment2_war/Controller?command=voegToe");
    }

    @Test
    public void Test_HtmlOverzichtPagina_IsValide() {
        this.validateHtml("localhost:8080/Assignment2_war/Controller?command=overview");
    }

    @Test
    public void Test_HtmlZoekPagina_IsValide() {
        this.validateHtml("localhost:8080/Assignment2_war/Controller?command=zoekPagina");
    }

    @Test
    public void Test_pasAanPagina_IsValide() {
        driver.get("localhost:8080/Assignment2_war/Controller?command=overview");
        WebElement editKnop = driver.findElement(By.xpath("/html/body/main/article/table/tbody/tr[2]/td[7]/a/img"));
        editKnop.click();
        this.validateHtmlBySourceCode(driver.getPageSource());
    }

    @Test
    public void Test_verwijderPagina_IsValide() {
        driver.get("localhost:8080/Assignment2_war/Controller?command=overview");
        WebElement deleteKnop = driver.findElement(By.xpath("/html/body/main/article/table/tbody/tr[2]/td[8]/a/img"));
        deleteKnop.click();
        this.validateHtmlBySourceCode(driver.getPageSource());
    }

    @Test
    public void Test_zoekPagina_IsValide() {
        this.validateHtml("localhost:8080/Assignment2_war/Controller?command=zoekPagina");
    }

    @Test
    public void Test_zoekResultPaginaMetResult_IsValide() {
        this.validateHtml("localhost:8080/Assignment2_war/Controller?command=zoek&zoekOpdracht=e");
    }

    @Test
    public void Test_zoekResultPaginaZonderResult_IsValide() {
        this.validateHtml("localhost:8080/Assignment2_war/Controller?command=zoek&zoekOpdracht=xfcghzcvguzebchbibsss");
    }

    private void validateHtml(String url) {
        driver.get(url);
        validateHtmlBySourceCode(driver.getPageSource());
    }

    private void validateHtmlBySourceCode(String h) {
        String html = "<!DOCTYPE html>\n" + h;
        driver.get("https://validator.w3.org/#validate_by_input");
        WebElement textarea = driver.findElement(By.id("fragment"));
        textarea.sendKeys(html);
        WebElement submitButton = driver.findElement(By.cssSelector("#validate-by-input > form > p.submit_button > a"));
        submitButton.click();
        String result = driver.findElement(By.cssSelector("html body div#results p.success")).getText();
        assertEquals("Document checking completed. No errors or warnings to show.", result);
    }

    public void sendKeysToInputWithId(String keys, String id) {
        WebElement naamVeld = driver.findElement(By.id(id));
        naamVeld.sendKeys(keys);
    }



}
