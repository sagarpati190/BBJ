package pages.preprod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Utility;

public class PreprodVosp extends Utility {
	WebDriver driver;
	
	By rdoVOSP = By.id("metadatable_metadata_attributes_0_value_true");
	By btnUpdate = By.name("commit");
	
	public PreprodVosp(WebDriver driver){
		this.driver = driver;
	}
	
		
	public boolean clickrdoVOSP(){
		try{
			if(existsElement(rdoVOSP, driver)){
				driver.findElement(rdoVOSP).click();
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	public boolean clickUpdateButton(){
		try{
			if(existsElement(btnUpdate, driver)){
				driver.findElement(btnUpdate).click();
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
}
