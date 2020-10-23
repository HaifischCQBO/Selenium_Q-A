package ex.resources;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium {

	// WebDriver
	private static WebDriver d;
	WebElement Elemento;
	Select Seleccion;
	WebDriverWait wait;

	public Selenium() {

	}

	public Selenium(WebDriver dr) {
		Selenium.d = dr;
	}

	public void IniciarDriver() {
		// Establecemos los argumentos de ejecucion para el chrome driver
		ChromeOptions Opciones = new ChromeOptions();

		// Opcion Headless
		// Opciones.addArguments("--headless");

		// Set Resolucion
		// Opciones.addArguments("--window-size=1080,1920");

		// Establecemos la propiedad del sistema para establecer la ubicacion del driver
		System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver_86.exe");

		// Inicializamos el driver
		d = new ChromeDriver(Opciones);
		Selenium.d = d;
		// Maximizarventana
		d.manage().window().maximize();

		// implementamos una espera implicita para los elementos no cubiertos con
		// esperas explicitas
		d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.wait = new WebDriverWait(d, 30);

	}

	public void CerrarDriver() {

		// cerramos el driver de selenium
		d.close();

	}

	public static WebDriver getDriver() {
		return d;
	}

	public void AbrirURL(String url) {
		Imprimir("Abrimos la url: " + url);
		d.get(url);
	}

	// ************************ ACCIONES DE CLICK *******************************
	public void Click(String Ruta) {
		this.wait = new WebDriverWait(d, 30);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Ruta))).click();
		Imprimir("Hacemos Click en el elemento " + Ruta);

		Esperar(500);

	}

	public void Click(By by) {

		this.wait = new WebDriverWait(d, 30);

		wait.until(ExpectedConditions.elementToBeClickable(by)).click();
		Imprimir("Hacemos Click en el elemento " + by.toString());

		Esperar(500);

	}

	public void Click(WebElement elemento) {

		Imprimir("Hacemos Click en el elemento " + elemento.toString());

		elemento.click();
		Esperar(500);

	}

	// ************************ ACCIONES DE CLICK *******************************

	// ************************ ACCIONES DE ESCRIBIR(sendKeys)
	// *******************************

	public void Escribir(String Ruta, String Texto) {

		this.wait = new WebDriverWait(d, 30);

		Elemento = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Ruta)));

		Imprimir("Ingresamos el Texto: " + Texto + " en el elemento: " + Ruta);

		Click(Elemento);

		Limpiar(Elemento);

		Elemento.sendKeys(Texto);

	}

	public void Escribir(By by, String Texto) {

		this.wait = new WebDriverWait(d, 30);

		Elemento = wait.until(ExpectedConditions.elementToBeClickable(by));

		Imprimir("Ingresamos el Texto: " + Texto + " en el elemento: " + by);

		Click(Elemento);

		Limpiar(Elemento);

		Elemento.sendKeys(Texto);
	}

	public void Escribir(WebElement elemento, String Texto) {

		Imprimir("Ingresamos el Texto: " + Texto + " en el elemento: " + elemento);

		Click(Elemento);

		Limpiar(Elemento);

		Elemento.sendKeys(Texto);
	}

	// ************************ ACCIONES DE ESCRIBIR(sendKeys)
	// *******************************

	// ************************ ACCIONES DE EnviarTeclas(sendKeys)
	// *******************************

	public void EnviarKeys(WebElement elemento, Keys tecla) {

		Imprimir("Ingresamos la tecla : " + tecla + " en el elemento: " + elemento);

		Elemento.sendKeys(tecla);
	}

	public void EnviarKeys(By by, Keys tecla) {

	}

	public void EnviarKeys(String Ruta, Keys tecla) {

	}

	// ************************ ACCIONES DE EnviarTeclas(sendKeys)
	// *******************************

	public void esperarAceptarAlertas() {

		wait.until(ExpectedConditions.alertIsPresent());
		Alert a = d.switchTo().alert();
		Imprimir("Aceptamos la Alerta con el texto" + a.getText());
		;
		a.accept();

	}

	public void Limpiar(WebElement elemento) {
		elemento.clear();

	}

	public void Limpiar(String Ruta) {

	}

	public void Limpiar(By by) {

	}

	public WebElement EncontrarElemento(By by) {

		Elemento = d.findElement(by);

		return Elemento;

	}

	// ************************ IMPRESION EN COLORES
	//

	public void ImprimirColor(String color, String texto) {
		Colors c = new Colors();
		System.out.println(color + texto + c.RESET);

	}

	public void Imprimir(String texto) {
		System.out.println(texto);

	}

	// ************************ IMPRESION EN COLORES
	//
	public void Esperar(int Milisegundos) {

		try {
			Thread.sleep(Milisegundos);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ImprimirLista(By by) {

		Seleccion = new Select(d.findElement(by));
		List<WebElement> listado = Seleccion.getOptions();

		for (int x = 0; x < listado.size(); x++) {

			Imprimir(listado.get(x).getText().toString());
		}

	}

	public List<WebElement> ObtenerLista(By by) {
		this.wait = new WebDriverWait(d, 30);

		Imprimir("Generamos una espera dinamica para el elemento" + by.toString());
		List<WebElement> listado = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		Imprimir("Se encuentran N~:" + listado.size() + " Elementos.");
		return listado;

	}

	public WebElement obtenerElemento(By by) {
		Imprimir("Generamos una espera dinamica para el elemento" + by.toString());
		Elemento = wait.until(ExpectedConditions.presenceOfElementLocated(by));

		return Elemento;

	}

	public void SeleccionarElementoByIndex(By by, int opcion) {

	}

	public void SeleccionarElementoByIndex(String ruta, int opcion) {

	}

	public void SeleccionarElementoByIndex(WebElement elemento, int opcion) {

	}

	public void SeleccionarElementoByValue(By by, String texto) {

	}

	public void SeleccionarElementoByValue(String ruta, String texto) {

	}

	public void SeleccionarElementoByValue(WebElement elemento, String texto) {

	}

	public void SeleccionarElementoByTexto(By by, String texto) {

		Seleccion = new Select(d.findElement(by));
		Seleccion.selectByVisibleText(texto);

	}

	public void SeleccionarElementoByTexto(String ruta, String texto) {

		Seleccion = new Select(d.findElement(By.xpath(ruta)));
		Seleccion.selectByVisibleText(texto);
	}

	public void SeleccionarElementoByTexto(WebElement elemento, String texto) {

		Seleccion = new Select(elemento);
		Seleccion.selectByVisibleText(texto);
	}

	public String ObtenerAtributo(By by, String atributo) {
		String Atributo = "";
		Atributo = d.findElement(by).getAttribute(atributo);

		return Atributo;
	}

	public String ObtenerAtributo(String ruta, String atributo) {
		String Atributo = "";
		Atributo = d.findElement(By.xpath(ruta)).getAttribute(atributo);
		return Atributo;
	}

	public String ObtenerAtributo(WebElement elemento, String atributo) {
		String Atributo = "";
		Atributo = elemento.getAttribute(atributo);
		return Atributo;
	}

	public void printElementInfo(WebElement elementoBuscado) {

		// METODO PARA IMRRIMIR DIFERNTES TIPOS DE ATRIBUTOS DE UN WEB ELEMENT DADO
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
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void takeSnapShot(WebDriver driver) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// Convertir driver a TakeScreenShot

		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		// Llamar al metodo getScreenshotAs para crear el archivo de la imagen

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Movemos la Imagen al nuevo destino

		File DestFile = new File("src/capturas/SEL_Evidencia" + timestamp.getTime() + ".png");

		// Copiamos el archivo al destino

		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String Chain(int largo) {

		byte[] array = new byte[largo]; // length is bounded by 7
		new Random().nextBytes(array);
		String generatedString = new String(array, Charset.forName("UTF-8"));

		return generatedString;
	}

	public String ChainAlfa(int largo) {
		 int leftLimit = 48; // numeral '0'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 10;
		    Random random = new Random();
		 
		    String generatedString = random.ints(leftLimit, rightLimit + 1)
		      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		      .limit(targetStringLength)
		      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		      .toString();
		 
;
		return generatedString;
	}

}
