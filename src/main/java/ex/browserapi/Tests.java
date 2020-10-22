package ex.browserapi;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import ex.fixtures.Fixtures;
import ex.resources.Selenium;

public class Tests extends Fixtures {

	
	private WebElement elemento;

	
	public void PruebaJunit() { // DECLARAR VARIABLE DE SISTEMA
		//SE CREA UN OBJETO SELENIUM, QUE CONTIENE TODAS LAS UTILIDADES QUE NECESITAMOS
		Selenium s= new Selenium();
		
		//ejemplo de como se realiza un refresh/F5/Actualizacion a la pagina en cual nos encontramos
		driver.navigate().refresh();
		//funcion para obtener el titulo de la pagina actual
		s.Imprimir(driver.getTitle());
		//funcion para obtener la actual URL de la pagina donde estamos
		System.out.println(driver.getCurrentUrl());
		//funcion para obtener el codigo fuente de la pagina
		//System.out.println(driver.getPageSource());
		
		//Busqueda y almacenamiento de elemento webdriver dentro de una variable para ser trabajada
		s.ClickBy(By.name("q"), driver);
		
		//llamada de objeto selenium, a su metodo printElementInfo()
		s.printElementInfo(elemento);
		
		//Ejemplos de Asserts de JUnit
		//assertEquals(driver.getTitle(), "Google", "El mensaje de la pagina no es el correcto: ");
		//Assert.assertNotEquals(driver.getTitle(), "Google", "El mensaje de la pagina no es diferente: ");

	}
	@Test
	public void PasajeAvion() { // DECLARAR VARIABLE DE SISTEMA
		
		//SE CREA UN OBJETO SELENIUM, QUE CONTIENE TODAS LAS UTILIDADES QUE NECESITAMOS
		Selenium s= new Selenium();
		
		List<WebElement> campos_rellenables = driver.findElements(By.tagName("input"));
		
		s.Imprimir("Se encontraron :" +String.valueOf(campos_rellenables.size())+" Correspondiente a el tag input");

		//BASICA
		for(int x=0; x < campos_rellenables.size();x++) {
			
			if(campos_rellenables.get(x).isDisplayed()) {
			s.Imprimir("--------------------------------------------------------");
			
			campos_rellenables.get(x).sendKeys("ok"+x);
			}
		}
		
		
		
		//PRO
		for(WebElement lista : campos_rellenables){
			
			if(lista.isDisplayed()) {
				s.Imprimir("--------------------------------------------------------");
				
				
				
				s.printElementInfo(lista);
				}
			
			
		}
		
		//MEJOR METODO
		Select lista_paises = new Select(driver.findElement(By.name("country")));
		
		Random r= new Random();
		
		s.Imprimir(String.valueOf(lista_paises.getOptions().size()));
		
		
		lista_paises.selectByIndex(r.nextInt(100));
		//s.Pausa(1);
		lista_paises.selectByValue("ANGOLA");
		//s.Pausa(1);
		lista_paises.selectByVisibleText("ANTARCTICA");
		//s.Pausa(1);
		
		//METODO RECOMENDADO
		//WebElement lista_paises = driver.findElement(By.name("country"));
		//lista_paises.sendKeys("CHILE");
		
		String Ventana_pasajes= driver.getWindowHandle();
		s.Imprimir(Ventana_pasajes);

		
		//javascript executor
		elemento = driver.findElement(By.name("register"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open(arguments[0], '_blank');", "https://www.google.com");
		
		for(String Ventana : driver.getWindowHandles()) {
			
			driver.switchTo().window(Ventana.toString());
		}
		
		s.Imprimir(driver.getTitle());
		s.Imprimir(driver.getWindowHandle());
		
		driver.close();
		
		driver.switchTo().window(Ventana_pasajes);
		
		s.Imprimir(driver.getTitle());
		
		
		js.executeScript("arguments[0].click();", elemento);
		s.Pausa(5);
		
		
		s.Imprimir(driver.getWindowHandle());
		
		
		
	}
}
