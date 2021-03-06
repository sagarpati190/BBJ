package pages.staging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Utility;

public class StagingGroup extends Utility {
	WebDriver driver;
	
	By byName = By.id("metadatable_metadata_attributes_0_value");
	By byOriginalName = By.id("metadatable_metadata_attributes_2_value");
	By byShortDesc = By.id("metadatable_metadata_attributes_3_value");
	By byMediumDesc = By.id("metadatable_metadata_attributes_4_value");
	By byLongDesc = By.id("metadatable_metadata_attributes_5_value");
	By bySessionNumber = By.id("metadatable_metadata_attributes_6_value");
	By byRollingFlag = By.id("metadatable_metadata_attributes_12_value_true");
	
	
	By btnUpdate = By.xpath("//*[@id='edit_metadatable']/div[22]/input");
	
	public StagingGroup(WebDriver driver){
		this.driver = driver;
	}
	
		
	public boolean setName(String strName){
		try{
			if(existsElement(byName, driver)){
				driver.findElement(byName).sendKeys(strName);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	
	
	public boolean setOriginalName(String strOriginalName){
		try{
			if(existsElement(byOriginalName, driver)){
				driver.findElement(byOriginalName).sendKeys(strOriginalName);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setShortDesc(String strShortDesc){
		try{
			if(existsElement(byShortDesc, driver)){
				driver.findElement(byShortDesc).sendKeys(strShortDesc);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setMediumDesc(String strMediumDesc){
		try{
			if(existsElement(byMediumDesc, driver)){
				driver.findElement(byMediumDesc).sendKeys(strMediumDesc);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setLongDesc(String strLongDesc){
		try{
			if(existsElement(byLongDesc, driver)){
				driver.findElement(byLongDesc).sendKeys(strLongDesc);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setSessionNumber(String strSessionNumber){
		try{
			if(existsElement(bySessionNumber, driver)){
				driver.findElement(bySessionNumber).sendKeys(strSessionNumber);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setRollingFlag(){
		try{
			if(existsElement(byRollingFlag, driver)){
				driver.findElement(byRollingFlag).click();
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
