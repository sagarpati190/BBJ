package pages.preprod;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Utility;

public class PreprodCreateContent extends Utility {
	WebDriver driver;
	
	By txtTitleName = By.id("title_name");
	By selectLicensorTitle = By.id("select2-title_licensor_name-container");
	By txtLicensorInput = By.xpath("//li/input[@class='select2-search__field']");
	By txtTitleExternalID = By.id("title_external_id");
	
	
	
	By txtSeriesName = By.id("series_name");
	By selectLicensorSeries = By.id("select2-series_licensor_name-container");
	By txtSeriesExternalID = By.id("series_external_id");
	By selectBrandName = By.id("select2-series_brand_name-container");
	By btnAddEpisodeSeries = By.linkText("Add Episode");
	By txtSetEpisodeSeries = By.xpath("//input[starts-with(@name,'series[episodes_attributes]') and contains(@name,'[name]')]");
	
	
	By txtCollectionName = By.id("collection_name");
	By txtCollectionExternalID = By.id("collection_external_id");
	By txtAddEpisodeCollection = By.id("candidates_q");
	
	
	
	By btnCreate = By.name("commit");
	
	
	public PreprodCreateContent(WebDriver driver){
		this.driver = driver;
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
	
	public boolean setSeriesName(String strSeriesName){
		try{
			if(existsElement(txtSeriesName, driver)){
				driver.findElement(txtSeriesName).sendKeys(strSeriesName);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}		
		
	}
	
	public boolean setCollectionName(String strCollectionName){
		try{
			if(existsElement(txtCollectionName, driver)){
				driver.findElement(txtCollectionName).sendKeys(strCollectionName);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}		
		
	}
	
	public boolean setLicensorTitle(String strLicensor){
		try{
			System.out.println("");
			if(existsElement(selectLicensorTitle, driver)){
				driver.findElement(selectLicensorTitle).click();
				By txtLicensorInput = By.xpath("//span[text()='"+strLicensor+"']");
				if(existsElement(txtLicensorInput, driver)){
					driver.findElement(txtLicensorInput).click();
					return true;
				}
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setLicensorSeries(String strLicensor){
		try{
			System.out.println("");
			if(existsElement(selectLicensorSeries, driver)){
				driver.findElement(selectLicensorSeries).click();
				By txtLicensorInput = By.xpath("//span[text()='"+strLicensor+"']");
				if(existsElement(txtLicensorInput, driver)){
					driver.findElement(txtLicensorInput).click();
					return true;
				}
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setTitleExternalID(String strExternalID){
		try{
			if(existsElement(txtTitleExternalID, driver)){
				driver.findElement(txtTitleExternalID).sendKeys(strExternalID);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}		
		
	}
	
	public boolean setSeriesExternalID(String strExternalID){
		try{
			if(existsElement(txtSeriesExternalID, driver)){
				driver.findElement(txtSeriesExternalID).sendKeys(strExternalID);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}		
		
	}
	
	public boolean setCollectionExternalID(String strExternalID){
		try{
			if(existsElement(txtCollectionExternalID, driver)){
				driver.findElement(txtCollectionExternalID).sendKeys(strExternalID);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}		
		
	}
	
	public boolean clickSubmit(){
	 try{
	    	if(existsElement(btnCreate, driver)){
		    	driver.findElement(btnCreate).click();
				return true;
			}
	    return false;
	 	}catch(Exception e){
	 		return false;
	 }
	}
	
	public boolean addEpisodeCollection(String strEpisodeName){
		try{
			
			if(existsElement(txtAddEpisodeCollection, driver)){
				driver.findElement(txtAddEpisodeCollection).clear();
				driver.findElement(txtAddEpisodeCollection).sendKeys(strEpisodeName);	
				Thread.sleep(5000);
				By addEpiResult = By.xpath("//*[@id='new_collection']/div[6]/div/div/div/ul/li[1]/a[1]");
				if(existsElement(addEpiResult, driver)){
					driver.findElement(addEpiResult).click();
				}
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}		
		
	}
	
	
	public boolean setBrandName(String strBrandName){
		try{
			
			if(existsElement(selectBrandName, driver)){
				driver.findElement(selectBrandName).click();
				By txtBrandInput = By.xpath("//span[text()='"+strBrandName+"']");
				if(existsElement(txtBrandInput, driver)){
					driver.findElement(txtBrandInput).click();
					return true;
				}
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean addEpisodeSeries(int EpiCount){
		try{
			
			if(existsElement(btnAddEpisodeSeries, driver)){
			    for(int i=0;i<EpiCount;i++){
			    	driver.findElement(btnAddEpisodeSeries).click();	
			    }
			    return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setEpisodeSeries(String strSeriesName){
		try{
			
			if(existsElement(txtSetEpisodeSeries, driver)){
				List<WebElement>  listSetEpisodeSeries = driver.findElements(txtSetEpisodeSeries); 
				int i = 0;
				for( WebElement SetEpisodeSeries : listSetEpisodeSeries ){
					i=i+1;
					String EpisodeName = "EP"+i+"_"+strSeriesName;
					SetEpisodeSeries.clear();
					SetEpisodeSeries.sendKeys(EpisodeName);
				}
				
			    return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	
	
	
	
}
