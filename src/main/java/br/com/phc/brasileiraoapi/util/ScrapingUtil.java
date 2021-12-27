package br.com.phc.brasileiraoapi.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import br.com.phc.brasileiraoapi.dto.PartidaGoogleDTO;

@Service
public class ScrapingUtil {
		
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingUtil.class);
	private static final String BASE_URL_GOOGLE="http://google.com.br/search?q=";
	private static final String COMPLEMENTO_GOOGLE="&hl=pt-BR";
	
	private static final String TEMPO_PARTIDA="span[class=liveresults-sports-immersive__game-minute]";
	private static final String NOME_TIME_CASA="div[class=imso_mh__first-tn-ed imso_mh__tnal-cont imso-tnol]";
	private static final String NOME_TIME_VISITANTE="div[class=imso_mh__second-tn-ed imso_mh__tnal-cont imso-tnol]";
		
	static String username = "----username here------"; //blocked out here for obvious reasons
	static String password = "----password here------";
	static String loginUrl = "https://parents.mtsd.k12.nj.us/genesis/parents/j_security_check";
	static String userDataUrl = "https://parents.mtsd.k12.nj.us/genesis/parents?module=gradebook";
	
	private static WebDriver driver;
	private static Map<String, Object> vars;
	private static JavascriptExecutor js;
	
	public void teste() {		
		System.setProperty("webdriver.chrome.driver", "src/test/java/chromedriver.exe");		
		driver = new ChromeDriver();
		js     = (JavascriptExecutor) driver;
		vars   = new HashMap<String, Object>();	
				
		try {
			driver.get("https://www.situacao-cadastral.com");						
			List<String> cpfs = new ArrayList<String>(); 
			
			//cpfs.add("91966078153");	
			//cpfs.add("32785950410");
			//cpfs.add("29322693487");
			cpfs.add("67046070482");
			
			for(String cpf : cpfs) {				
				if(waitForPageLoad()) {
					
					if(isElementPresent(By.id("doc"))) {
					   driver.findElement(By.id("doc")).sendKeys(cpf);
					   driver.findElement(By.id("consultar")).click();						
					}				   				
				}
				
				if(waitForPageLoad()) { 					
					if(isElementPresent(By.className("vrd"))) {					   
					   WebElement resultado = driver.findElement(By.className("vrd"));
					   String resultado_    = resultado.getText();
					   System.out.println(resultado_);				   
						   }			   				   				   				
				      }				
					
				if(waitForPageLoad()) {
					if(isElementPresent(By.id("btnVoltar"))) {
						driver.findElement(By.id("btnVoltar")).click();						
					}
				}				   				
			}
			
			driver.close();
			driver.quit();
			
		} catch (Exception e) {			
			e.printStackTrace();
		}				
	}
	
	private boolean isElementPresent(By by){
        try{
            driver.findElement(by);
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }
	
	public Boolean waitForPageLoad() {
	    Wait<WebDriver> wait = new WebDriverWait(driver, 30);
	    Boolean retorno = wait.until(new Function<WebDriver, Boolean>() {
	        public Boolean apply(WebDriver driver) {
	            System.out.println("Current Window State : " + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
	            return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")).equals("complete");
	        }
	    });
	    return retorno;
	}
	  
	public static void checkElement(String name, Element elem) {
	      if (elem == null) {
	          throw new RuntimeException("Unable to find " + name);
	    }
	}	 
	
	public String montaUrlGoogle(String nomeEquipeCasa, String nomeEquipeVisitante) {
		try {
			String equipeCasa = nomeEquipeCasa.replace(" ", "+").replace("-", "+");
			String equipeVisitante = nomeEquipeVisitante.replace(" ", "+").replace("-", "+");

			return BASE_URL_GOOGLE + equipeCasa + "+x+" + equipeVisitante + COMPLEMENTO_GOOGLE;
		} catch (Exception e) {
			LOGGER.error("ERRO: {}", e.getMessage());
		}
		return null;
	}
	
	public PartidaGoogleDTO partidaGoogle(String url) {
		PartidaGoogleDTO dto = new PartidaGoogleDTO();		
		Document document = null;		
		try {
			document = Jsoup.connect(url).get();
			String title = document.title();
			LOGGER.info("Titulo da Página: [{}]",title);	
						
			StatusPartidasEnum statusPartida = obterStatusPartida(document);
			LOGGER.info("Status Partida: {}",statusPartida.name());	
			dto.setStatusPartida(statusPartida);
			
			if(statusPartida!= StatusPartidasEnum.PARTIDA_NAO_INICIADA) {
			   String tempoPartida = tempoPartida(document);
			   LOGGER.info(tempoPartida);	
			   dto.setTempoPartida(tempoPartida);
			  			   
			   String placarTimeCasa = obterPlacarTimeCasa(document);
			   LOGGER.info(placarTimeCasa);
			   dto.setGolsEquipeCasa(placarTimeCasa);
				
			   String placarTimeVisitante = obterPlacarTimeVisitante(document);
			   LOGGER.info(placarTimeVisitante);
			   dto.setGolsEquipeVisitante(placarTimeVisitante);			   
			}			
			
			String nomeEquipeCasa = obterNomeTime(document,NOME_TIME_CASA);
			LOGGER.info(nomeEquipeCasa);
			dto.setNomeEquipeCasa(nomeEquipeCasa);
			
			String nomeEquipeVisitante = obterNomeTime(document,NOME_TIME_VISITANTE);
			LOGGER.info(nomeEquipeVisitante);
			dto.setNomeEquipeVisitante(nomeEquipeVisitante);
			
			String urlLogoEquipeCasa = obterLogoTimeCasa(document);
			LOGGER.info(urlLogoEquipeCasa);
			dto.setUrlLogoEquipeCasa(urlLogoEquipeCasa);
			
			String urlLogoEquipeVisitante = obterLogoTimeVisitante(document);
			LOGGER.info(urlLogoEquipeVisitante);
			dto.setUrlLogoEquipeVisitante(urlLogoEquipeVisitante);
			
			LOGGER.info("Saída do Objeto DTO:{}",dto);
			
			
		} catch (IOException e) {
			LOGGER.error("Error ao tentar conectar no Google Jsoup -> {}",e.getMessage());
		}		
		return dto;		
	}
	
	public static StatusPartidasEnum obterStatusPartida(Document document) {		
		StatusPartidasEnum statusPartida = StatusPartidasEnum.PARTIDA_NAO_INICIADA;		
		boolean isPartidaIniciada;
		
		isPartidaIniciada=document.select("div[class=imso_mh__lv-m-stts-cont]").isEmpty();	
		if(!isPartidaIniciada) {
		   //tempoPartida = document.select("div[class=imso_mh__lv-m-stts-cont]").first().text();	
		   statusPartida=StatusPartidasEnum.PARTIDA_EM_ANDAMENTO;	   		   
		}		
		
		isPartidaIniciada = document.select("span[class=imso_mh__ft-mtch imso-medium-font imso_mh__ft-mtchc]").isEmpty();		
		if(!isPartidaIniciada) {
		   //tempoPartida = document.select("span[class=imso_mh__ft-mtch imso-medium-font imso_mh__ft-mtchc]").first().text();
		   statusPartida=StatusPartidasEnum.PARTIDA_ENCERRADA;
		}		
		return statusPartida;		
	}
	
	
	public static String tempoPartida(Document document) {
		boolean isTempoPartida = document.select(TEMPO_PARTIDA).isEmpty();
		String tempoPartida="00";
		
		if(!isTempoPartida) {
		   tempoPartida= document.select(TEMPO_PARTIDA).first().text();			
		}		
		return tempoPartida;		
	}
	
	private String obterNomeTime(Document document, String nomeTime) {		
		Element element = document.selectFirst(nomeTime);
		String nomeTime_ = element.select("span").text();		
		return nomeTime_;		
	}
	
	public static String obterLogoTimeCasa(Document document) {		
		Element element = document.selectFirst("div[class=imso_mh__first-tn-ed imso_mh__tnal-cont imso-tnol]");
		String logoEquipeCasa = element.select("img[class=imso_btl__mh-logo]").attr("src");	
		return logoEquipeCasa;
	}
	
	public static String obterLogoTimeVisitante(Document document) {		
		Element element = document.selectFirst("div[class=imso_mh__second-tn-ed imso_mh__tnal-cont imso-tnol]");
		String logoEquipeVisitante = element.select("img[class=imso_btl__mh-logo]").attr("src");		
		return logoEquipeVisitante;
	}
	
	public static String obterPlacarTimeCasa(Document document) {		
		return document.selectFirst("div[class=imso_mh__l-tm-sc imso_mh__scr-it imso-light-font]").text();	
	}
	
	public static String obterPlacarTimeVisitante(Document document) {		
		return document.selectFirst("div[class=imso_mh__r-tm-sc imso_mh__scr-it imso-light-font]").text();		
	}	
	

}
