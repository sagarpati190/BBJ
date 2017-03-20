package pages.staging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Utility;

public class StagingBrandMetadata extends Utility {
	
	WebDriver driver;
	
	
	By txtTitleShort = By.id("metadatable_metadata_attributes_0_value");
	By txtTitleMedium = By.id("metadatable_metadata_attributes_1_value");
	By txtTitleLong = By.id("metadatable_metadata_attributes_2_value");
	
	By btnCommit = By.xpath("//*[@id='edit_metadatable']/div[9]/input");
	
	public StagingBrandMetadata(WebDriver driver){
		this.driver = driver;
	}
	
	
	
	
	
	
	public boolean setBrandShortDesc(String strTitleShort){
		try{
			if(existsElement(txtTitleShort, driver)){
				driver.findElement(txtTitleShort).sendKeys(strTitleShort);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setBrandMediumDesc(String strTitleMedium){
		try{
			if(existsElement(txtTitleMedium, driver)){
				driver.findElement(txtTitleMedium).sendKeys(strTitleMedium);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setBrandLongDesc(String strTitleLong){
		try{
			if(existsElement(txtTitleLong, driver)){
				driver.findElement(txtTitleLong).sendKeys(strTitleLong);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	
	
	public boolean clickSubmit(){
		try{
			if(existsElement(btnCommit, driver)){
				driver.findElement(btnCommit).click();
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	

}
