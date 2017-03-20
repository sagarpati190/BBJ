package pages.staging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Utility;

public class StagingVosp extends Utility {
	WebDriver driver;
	
	By rdoVOSP = By.id("metadatable_metadata_attributes_2_value_true");
	By btnUpdate = By.xpath("//*[@id='edit_metadatable']/div[5]/input");
	
	public StagingVosp(WebDriver driver){
		this.driver = driver;
	}
	
		
	public boolean clickrdoVOSP(){
		try{
			if(existsElement(rdoVOSP, driver)){
				driver.navigate().refresh();
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
