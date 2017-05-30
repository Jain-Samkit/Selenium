import java.io.*;
import java.io.FileNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

public class BingTranslate {

	public static WebDriver chromeDriver()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\samkitjain\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	public static void chkInput(WebDriver driver,String input)
	 {
	        WebElement inputBox = driver.findElement(By.cssSelector("textarea.srcTextarea"));
	        String text = inputBox.getAttribute("value");
	        
	        if(text.isEmpty())
	        {
	           System.out.println("Input field is empty");
	        }  
	        else
	        {
	         System.out.println("Input field is not empty");
	        }
	        
	        if(text.equals(input)){
	        	System.out.println("Input Fields match");
	        }
	        else 
	        {
	        	System.out.println("Error! Different Input field");
	        }
	 }
	
	public static void chkOutput(WebDriver driver,String result)
	 { 
			WebElement outputBox = driver.findElement(By.cssSelector("div#destText"));
	        String text = outputBox.getText();
	        
	        if(text.equals(result))
	        {
	           System.out.println("Test Result is same");
	        }  
	        else
	        {
	         System.out.println("Error! Wrong Test Result");
	        }
	 }
	
	
	public static void googleSearch(WebDriver driver,YamlReader read)
	
	{ 	  driver.get(read.getUrl());
			WebElement element = driver.findElement(By.cssSelector("input[name='q']"));
			element.sendKeys(read.getSearchKey());
			if(driver.getTitle().equals(read.getPageTitles().get(0).get("title1")))
				{
					System.out.println("Title 1 successful-"+driver.getTitle());
				}
			else
				{
					System.out.println("Error! Title one is wrong");
				}
			element.submit();
    	
			new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.linkText(read.getSearchKey())));
		
	}

	public static void bingTranslator(WebDriver driver, YamlReader read)
	{
		if(driver.getTitle().equals(read.getPageTitles().get(1).get("title2")))
    	{
    		System.out.println("Title 2 successful-"+driver.getTitle());
    	}
    	else
    	{
    		System.out.println("Error! Title 2 is wrong");
    	}	
     driver.findElement(By.linkText("Bing Translator")).click();
     new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'srcTextarea')]")));
	}
	
	public static void inputText(WebDriver driver, YamlReader read)
	{
		if(driver.getTitle().equals(read.getPageTitles().get(2).get("title3")))
    	{
    		System.out.println("Title 3 successful-"+driver.getTitle());
    	}
    	else
    	{
    		System.out.println("Error! Title 3 is wrong");
    	}	
		String s = read.getTestData();
		driver.findElement(By.xpath("//*[contains(@class,'srcTextarea')]")).sendKeys(s);
		chkInput(driver,s);
	}
	
	public static void selectLanguage(WebDriver driver,YamlReader read) throws InterruptedException
	
	{
		driver.findElement(By.cssSelector("div.col.translationContainer.destinationText div.LS_Header")).click();
        driver.findElement(By.cssSelector("div.col.translationContainer.destinationText td[value='"+read.getLanguage()+"'] ")).click();
        Thread.sleep(1000);
        chkOutput(driver,read.getTestResult());
	}
	
	
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		
	    InputStream input = new FileInputStream(new File("src/Translate.yml"));
	    Yaml yaml = new Yaml();
	    YamlReader read = yaml.loadAs(input, YamlReader.class);
	      
	    WebDriver d = chromeDriver();
    	googleSearch(d, read);
    	bingTranslator(d,read);
    	inputText(d,read);
    	selectLanguage(d,read);
    	     
    
    	
	}

}
