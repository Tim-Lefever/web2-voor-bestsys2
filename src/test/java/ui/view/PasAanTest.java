package ui.view;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PasAanTest {
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
    public void pasAan_NaarParametersDieBestaanInAnderePuzzel_PastObjectNietAan() {
        String naam = "Naam" + Integer.toString((int) (Math.random() * 1000 + 1));
        String merk = "Merk" + Integer.toString((int) (Math.random() * 1000 + 1));
        String aantal = Integer.toString((int) (Math.random() * 1000 + 1));
        String kleur = "Kleur" + Integer.toString((int) (Math.random() * 1000 + 1));
        String thema = "Thema" + Integer.toString((int) (Math.random() * 1000 + 1));

        voegPuzzelToe(naam, merk, aantal, kleur, thema);

        driver.get("localhost:8080/Assignment2_war/Controller?command=overview");

        WebElement editKnop = driver.findElement(By.xpath("/html/body/main/article/table/tbody/tr[2]/td[7]/a/img"));
        editKnop.click();

        sendKeysToInputWithId(naam, "Naam");
        sendKeysToInputWithId(merk, "Merk");
        sendKeysToInputWithId(aantal, "Stukken");
        sendKeysToInputWithId(kleur, "Kleur");
        sendKeysToInputWithId(thema, "Thema");

        WebElement submitButton = driver.findElement(By.cssSelector("html body main article form.form input#sendPuzzle.submit"));
        submitButton.click();

        assertEquals("Pas aan", driver.findElement(By.tagName("h2")).getText());
        assertTrue(paginaBevatItemMetText(driver.findElements(By.cssSelector(".foutmelding > li")), "Deze puzzel is al opgeslagen."));

    }

    @Test
    public void pasAan_MetGeldigeParametersEnAllesConfirmed_PastObjectAan() {
        String naam = "Naam" + Integer.toString((int) (Math.random() * 1000 + 1));
        String merk = "Merk" + Integer.toString((int) (Math.random() * 1000 + 1));
        String aantal = Integer.toString((int) (Math.random() * 1000 + 1));
        String kleur = "Kleur" + Integer.toString((int) (Math.random() * 1000 + 1));
        String thema = "Thema" + Integer.toString((int) (Math.random() * 1000 + 1));

        driver.get("localhost:8080/Assignment2_war/Controller?command=overview");
        WebElement editKnop = driver.findElement(By.xpath("/html/body/main/article/table/tbody/tr[2]/td[7]/a/img"));
        editKnop.click();

        sendKeysToInputWithId(naam, "Naam");
        sendKeysToInputWithId(merk, "Merk");
        sendKeysToInputWithId(aantal, "Stukken");
        sendKeysToInputWithId(kleur, "Kleur");
        sendKeysToInputWithId(thema, "Thema");

        WebElement submitButton = driver.findElement(By.cssSelector("html body main article form.form input#sendPuzzle.submit"));
        submitButton.click();

        WebElement confirmButton = driver.findElement(By.cssSelector("html body main article ul.knoppenlijst li.green form button.green"));
        confirmButton.click();


        assertEquals("Overzicht", driver.findElement(By.tagName("h2")).getText());
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), naam));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), merk));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), aantal));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), kleur));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), thema));
    }

    public void sendKeysToInputWithId(String keys, String id) {
        WebElement naamVeld = driver.findElement(By.id(id));
        naamVeld.clear();
        naamVeld.sendKeys(keys);
    }

    private void voegPuzzelToe(String naam, String merk, String aantal, String kleur, String thema) {
        driver.get("localhost:8080/Assignment2_war/Controller?command=voegToe");
        sendKeysToInputWithId(naam, "Naam");
        sendKeysToInputWithId(merk, "Merk");
        sendKeysToInputWithId(aantal, "aantalStukken");
        sendKeysToInputWithId(kleur, "Kleur");
        sendKeysToInputWithId(thema, "Thema");

        WebElement submitButton = driver.findElement(By.id("sendPuzzle"));
        submitButton.click();
    }

    private boolean paginaBevatItemMetText(List<WebElement> items, String tekst) {
        for (WebElement item : items) {
            if (item.getText().equals(tekst)) {
                return true;
            }
        }
        return false;
    }

}
