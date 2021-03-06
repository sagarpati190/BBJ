package pages.staging;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Utility;
public class StagingAddAsset extends Utility {
	
		WebDriver driver;
		By txtAssetDesc = By.id("metadatable_description");
		By txtAssetRuntime = By.id("metadatable_metadata_attributes_2_value");
		By selectAssetLanguage = By.xpath("//*[@id='select2-metadatable_metadata_attributes_3_value-container']");
		By selectAssetRating  = By.xpath("//*[@id='select2-metadatable_metadata_attributes_4_value-container']");
		By selectAssetResolution  = By.xpath("//*[@id='select2-metadatable_metadata_attributes_6_value-container']");
		By selectAssetAspectRatio  = By.xpath("//*[@id='select2-metadatable_metadata_attributes_8_value-container']");
		By btnAddAsset = By.xpath("//*[@id='new_metadatable']/div[18]/input");
		
		public StagingAddAsset(WebDriver driver){
			this.driver = driver;
		}
		
				
		public boolean setAssetDesc(String strAssetDesc){
			try{
				if(existsElement(txtAssetDesc, driver)){
					driver.findElement(txtAssetDesc).sendKeys(strAssetDesc);
					return true;
				}
				return false;
			}catch(Exception e){
				return false;
			}
		}
		
		public boolean setAssetRuntime(String strAssetRuntime){
			try{
				if(existsElement(txtAssetRuntime, driver)){
					driver.findElement(txtAssetRuntime).clear();
					driver.findElement(txtAssetRuntime).sendKeys(strAssetRuntime);
					return true;
				}
				return false;
			}catch(Exception e){
				return false;
			}
		}
		
		public boolean setAssetLanguage(String strAssetLanguage){
			try{
				if(existsElement(selectAssetLanguage, driver)){
					driver.findElement(selectAssetLanguage).click();
					By txtAssetLanguageInput = By.xpath("//*[@id='select2-metadatable_metadata_attributes_3_value-results']//span[contains(text(),'"+strAssetLanguage+"')]");
					if(existsElement(txtAssetLanguageInput, driver)){
							driver.findElement(txtAssetLanguageInput).click();
							return true;
					}
					return false;
				}
				return false;
			}catch(Exception e){
				return false;
			}
		}
		
		public boolean setAssetRating(String strAssetRating){
			try{
				if(existsElement(selectAssetRating, driver)){
					driver.findElement(selectAssetRating).click();
					By txtAssetRatingInput = By.xpath("//*[@id='select2-metadatable_metadata_attributes_4_value-results']//span[contains(text(),'"+strAssetRating+"')]");
					System.out.println("//*[@id='new_metadatable']//div[@data-value='"+strAssetRating+"']");
					if(existsElement(txtAssetRatingInput, driver)){
							driver.findElement(txtAssetRatingInput).click();
							return true;
					}
					return false;
				}
				return false;
			}catch(Exception e){
				return false;
			}
		}
		
		public boolean setAssetResolution(String strAssetResolution){
			try{
				By txtAssetResolutionInput = null;
				if(existsElement(selectAssetResolution, driver)){
					driver.findElement(selectAssetResolution).click();
					if(strAssetResolution.equalsIgnoreCase("HD")){
						txtAssetResolutionInput = By.xpath("//*[@id='select2-metadatable_metadata_attributes_6_value-results']//span[contains(text(),'High definition')]");
					}else if(strAssetResolution.equalsIgnoreCase("SD")){
						txtAssetResolutionInput = By.xpath("//*[@id='select2-metadatable_metadata_attributes_6_value-results']//span[contains(text(),'Standard definition')]");
					}
					if(strAssetResolution.equalsIgnoreCase("UHD")){
						txtAssetResolutionInput = By.xpath("//*[@id='select2-metadatable_metadata_attributes_6_value-results']//span[contains(text(),'Ultra HD (4K)')]");
					}
					if(existsElement(txtAssetResolutionInput, driver)){
							driver.findElement(txtAssetResolutionInput).click();
							return true;
					}
					return false;
				}
				return false;
			}catch(Exception e){
				return false;
			}
		}
		
		public boolean setAssetRatio(){
			try{
				if(existsElement(selectAssetAspectRatio, driver)){
					driver.findElement(selectAssetAspectRatio).click();
					By txtAssetRatioInput = By.xpath("//*[@id='select2-metadatable_metadata_attributes_8_value-results']//span[contains(text(),'16:9 standard')]");
					if(existsElement(txtAssetRatioInput, driver)){
							driver.findElement(txtAssetRatioInput).click();
							return true;
					}
					return false;
				}
				return false;
			}catch(Exception e){
				return false;
			}
		}
		
		public boolean clickUpdateButton(){
			try{
				if(existsElement(btnAddAsset, driver)){
					driver.findElement(btnAddAsset).click();
					return true;
				}
				return false;
			}catch(Exception e){
				return false;
			}
		}
	}


