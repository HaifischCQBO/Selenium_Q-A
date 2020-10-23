package ex.browserapi;

import java.io.Console;
import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ex.fixtures.Fixtures;
import ex.resources.Colors;
import ex.resources.Selenium;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class demoblaze_Shop extends Fixtures {

	Selenium s = new Selenium(Fixtures.driver);
	Colors c = new Colors();
    @Rule
    public TestWatcher watchman = new TestWatcher() {
    	
        @Override
        protected void failed(Throwable e, Description description) {
        	s.ImprimirColor(c.RED_BOLD_BRIGHT ," PRUEBA FALLIDA: " + description );
        }
        
        @Override
        protected void succeeded(Description description) {

        	s.ImprimirColor(c.GREEN_BOLD_BRIGHT,  " PRUEBA COMPLETADA: " + description );
        	 s.getDriver().quit();
        }
        
        
    };
	
	
	
	@Test
	public void T1_agregarProductos() {
		

		for(int x=0; x<6;x++) {
			s.ObtenerLista(By.xpath("//a[@class='hrefch' and starts-with(@href, 'prod.html')]")).get(x).click();
			s.ImprimirColor(c.PURPLE_BOLD_BRIGHT,"Procedemos a agregar producto al carro:");
			s.ImprimirColor(c.PURPLE_BOLD_BRIGHT,"-----------------");
			s.ImprimirColor(c.PURPLE_BOLD_BRIGHT,s.ObtenerAtributo(By.xpath("//div[@id='tbodyid']/h2"),"innerText"));
			s.ImprimirColor(c.PURPLE_BOLD_BRIGHT, s.ObtenerAtributo(By.xpath("//div[@id='tbodyid']/h3"),"innerText"));
			s.ImprimirColor(c.PURPLE_BOLD_BRIGHT,"-----------------");

			s.Click(By.linkText("Add to cart"));
			
			s.esperarAceptarAlertas();
			
			s.Click(By.xpath("//a[@href='index.html']"));
		}
		
	}
	@Test

	public void T2_enviarCorreoContacto() {
		
		s.Click(By.xpath("//a[@data-target='#exampleModal']"));
		s.Escribir(By.id("recipient-email"), s.ChainAlfa(10)+"@"+s.ChainAlfa(10)+"."+s.ChainAlfa(2));
		s.Escribir(By.id("recipient-name"), s.ChainAlfa(10));
		s.Escribir(By.id("message-text"), s.ChainAlfa(100));
		s.Click(By.xpath("//button[text()='Send message']"));
		
		s.esperarAceptarAlertas();
		

	}
	@Test

	public void T3_signInLogIn() {
		s.Click(By.id("signin2"));
		
		String user = "TestGroup"+ new Random().nextInt(10000);;
		String pass =s.ChainAlfa(20);
		
		s.Escribir(By.id("sign-username"), user);
		s.Escribir(By.id("sign-password"), pass);
		s.Click(By.xpath("//button[text()='Sign up']"));
		
		s.esperarAceptarAlertas();

		s.Click(By.id("login2"));
		
		s.Escribir(By.id("loginusername"), user);
		s.Escribir(By.id("loginpassword"), pass);

		s.Click(By.xpath("//button[text()='Log in']"));

		
		
	}
	@Test
	public void T4_completarCompra() {
		
		T1_agregarProductos();
		s.Click(By.id("cartur"));
		s.Click(By.xpath("//button[text()='Place Order']"));

		s.Escribir(By.id("name"), s.ChainAlfa(10));
		s.Escribir(By.id("country"), s.ChainAlfa(10));
		s.Escribir(By.id("city"), s.ChainAlfa(10));
		s.Escribir(By.id("card"), s.ChainAlfa(10));
		s.Escribir(By.id("month"), s.ChainAlfa(10));
		s.Escribir(By.id("year"), s.ChainAlfa(10));
		
		s.Click(By.xpath("//button[text()='Purchase']"));
		
		
		s.ImprimirColor(c.BLUE_BOLD_BRIGHT, "**********************************");

		s.ImprimirColor(c.BLUE_BOLD_BRIGHT,"INFORMACION DE LA COMPRA");

		s.ImprimirColor(c.BLUE_BOLD_BRIGHT,"**********************************");

		s.ImprimirColor(c.BLUE_BOLD_BRIGHT, s.ObtenerAtributo(By.xpath("//div[starts-with(@class,'sweet-alert')]/h2"),"innerText"));

		s.ImprimirColor(c.BLUE_BOLD_BRIGHT, s.ObtenerAtributo(By.xpath("//div[starts-with(@class,'sweet-alert')]/p"),"innerText"));

		s.ImprimirColor(c.BLUE_BOLD_BRIGHT,"**********************************");
		
		
	}
}
