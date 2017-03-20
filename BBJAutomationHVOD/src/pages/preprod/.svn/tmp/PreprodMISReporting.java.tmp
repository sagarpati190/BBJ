package pages.preprod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Utility;

public class PreprodMISReporting extends Utility {
	WebDriver driver;
	
	By selectRoyaltyCategory = By.id("select2-metadatable_metadata_attributes_2_value-container");
	By txtISRC = By.id("metadatable_metadata_attributes_5_value");
	
	By btnUpdate = By.name("commit");
	
	public PreprodMISReporting(WebDriver driver){
		this.driver = driver;
	}
	
		
	public boolean setRoyaltyCategory(String strCategory){
		try{
			if(existsElement(selectRoyaltyCategory, driver)){
				driver.findElement(selectRoyaltyCategory).click();
				By txtTitlePlacementInput = By.xpath("//*[@id='select2-metadatable_metadata_attributes_2_value-results']//span[contains(text(),'"+strCategory+"')]");
				if(existsElement(txtTitlePlacementInput, driver)){
						driver.findElement(txtTitlePlacementInput).click();
						return true;
				}
				return false;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setISRCValue(String strISRC){
		try{
			if(existsElement(txtISRC, driver)){
				driver.findElement(txtISRC).sendKeys(strISRC);
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
