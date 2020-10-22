package ex.fixtures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ex.resources.Selenium;

public class Fixtures {

	protected WebDriver driver;
	static Selenium s = new Selenium();

	@BeforeClass
	public static void antesDeTodo() {
		s.Imprimir("Fixture BeforeClass que se ejecuta antes de todo");

	}
	
		@Before
		public void antesDeCadaPrueba() {
			s.Imprimir("Fixture Before que se ejecuta antes de cada Prueba");
	
			// inicializamos el driver antes de cada prueba
			this.driver = s.InicializarChromeDriver();
			
			this.driver.get("https://vins-udemy.s3.amazonaws.com/docker/docker-book-flight.html");
		}
	
		@After
		public void despuesDeCadaPrueba() {
			s.Imprimir("Fixture After que se ejecuta despues de cada Prueba");
	
			this.driver.quit();
		}

	@AfterClass
	public static void despuesDeTodo() {

		s.Imprimir("Fixture AfterClass que se ejecuta despues de todo");
	}
}
