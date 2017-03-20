package pages.staging;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import utility.Utility;

public class StagingEditSchedule extends Utility {
	
	WebDriver driver;
	
	//By btnCommit = By.name("commit");
	public static HashMap<String,String> AssetID = new HashMap<String,String> ();
	
	public StagingEditSchedule(WebDriver driver){
		this.driver = driver;
	}
	
	public boolean setContract(String strContract, String value){
		try{
			By byContract = By.id("select2-"+value+"_scheduling_metadata_attributes_1_value-container");
			if(existsElement(byContract, driver)){
				driver.findElement(byContract).click();
				By txtContractInput = By.xpath("//*[@id='select2-"+value+"_scheduling_metadata_attributes_1_value-results']//span[contains(text(),'"+strContract+"')]");
				if(existsElement(txtContractInput, driver)){
						driver.findElement(txtContractInput).click();
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
	
	public boolean setAsset(String value , String EpiName){
		try{
			
			By byAssetSpan = By.id("select2-"+value+"_scheduling_asset_id-container");
			if(existsElement(byAssetSpan, driver)){
				driver.findElement(byAssetSpan).click();
				By txtAsset = By.xpath("//*[@id='select2-"+value+"_scheduling_asset_id-results']/li[2]/ul/li");
				if(existsElement(txtAsset, driver)){
						String strAssetID[] = driver.findElement(txtAsset).getText().split(" ");
						if(!AssetID.containsKey(EpiName)){
							AssetID.put(EpiName, strAssetID[0]);
						}
						driver.findElement(txtAsset).click();
						return true;
				}
				return false;
			}
			return false;
		}catch(Exception e){
			System.out.println();
			e.printStackTrace();
			return false;
		}
	}
	
	public String getAssetID(String EpiName){
		return AssetID.get(EpiName);
	}
	
	public boolean setPlacement(String strPlacement, String value){
		try{
			By byContract = By.id("select2-"+value+"_scheduling_metadata_attributes_3_value-container");
			if(existsElement(byContract, driver)){
				driver.findElement(byContract).click();
				By txtContractInput = By.xpath("//*[@id='select2-"+value+"_scheduling_metadata_attributes_3_value-results']//span[contains(text(),'"+strPlacement+"')]");
				if(existsElement(txtContractInput, driver)){
						driver.findElement(txtContractInput).click();
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
	
	public boolean setSubscriptions(String strSubs, String value, String Flag){
		try{
			By bySubs = null,txtSubsInput = null;
			//strPrice="Film - Library (£3.00)";
			if(Flag.equalsIgnoreCase("Create")){
				bySubs = By.id("select2-"+value+"_scheduling_metadata_attributes_4_value-container");
				txtSubsInput = By.xpath("//*[@id='select2-"+value+"_scheduling_metadata_attributes_4_value-results']//span[contains(text(),'"+strSubs+"')]");
				
			}else if(Flag.equalsIgnoreCase("Update")){
				bySubs = By.id("select2-"+value+"_scheduling_metadata_attributes_5_value-container");
				txtSubsInput = By.xpath("//*[@id='select2-"+value+"_scheduling_metadata_attributes_5_value-results']//span[contains(text(),'"+strSubs+"')]");
				
			}
			if(existsElement(bySubs, driver)){
				driver.findElement(bySubs).click();
				if(existsElement(txtSubsInput, driver)){
						driver.findElement(txtSubsInput).click();
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
	
	public boolean setPrice(String strPrice, String value, String Flag){
		try{
			By byPrice = null,txtPriceInput = null;
			//strPrice="Film - Library (£3.00)";
			if(Flag.equalsIgnoreCase("Create")){
				byPrice = By.id("select2-"+value+"_scheduling_metadata_attributes_5_value-container");
				txtPriceInput = By.xpath("//*[@id='select2-"+value+"_scheduling_metadata_attributes_5_value-results']//span[contains(text(),'"+strPrice+"')]");
				
			}else if(Flag.equalsIgnoreCase("Update")){
				By lblPrice = By.xpath("//label[text()='Price']");
				String ForPrice = driver.findElement(lblPrice).getAttribute("for"); 
				byPrice = By.id("select2-"+ForPrice+"-container");
				txtPriceInput = By.xpath("//*[@id='select2-"+ForPrice+"-results']//span[contains(text(),'"+strPrice+"')]");
				
			}
			
			if(existsElement(byPrice, driver)){
				driver.findElement(byPrice).click();
				
				if(existsElement(txtPriceInput, driver)){
						driver.findElement(txtPriceInput).click();
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
	
	
	public boolean setStandalone(String value){
		try{
				By byStandalone = By.id(value+"_scheduling_metadata_attributes_9_value_true");
				System.out.println(value+"_scheduling_metadata_attributes_9_value_true");
				if(existsElement(byStandalone, driver)){
					driver.findElement(byStandalone).click();
						driver.findElement(byStandalone).click();
							return true;
				}	
				return false;
				
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean clickSubmit(String value){
		try{
			By btnCommit = By.xpath("//*[@id='"+value+"_edit_scheduling_"+value+"']/div[4]/div[1]/input");
			if(existsElement(btnCommit, driver)){
				driver.findElement(btnCommit).click();
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setExpiryDate(String strEndingDate,String value){ 
        try{ 
                
                if(existsElement((By.id(value+"_scheduling_take_down_3i")), driver)){ 
                        String []strEndingDatearr = strEndingDate.split("-"); 
                        
                        driver.findElement(By.id(value+"_scheduling_take_down_3i")).clear(); 
                        driver.findElement(By.id(value+"_scheduling_take_down_3i")).sendKeys(strEndingDatearr[0]); 

                        
                        driver.findElement((By.id(value+"_scheduling_take_down_2i"))).clear(); 
                        driver.findElement((By.id(value+"_scheduling_take_down_2i"))).sendKeys(strEndingDatearr[1]);

                        
                        driver.findElement((By.id(value+"_scheduling_take_down_1i"))).clear(); 
                        driver.findElement((By.id(value+"_scheduling_take_down_1i"))).sendKeys(strEndingDatearr[2]);

                        
                        driver.findElement((By.id(value+"_scheduling_take_down_4i"))).clear(); 
                        driver.findElement((By.id(value+"_scheduling_take_down_4i"))).sendKeys(strEndingDatearr[3]);

                        
                        driver.findElement((By.id(value+"_scheduling_take_down_5i"))).clear(); 
                        driver.findElement((By.id(value+"_scheduling_take_down_5i"))).sendKeys(strEndingDatearr[4]);

                        
                        return true; 
                } 
                else{ 
                return false; 
                } 
        }catch(Exception e){ 
                return false; 
        } 
}

	public boolean setPresentationVideoQuality(String presenationVQ, String value) {
		try{
			String strPresentationVQ = null;
			if(presenationVQ.equalsIgnoreCase("HD")){
				strPresentationVQ = "High definition";
			}else if(presenationVQ.equalsIgnoreCase("SD")){
				strPresentationVQ = "Standard definition"; 
			}else if(presenationVQ.equalsIgnoreCase("UHD")){
				strPresentationVQ = "Ultra HD (4K)";
			}else{
				return false;
			}
			
			By byPresentationVQ = By.id("select2-"+value+"_scheduling_metadata_attributes_2_value-container");
			if(existsElement(byPresentationVQ, driver)){
				driver.findElement(byPresentationVQ).click();
				By txtPresentationVQ = By.xpath("//*[@id='select2-"+value+"_scheduling_metadata_attributes_2_value-results']//span[contains(text(),'"+strPresentationVQ+"')]");
				if(existsElement(txtPresentationVQ, driver)){
						driver.findElement(txtPresentationVQ).click();
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


	
	
	

}
