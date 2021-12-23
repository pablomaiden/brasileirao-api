package br.com.phc.brasileiraoapi;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SituacaoCadastral {
		
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;
	
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/java/chromedriver.exe");		
		driver = new ChromeDriver();
		js     = (JavascriptExecutor) driver;
		vars   = new HashMap<String, Object>();
	}
	
	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void login() {
		try {
			driver.get("https://www.situacao-cadastral.com");
			Thread.sleep(1000);
			driver.manage().window().setSize(new Dimension(1382, 744));
			Thread.sleep(1000);
			driver.findElement(By.id("doc")).sendKeys("91966078153");			
			Thread.sleep(5000);
			driver.findElement(By.id("consultar")).click();
			Thread.sleep(5000);			
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}

}
