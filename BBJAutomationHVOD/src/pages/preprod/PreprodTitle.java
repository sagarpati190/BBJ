package pages.preprod;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import utility.Utility;

public class PreprodTitle extends Utility {

	
	WebDriver driver;
	
	By selectTitleType = By.xpath("//*[@id='select2-metadatable_metadata_attributes_0_value-container']");
	By txtTitleName = By.id("metadatable_metadata_attributes_1_value");
	By txtTitleShort = By.id("metadatable_metadata_attributes_2_value");
	By txtTitleMedium = By.id("metadatable_metadata_attributes_3_value");
	By txtTitleLong = By.id("metadatable_metadata_attributes_4_value");
	By selectTitleCategory = By.xpath("//*[@id='select2-metadatable_metadata_attributes_5_value-container']");
		
	By selectTitleSubgeneres = By.xpath("//*[@id='select2-metadatable_metadata_attributes_6_value-container']");
	
	By selectTitlePlacement = By.xpath("//*[@id='select2-metadatable_metadata_attributes_7_value-container']");
	By txtTitleYear = By.id("metadatable_metadata_attributes_8_value");
	By txtTitleArtistName = By.id("metadatable_metadata_attributes_13_value");
	By txtTitleLinkedID = By.id("metadatable_metadata_attributes_20_value");
	
	By btnCommit = By.name("commit");
	
	public PreprodTitle(WebDriver driver){
		this.driver = driver;
	}
	
	
	public boolean setTitleType(String strTitleType){
		try{
			if(existsElement(selectTitleType, driver)){
				driver.findElement(selectTitleType).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath(".//*[@id='select2-metadatable_metadata_attributes_0_value-results']//span[contains(text(),'"+strTitleType+"')]")).click();
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setTitleName(String strTitleName){
		try{
			if(existsElement(txtTitleName, driver)){
				driver.findElement(txtTitleName).sendKeys(strTitleName);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setTitleShortDesc(String strTitleShort){
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
	
	public boolean setTitleMediumDesc(String strTitleMedium){
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
	
	public boolean setTitleLongDesc(String strTitleLong){
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
	
	public boolean setTitleCategory(String strTitleCategory){
		try{
			if(existsElement(selectTitleCategory, driver)){
				driver.findElement(selectTitleCategory).click();
				By txtTitleCategoryInput = By.xpath("//*[@id='select2-metadatable_metadata_attributes_5_value-results']//span[contains(text(),'"+strTitleCategory+"')]");
				if(existsElement(txtTitleCategoryInput, driver)){
						driver.findElement(txtTitleCategoryInput).click();
						return true;
				}
				return false;
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean setTitleSubgeneres(String strTitleSubgeneres){
		try{
			if(existsElement(selectTitleSubgeneres, driver)){
				driver.findElement(selectTitleSubgeneres).click();
				By txtTitleSubgenereInput = By.xpath("//*[@id='select2-metadatable_metadata_attributes_6_value-results']//span[contains(text(),'"+strTitleSubgeneres+"')]");
				if(existsElement(txtTitleSubgenereInput, driver)){
						driver.findElement(txtTitleSubgenereInput).click();
						return true;
				}
				return false;
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean setTitlePlacement(String strTitlePlacement){
		try{
			if(existsElement(selectTitlePlacement, driver)){
				driver.findElement(selectTitlePlacement).click();
				By txtTitlePlacementInput = By.xpath("//*[@id='select2-metadatable_metadata_attributes_7_value-results']//span[contains(text(),'"+strTitlePlacement+"')]");
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
	
	public boolean setTitleYear(String strTitleYear){
		try{
			if(existsElement(txtTitleYear, driver)){
				driver.findElement(txtTitleYear).sendKeys(strTitleYear);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setTitleArtistName(String strTitleArtistName){
		try{
			if(existsElement(txtTitleArtistName, driver)){
				driver.findElement(txtTitleArtistName).sendKeys(strTitleArtistName);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setTitleLinkedID(String strTitleLinkedID){
		try{
			if(existsElement(txtTitleLinkedID, driver)){
				driver.findElement(txtTitleLinkedID).sendKeys(strTitleLinkedID);
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
