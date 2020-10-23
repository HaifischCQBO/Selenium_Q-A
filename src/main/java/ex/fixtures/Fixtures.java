package ex.fixtures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ex.resources.Selenium;

public class Fixtures  {

	
	protected static WebDriver driver;
	static Selenium sel = new Selenium();
	@BeforeClass
	public static void antesDeTodo() {
		
		System.out.print("Fixture BeforeClass que se ejecuta antes de todo");
		driver =sel.getDriver();
	}
	
		@Before
		public  void antesDeCadaPrueba() {
			sel.Imprimir("Fixture Before que se ejecuta antes de cada Prueba");
			sel.IniciarDriver();
			sel.AbrirURL("https://www.demoblaze.com/index.html");
			// inicializamos el driver antes de cada prueba
			
			
		}
	
		/*@After
		public void despuesDeCadaPrueba() {
			sel.Imprimir("Fixture After que se ejecuta despues de cada Prueba");
	
		}*/

	@AfterClass
	public static void despuesDeTodo() {
		System.out.print("Fixture AfterClass que se ejecuta despues de todo");
	}
	 private static String watchedLog;

	

}
