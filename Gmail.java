//package Test.Gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Gmail  {
    public static void main(String[] args) {
    	
    	//System.setProperty("webdriver.firefox.marionette", "C:\\Users\\samkitjain\\Downloads\\geckodriver-v0.16.1-win64\\geckodriver.exe");
    	//driver =new FirefoxDriver();
    	
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\samkitjain\\Downloads\\chromedriver_win32\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
    	
    	WebDriverWait myWait = new WebDriverWait(driver,10) ;
        // And now use this to visit Gmail
        driver.get("https://www.google.com/gmail/about/#");
        
        // Alternatively the same thing can be done like this
        // driver.navigate().to("https://www.google.com/gmail/about/#");

        
        WebElement signin = driver.findElement(By.linkText("SIGN IN"));
        signin.click();
        
        WebElement username = driver.findElement(By.xpath("//*[@id='identifierId']")); 
        //enter username
        username.sendKeys("dummya481@gmail.com");
        username.submit();
        //next 
        driver.findElement(By.xpath("//*[@id='identifierNext']/content")).click();
        
        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        //delay for password to arrive using the visibility of password tag
        myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='password']/div[1]/div/div[1]/input")));

        WebElement password = driver.findElement(By.xpath("//*[@id='password']/div[1]/div/div[1]/input")); 
        password.sendKeys("asdfghjkl@12345");
        password.submit();
        //clicking next 
        driver.findElement(By.xpath("//*[@id='passwordNext']")).click();
        
        myWait.until(ExpectedConditions.titleContains("Inbox"));
        System.out.println("Page title is: " + driver.getTitle());
        //getting initial count
        String inbox = driver.getTitle();
        int count1 =  Integer.parseInt(inbox.substring(inbox.indexOf('(')+1,inbox.indexOf(')')));
       
        System.out.println(count1);
        
        WebElement compose = driver.findElement(By.xpath("//*[(text()='COMPOSE')]"));
        compose.click();
        //wait till the compose dialog box appears
        myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Send']")));
        driver.findElement(By.name("to")).sendKeys("dummya481@gmail.com");
        driver.findElement(By.name("subjectbox")).sendKeys("Samkit - Test Script "); 
        // finding xpath using contains 
        driver.findElement(By.xpath("//*[contains(@class,'Am Al editable LW-avf')]")).sendKeys("First script in java");
        
        driver.findElement(By.xpath("//*[text()='Send']")).click();
       
        //wait until value changed to count +1
        myWait.until(ExpectedConditions.titleContains(Integer.toString(count1+1)));
       
       
        String inbox2 = driver.getTitle();
       
        int count2 =  Integer.parseInt(inbox2.substring(inbox2.indexOf('(')+1,inbox2.indexOf(')')));
       
        System.out.println(inbox2);

        if(count2-count1==1)System.out.println("Successful");
        else System.out.println("Not Successful");
        driver.findElement(By.xpath("//*[contains(@class,'T-I J-J5-Ji nu T-I-ax7 L3')]")).click();
       
        // driver.quit();
    }
}