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

public class VoegToeTest {
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
    public void VoegToe_MetOngeldigeParameters_VoegtGeenNieuwObjectToe() {
        driver.get("http://localhost:8080/Assignment2_war/Controller?command=overview");
        voegPuzzelToe("", "", "", "", "");
        assertEquals("Nieuwe puzzel", driver.findElement(By.tagName("h2")).getText());
        assertTrue(paginaBevatItemMetText(driver.findElements(By.cssSelector(".foutmelding > li")), "De naam is ongeldig!"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.cssSelector(".foutmelding > li")), "Het merk is ongeldig!"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.cssSelector(".foutmelding > li")), "Vul een nummer in voor het aantal stukken."));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.cssSelector(".foutmelding > li")), "Het thema is ongeldig!"));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.cssSelector(".foutmelding > li")), "De kleur is ongeldig!"));

    }

    @Test
    public void VoegToe_MetGeldigeParameters_VoegtNieuwObjectToe() {
        String naam = "Naam" + Integer.toString((int) (Math.random() * 1000 + 1));
        String merk = "Merk" + Integer.toString((int) (Math.random() * 1000 + 1));
        String aantal = Integer.toString((int) (Math.random() * 1000 + 1));
        String kleur = "Kleur" + Integer.toString((int) (Math.random() * 1000 + 1));
        String thema = "Thema" + Integer.toString((int) (Math.random() * 1000 + 1));

        voegPuzzelToe(naam, merk, aantal, kleur, thema);

        System.out.println(driver.findElement(By.tagName("h2")).getText());
        assertEquals("Overzicht", driver.findElement(By.tagName("h2")).getText());

        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), naam));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), merk));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), aantal));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), kleur));
        assertTrue(paginaBevatItemMetText(driver.findElements(By.tagName("td")), thema));

    }

    @Test
    public void VoegToe_MetGeldigeParametersEnOngeldigAantal_VoegtGeenNieuwObjectToe() {
        String naam = "Naam" + Integer.toString((int) (Math.random() * 1000 + 1));
        String merk = "Merk" + Integer.toString((int) (Math.random() * 1000 + 1));
        String aantal = "a" + Integer.toString((int) (Math.random() * 1000 + 1));
        String kleur = "Kleur" + Integer.toString((int) (Math.random() * 1000 + 1));
        String thema = "Thema" + Integer.toString((int) (Math.random() * 1000 + 1));

        voegPuzzelToe(naam, merk, aantal, kleur, thema);

        System.out.println(driver.findElement(By.tagName("h2")).getText());
        assertEquals("Nieuwe puzzel", driver.findElement(By.tagName("h2")).getText());

        assertTrue(paginaBevatItemMetText(driver.findElements(By.cssSelector(".foutmelding > li")), "Vul een nummer in voor het aantal stukken."));

    }

    @Test
    public void VoegToe_2keerDezelfdeParameters_VoegtGeenNieuwObjectToe() {
        String naam = "Naam" + Integer.toString((int) (Math.random() * 1000 + 1));
        String merk = "Merk" + Integer.toString((int) (Math.random() * 1000 + 1));
        String aantal = Integer.toString((int) (Math.random() * 1000 + 1));
        String kleur = "Kleur" + Integer.toString((int) (Math.random() * 1000 + 1));
        String thema = "Thema" + Integer.toString((int) (Math.random() * 1000 + 1));

        voegPuzzelToe(naam, merk, aantal, kleur, thema);
        voegPuzzelToe(naam, merk, aantal, kleur, thema);

        assertEquals("Nieuwe puzzel", driver.findElement(By.tagName("h2")).getText());
        assertTrue(paginaBevatItemMetText(driver.findElements(By.cssSelector(".foutmelding > li")), "Deze puzzel is al opgeslagen."));


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

    public void sendKeysToInputWithId(String keys, String id) {
        WebElement naamVeld = driver.findElement(By.id(id));
        naamVeld.sendKeys(keys);
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
