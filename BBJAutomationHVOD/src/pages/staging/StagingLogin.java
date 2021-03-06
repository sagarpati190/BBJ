package pages.staging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

import utility.Utility;

public class StagingLogin extends Utility {
	WebDriver driver;
	
	
	By txtUsername = By.id("user_login");
	By txtPassword = By.id("user_password");
	By btnSubmit = By.className("button-submit");
	
	public StagingLogin(WebDriver driver){
		this.driver = driver;
	}
	
	public boolean navigatetoStaging(){
		try{
			driver.get("https://staging-movida.bebanjo.net/schedule");
			return true;
		}catch(Exception e){
			return false;
		}	
		
	}
	
	public boolean setUsername(String username){
		try{
			if(existsElement(txtUsername, driver)){
				driver.findElement(txtUsername).sendKeys(username);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}		
		
	}
	
	public boolean setPassword(String password){
		try{
			if(existsElement(txtPassword, driver)){
				driver.findElement(txtPassword).sendKeys(password);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean clickSubmit(){
		try{
			if(existsElement(txtPassword, driver)){
				driver.findElement(btnSubmit).click();
				return true;
			}
		return false;
	}catch(Exception e){
		return false;
	}
	}
}
