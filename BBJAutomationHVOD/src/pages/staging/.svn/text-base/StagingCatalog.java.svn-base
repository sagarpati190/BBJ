package pages.staging;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Utility;

public class StagingCatalog extends Utility {
	WebDriver driver;
	
	By txtSearchCatalog = By.id("q");
	By btnCreateImport = By.className("dropdown-button");
	By linkContentType =  By.className("dropdown-widget");
	By searchresult = By.className("catalog-top-entry-title");
	
	public StagingCatalog(WebDriver driver){
		this.driver = driver;
	}
	
	public boolean navigatetoCatalog(){
		try{
				driver.get("https://staging-movida.bebanjo.net/catalog");
				return true;
			}catch(Exception e){
			return false;
		}	
		
	}
	
	public boolean setSerchText(String query){
		try{
			if(existsElement(txtSearchCatalog, driver)){
				driver.findElement(txtSearchCatalog).sendKeys(query);
				driver.findElement(txtSearchCatalog).submit();
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean clickCreateImport(){
		try{
			if(existsElement(btnCreateImport, driver)){
				driver.findElement(btnCreateImport).click();
				return true;
			}
		return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean selectContentType(String strcontentType){
		try{
			if(existsElement(linkContentType, driver)){
				List<WebElement> contentTypes = driver.findElements(linkContentType);
				for(WebElement contentType:contentTypes){
					if(contentType.getText().contains(strcontentType)){
						contentType.click();
						return true;
					}
				}
			}
		return false;
		}catch(Exception e){
			return false;
		}
	}
	
	
	public boolean clickSearchedContent(String strContentName) {
		try {
			if(existsElement(searchresult,driver)) {
				WebElement contentName = driver.findElement(searchresult);
				if(contentName.getText().equalsIgnoreCase(strContentName)) {
					contentName.click();
					return true;
				} 
			}
			return false;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	
}
