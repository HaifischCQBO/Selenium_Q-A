package ex.browserapi;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ex.fixtures.Fixtures;
import ex.resources.Selenium;

public class Tests extends Fixtures {

	private WebElement elemento;
	Date date = new Date();
	Selenium s = new Selenium();

	public void PruebaJunit() { // DECLARAR VARIABLE DE SISTEMA
		// SE CREA UN OBJETO SELENIUM, QUE CONTIENE TODAS LAS UTILIDADES QUE NECESITAMOS

		// ejemplo de como se realiza un refresh/F5/Actualizacion a la pagina en cual
		// nos encontramos
		driver.navigate().refresh();
		// funcion para obtener el titulo de la pagina actual
		s.Imprimir(driver.getTitle());
		// funcion para obtener la actual URL de la pagina donde estamos
		System.out.println(driver.getCurrentUrl());
		// funcion para obtener el codigo fuente de la pagina
		// System.out.println(driver.getPageSource());

		// Busqueda y almacenamiento de elemento webdriver dentro de una variable para
		// ser trabajada
		s.ClickBy(By.name("country"));

		// llamada de objeto selenium, a su metodo printElementInfo()
		s.printElementInfo(elemento);

		// Ejemplos de Asserts de JUnit
		// assertEquals(driver.getTitle(), "Google", "El mensaje de la pagina no es el
		// correcto: ");
		// Assert.assertNotEquals(driver.getTitle(), "Google", "El mensaje de la pagina
		// no es diferente: ");

	}
	
	
	public void AlertTest() {
		
		try {
			
		Alert a = driver.switchTo().alert();
		
		a.accept();
		a.dismiss();
		
		}catch(NoAlertPresentException nape) {
			s.Imprimir(nape.getCause().toString());
			
		}
		
		
		
	}
	@Test
	public void Testing() {
		
		//driver.findElement(By.xpath("//input[name='firstName'],input[maxleght='20']")).click();
		driver.findElement(By.xpath("//input[name='firstName'][maxleght='20']")).click();

		
	}
	
	public void ActionChains() {

		//driver.get("https://pruebas-de-test.webnode.es/");
		Actions action = new Actions(driver);

		/*action.click(driver.findElement(By.id("sign-on")))
		.click(driver.findElement(By.tagName("REGISTER")))
		.click(driver.findElement(By.tagName("SUPPORT")))
		.click(driver.findElement(By.tagName("CONTACT"))).build().perform();*/
		
		/*action
		.moveToElement(driver.findElement(By.linkText("Tienda")))		
		.build().perform();
		s.takeSnapShot(driver);*/
		
		
		
		WebElement Elemento = driver.findElement(By.name("firstName"));
		
		
		action
		.keyDown(Keys.SHIFT).sendKeys(Elemento,Keys.chord("v","i","c"))
		.pause(5000)
		.keyUp(Keys.SHIFT).sendKeys(Elemento,Keys.chord("t","o","r"))
		.build().perform();
		
		s.Pausa(10);
	}
	
	
	public void GetCookies() {
		
		s.Imprimir(driver.manage().getCookies().toString());
		
		Cookie ck = new Cookie("Selenium", "id255458778784");
		driver.manage().addCookie(ck);
		
		s.Imprimir(driver.manage().getCookies().toString());

	}
	
	
	public void PasajeAvion() { // DECLARAR VARIABLE DE SISTEMA

		// SE CREA UN OBJETO SELENIUM, QUE CONTIENE TODAS LAS UTILIDADES QUE NECESITAMOS

		List<WebElement> campos_rellenables = driver.findElements(By.tagName("input"));

		s.Imprimir("Se encontraron :" + String.valueOf(campos_rellenables.size()) + " Correspondiente a el tag input");

		// BASICA
		for (int x = 0; x < campos_rellenables.size(); x++) {

			if (campos_rellenables.get(x).isDisplayed()) {
				s.Imprimir("--------------------------------------------------------");

				campos_rellenables.get(x).sendKeys("ok" + x);
			}
		}

		// PRO
		for (WebElement lista : campos_rellenables) {

			if (lista.isDisplayed()) {
				s.Imprimir("--------------------------------------------------------");

				s.printElementInfo(lista);
			}

		}

		// MEJOR METODO
		Select lista_paises = new Select(driver.findElement(By.name("country")));

		Random r = new Random();

		s.Imprimir(String.valueOf(lista_paises.getOptions().size()));

		lista_paises.selectByIndex(r.nextInt(100));
		// s.Pausa(1);
		lista_paises.selectByValue("ANGOLA");
		// s.Pausa(1);
		lista_paises.selectByVisibleText("ANTARCTICA");
		// s.Pausa(1);

		// METODO RECOMENDADO

		// WebElement lista_paises = driver.findElement(By.name("country"));
		// lista_paises.sendKeys("CHILE");

		String Ventana_pasajes = driver.getWindowHandle();

		s.Imprimir(Ventana_pasajes);

		// javascript executor
		elemento = driver.findElement(By.name("register"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open(arguments[0], '_blank');", "https://www.google.com");

		// MANEJO DE VENTANAS
		for (String Ventana : driver.getWindowHandles()) {
			
			
			
			driver.switchTo().window(Ventana.toString());
		}
	
		s.Imprimir(driver.getTitle());
		s.Imprimir(driver.getWindowHandle());

		driver.close();

		driver.switchTo().window(Ventana_pasajes);
		
		s.Imprimir(driver.getTitle());

		long ts = System.currentTimeMillis();
		Date localTime = new Date(ts);
		String format = "yyyyMMddHHmmss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		s.Imprimir(sdf.toString());

		System.out.println(date.toString());
		js.executeScript("arguments[0].click();", elemento);

		s.Imprimir(driver.getWindowHandle());

	}


	public void handleIframe() {

		driver.navigate().to("https://apps.tiny.cloud/products/cloud-essential/");

		// s.Imprimir(driver.findElement(By.id("tinymce")).getText());

		s.Imprimir(driver.getCurrentUrl());
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		
		
		
		// ENTRO AL IFRAME 1
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("cp_embed_1")));
		
		// entrar en el iframe 1-1
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mce_0_ifr"));
		// IMPRIMIMOS BODY
		s.Imprimir(driver.findElement(By.id("tinymce")).getText());

		// SALGO DEL IFRAME 1-1
		driver.switchTo().parentFrame();
		// SALGO DEL IFRAME 1
		driver.switchTo().parentFrame();

		// ------------------
		// DEFAULT CONTENT
		// ------------------

		// ENTRO AL IFRAME 1
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		// ENTRO AL IFRAME 1-1
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		// IMPRIMIMOS BODY

		s.Imprimir(driver.findElement(By.id("tinymce")).getText());

		// SALGO DEL IFRAME
		driver.switchTo().defaultContent();

		// ------------------
		// DEFAULT CONTENT
		// ------------------

		// ENTRO AL IFRAME 1
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("cp_embed_1"));

		// ENTRO AL IFRAME 1
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mce_0_ifr"));
		// IMPRIMIMOS BODY

		s.Imprimir(driver.findElement(By.id("tinymce")).getText());

		
		
	}

}
