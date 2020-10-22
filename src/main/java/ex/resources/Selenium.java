package ex.resources;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium {

	private WebDriver driver;
	private WebElement elemento;
	

	public WebDriver InicializarGeckoDriver() {
		
		//configuramos las propiedades del sistema respecto a la ubicacion del driver

		System.setProperty("webdriver.gecko.driver", "src/drivers/geckodriver.exe");

		// INICIALIZAR EL OBJETO WEBDRIVER
		this.driver = new ChromeDriver();

		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return this.driver;
	}

	public WebDriver InicializarChromeDriver() {

		//configuramos las propiedades del sistema respecto a la ubicacion del driver
		System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver_86.exe");
		
		//Establecemos los argumentos de ejecucion para el chrome driver
		ChromeOptions Opciones = new ChromeOptions();

		//Opcion Headless
		//Opciones.addArguments("--headless");
		
		//Set Resolucion
		Opciones.addArguments("--window-size=1080,1920");
		// INICIALIZAR EL OBJETO WEBDRIVER
		this.driver = new ChromeDriver(Opciones);

		driver.manage().window().maximize();
		return this.driver;
	}

	public void ClickBy(By by, WebDriver driver) {
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		wait.until(ExpectedConditions.elementToBeClickable(by));
		
		elemento = driver.findElement(by);
		
		elemento.click();
		
	}
		
	public void printElementInfo(WebElement elementoBuscado) {

		//METODO PARA IMRRIMIR DIFERNTES TIPOS DE ATRIBUTOS DE UN WEB ELEMENT DADO
		Imprimir("Atributo maxlength: " + elementoBuscado.getAttribute("maxlength"));
		Imprimir("Atributo Class: " + elementoBuscado.getAttribute("class"));
		Imprimir("Atributo id: " + elementoBuscado.getAttribute("id"));
		Imprimir("Atributo name: " + elementoBuscado.getAttribute("name"));
		Imprimir("Atributo value: " + elementoBuscado.getAttribute("value"));

		Imprimir("getTagname: " + elementoBuscado.getTagName());
		Imprimir("getText: " + elementoBuscado.getText());
		Imprimir("isDisplayed: " + elementoBuscado.isDisplayed());
		Imprimir("isEnabled: " + elementoBuscado.isEnabled());
		Imprimir("isSelected: " + elementoBuscado.isSelected());

	}
	
	public void Pausa(int segundos) {
		
		try {
			Thread.sleep(segundos*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Imprimir(String texto) {
		//IMPRIMIMOS UN STRING
		
		System.out.println(texto);
	}

}
