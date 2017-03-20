package pages.staging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Utility;

public class StagingEpisode extends Utility {
  WebDriver driver;
	
	By rdoVOSP = By.id("metadatable_metadata_attributes_0_value_true");
	By btnUpdate = By.name("commit");
	
	public StagingEpisode(WebDriver driver){
		this.driver = driver;
	}
	
		
	public boolean clickEpisodeSerieslink(String strEpiName){
		try{
			By linkEpisode = By.partialLinkText(strEpiName);
			if(existsElement(linkEpisode, driver)){
				driver.findElement(linkEpisode).click();
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
