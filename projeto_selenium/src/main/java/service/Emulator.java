package service;

import java.io.File;
import java.util.List;

import org.apache.xmlbeans.impl.tool.Extension.Param;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import app.Screen;
import model.Parameters;
import tool.SerializebleDeserializable;

public class Emulator {


	ChromeDriver webDriver ;

	DataService service ;
	SerializebleDeserializable serializa = new SerializebleDeserializable() ;

	int page = 1;
	int sequence = 1;
	
	Screen screen;

	public  void start(Screen screen) {
//
//		try{
//			
//			service = new DataService("");	
//
//			System.out.println("Start...");
//
//			webDriver.get("https://login.propstream.com/");
//
//			webDriver.findElement(By.name("username")).sendKeys("pehomebuyers@gmail.com");
//			webDriver.findElement(By.name("password")).sendKeys("Pehb1602!");
//
//			webDriver.findElementByClassName("dark-button").click();
//
//			WebElement linkElement = new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")));
//
//
//			webDriver.findElementByXPath("//input[@type='text']").sendKeys(data_search);
//
//			new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@id='react-autowhatever-1--item-0']")));
//
//			webDriver.findElementByXPath("//input[@type='text']").sendKeys(Keys.ENTER);
//
//
//			new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@title='High Equity']")));
//			webDriver.findElementByXPath("//button[@title='High Equity']").click();
//
//
//			new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='SearchResultsAll']")));
//
//			getData(webDriver);
//
//
//			while (nextPagina(webDriver)) {
//				getData(webDriver);
//			}
//
//
//
//
//			finish(webDriver);
//
//
//			System.out.println("fim.....");
//
//
//
//		}catch( Exception e){
//			Parameters.getInstance().setLog(e.getMessage());
//		}finally {
//			service.gerarPlanilha();
//		}

		//selectOptionWithText(webDriver, "//ul[@role='listbox']");

		//WebElement lista = new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@role='listbox']")));
		//linkElement = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(lista));




		//List<WebElement> allElements = lista.findElements(By.xpath("//li[@role='option']")); 

		// allElements.get(0).click();








	}	    


	private  void finish(ChromeDriver webDriver) {

		webDriver.close();

	}


	private  boolean nextPagina(ChromeDriver webDriver) {

		try{
			List<WebElement> listSpan =  (List<WebElement>) webDriver.findElements(By.xpath("//span[contains(@class, 'text')]"));

			for (WebElement webElement : listSpan) {

				if("Next".equals(webElement.getText())){
					webElement.click();
					System.out.println("Trocou de pagina....");
					return true;
				}
			}
		}catch(Exception e){

		}
		return false;

	}


	public  void getData(ChromeDriver webDriver) throws Exception {

		String adress  = "";
		String owner  = "";
		String pagina  = (page++) + "";
		String sequencia  = (sequence++ )+"";


		WebElement linkDiv  =  new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'wrapper')]")));


		new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@class, 'name')]")));

		List<WebElement> listDado =  (List<WebElement>) linkDiv.findElements(By.xpath("//a[contains(@class, 'name')]"));

		for (WebElement webElement : listDado) {

			String url = webElement.getAttribute("href");


			webElement.click();

			adress  =  new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'headerTitle')]"))).getText();

			while (adress == null || adress.equals("")) {
				adress  =  new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'headerTitle')]"))).getText();
			}



			List<WebElement> listDivValue =  (List<WebElement>) linkDiv.findElements(By.xpath("//div[contains(@class, 'value')]"));


			while (listDivValue.size() < 27) {
				listDivValue =  (List<WebElement>) linkDiv.findElements(By.xpath("//div[contains(@class, 'value')]"));
			}



			owner = listDivValue.get(27).getText();
			if(owner.equals("No")){
				owner = listDivValue.get(26).getText();

			}

			System.out.println(adress + " >  " + owner);

			sequencia  = (sequence++ )+"";

			service.add_Data(adress, owner, pagina, sequencia);


			webDriver.findElementByName("close").click();			



		}	



	}	

	public static void selectOptionWithText(ChromeDriver driver  , String textToSelect) {
		try {
			WebElement autoOptions = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='react-autosuggest__suggestions-list']")));
			FluentWait<WebDriver> wait;
			wait = new WebDriverWait(driver, 5);

			System.out.println(driver.getPageSource());

			//wait.until(ExpectedConditions.visibilityOf(autoOptions));


			List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName("li"));

			for(WebElement option : optionsToSelect){
				System.out.println("Trying to select: "+textToSelect);
				option.click();
				break;
			}

		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println(e.getMessage() + e.getStackTrace());
		}
	}


	public void step_One(Screen screen) {
		
		if(!new File(Parameters.getInstance().getChrome()).exists()){
			Parameters.getInstance().setLog("chromedriver not found");
			return;
		}

		service = new DataService("");	

		System.out.println("Start...");
		
		this.screen  = screen;

		Parameters.getInstance().setLogin(screen.userText.getText());
		Parameters.getInstance().setPassword(screen.passwordText.getText());

		//System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", Parameters.getInstance().getChrome());
		
		webDriver = new ChromeDriver();

		webDriver.get("https://login.propstream.com/");

		webDriver.findElement(By.name("username")).sendKeys(Parameters.getInstance().getLogin());
		webDriver.findElement(By.name("password")).sendKeys(Parameters.getInstance().getPassword());

		webDriver.findElementByClassName("dark-button").click();

		WebElement linkElement = new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")));


	}


	public void step_Two() {

		try{
			new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@title='High Equity']")));
			//webDriver.findElementByXPath("//button[@title='High Equity']").click();


			new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='SearchResultsAll']")));

			getData(webDriver);


			while (nextPagina(webDriver)) {
				getData(webDriver);
			}




			finish(webDriver);


			System.out.println("fim.....");


		}catch( Exception e){
			System.out.println(e.getMessage());
		}finally {
			service.gerarPlanilha();
			serializa.serializar();

		}

	}
}
