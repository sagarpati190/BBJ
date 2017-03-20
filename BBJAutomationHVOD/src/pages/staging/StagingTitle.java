package pages.staging;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import utility.Utility;

public class StagingTitle extends Utility {
	
	WebDriver driver;
	
	By selectTitleType = By.xpath("//*[@id='select2-metadatable_metadata_attributes_0_value-container']");
	By txtTitleName = By.id("metadatable_metadata_attributes_2_value");
	By txtTitleShort = By.id("metadatable_metadata_attributes_3_value");
	By txtTitleMedium = By.id("metadatable_metadata_attributes_4_value");
	By txtTitleLong = By.id("metadatable_metadata_attributes_5_value");
	By selectTitleCategory = By.xpath("//*[@id='select2-metadatable_metadata_attributes_6_value-container']");
		
	By selectTitleSubgeneres = By.xpath("//*[@id='select2-metadatable_metadata_attributes_7_value-container']");
	
	By selectTitlePlacement = By.xpath("//*[@id='select2-metadatable_metadata_attributes_8_value-container']");
	By txtTitleYear = By.id("metadatable_metadata_attributes_9_value");
	By txtTitleArtistName = By.id("metadatable_metadata_attributes_14_value");
	By txtTitleLinkedID = By.id("metadatable_metadata_attributes_21_value");
	
	By selectTitleCarousels_1 = By.xpath("//*[@id='select2-metadatable_metadata_attributes_23_value-container']");
	By selectTitleCarousels_2 = By.xpath(".//*[@id='edit_metadatable']/div[25]/div[2]");
		//	By.xpath("//*[@id='select2-metadatable_metadata_attributes_1478162689736_0_value-container']");
	By addCarousels_2 = By.xpath(".//*[@id='edit_metadatable']/div[25]/div[1]/div/a[1]");
	By btnCommit = By.xpath("//*[@id='edit_metadatable']/div[28]/input");
	
	
	   String[] Entertainment={"CATCH UP HIGHLIGHTS","GOLD","NATIONAL GEOGRAPHIC","DISCOVERY","REALITY AND ENT","ENT DRAMA","ENT FACTUAL","ENT COMEDY","ENT LIFESTYLE","ENT CRIME","EXPLORE AMC","LAST NIGHT'S TV","CATCH UP BOX SETS","COMING SOON","TV BOX SET HIGHLIGHTS"};
	   String[] Sport={"SPORT HIGHLIGHTS","SPORT TRENDING","CL FINAL","CL SEMI FINALS","CL QUARTER FINALS","CL ROUND OF 16","CL MATCHDAY 6","CL MATCHDAY 5","CL MATCHDAY 4","CL MATCHDAY 3","CL MATCHDAY 2","CL LAST SEASON","CL BEST MOMENTS","CL CLASSICS","CL ALL","EUROPA FINAL","EUROPA SEMI FINALS","EUROPA QUARTER FINALS","EUROPA ROUND OF 16","EUROPA MATCHDAY 6","EUROPA MATCHDAY 5","EUROPA MATCHDAY 4","EUROPA MATCHDAY 3","EUROPA MATCHDAY 2","EUROPA MATCHDAY 1","EUROPA LAST SEASON","EUROPA ALL","BUNDESLIGA","SERIE A","LIGUE 1","SCOTTISH FOOBALL","MORE FOOTBALL","ALL OTHER FOOTBALL","MOTOGP","WRC","FIM SPEEDWAY","MORE MOTORSPORT","ALL MOTORSPORT","UFC FIGHTS","UFC SHOWS","UFC FIGHTER PROFILES","UFC BEST MOMENTS","UFC CLASSICS","ALL UFC","PREMIER LEAGUE","FA CUP","CRICKET","RUGBY","BT SPORT FILMS","SPORT CLASSICS","SPORT BEST OF REST"};
	   String[] Film={"FILM NEW RELEASES","SKY HIGHLIGHTS","CURZON MOST POPULAR","SKY BOX SET 1","SKY BOX SET 2","SKY BOX SET 3","SKY BOX SET 4","CURZON COLLECTION 1","CURZON COLLECTION 2","CURZON COLLECTION 3","CURZON COLLECTION 4","CURZON COLLECTION 5","CURZON COLLECTION 6","CURZON COLLECTION 7","CURZON COLLECTION 8","CURZON COLLECTION 9","CURZON COLLECTION 10","FILM RECENTLY ADDED","FILM NEW TO RENT","FILM HOT RIGHT NOW","FILMS ON CATCH-UP"};
	   String[] Kids={"KIDS OPTIONAL 1","NICKTOONS CATCH UP","CARTOON NETWORK CATCH UP","BOOMERANG CATCH UP","CARTOONITO CATCH UP","NICKELODEON","NICKTOONS","JUST KIDZ","NICK JR","CARTOONITO","TODDLER TELLY","BACK TO BACKS","KIDS STORE NEW","KIDS STORE BOX SETS","KIDS STORE FILMS","KIDS OPTIONAL 2","EXTRA AWESOME TV","HERO CHARACTERS","SING-ALONG"};
	   String[] Store={"TOP 40","PLAYLISTS>POPULAR","PLAYLISTS>TIME TO RELAX","PLAYLISTS>PARTY","PLAYLISTS>LOVE SONGS","ARTIST PLAYLISTS","PLAYLISTS>RETRO HITS","PLAYLISTS>FESTIVALS","PLAYLISTS>AWARD NOMINEES","PLAYLISTS>TV SHOWS","PLAYLIST>ALL CATEGORIES","ALBUMS>NEW RELEASES","ALBUMS>MOST POPULAR","ALBUMS>POP","ALBUMS>R&B/HIP HOP","ALBUMS>INDIE / ROCK","ALBUMS>FOLK","ALBUMS>60S","ALBUMS>70S","ALBUMS>80S","ALBUMS>90S","CONCERTS","MUSIC ON FILM","KARAOKE!","POPULAR PLAYLISTS","LATEST ALBUMS","SPOTLIGHT INTERVIEWS","SPOTLIGHT 1","SPOTLIGHT 2","SPOTLIGHT 3","SPOTLIGHT 4","SPOTLIGHT 5","SPOTLIGHT 6","SPOTLIGHT 7","SPOTLIGHT 11","SPOTLIGHT 12","SPOTLIGHT 14","SPOTLIGHT 15"};
	   String[] Highlights={"EXPLORE AMC","SPORT TRENDING","BEST OF FILM","KIDS HIGHLIGHTS","NEW MUSIC VIDEOS","CATCH UP HIGHLIGHTS","NEW TO STORE"};

	public StagingTitle(WebDriver driver){
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
				By txtTitleCategoryInput = By.xpath("//*[@id='select2-metadatable_metadata_attributes_6_value-results']//span[contains(text(),'"+strTitleCategory+"')]");
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
				By txtTitleSubgenereInput = By.xpath("//*[@id='select2-metadatable_metadata_attributes_7_value-results']//span[contains(text(),'"+strTitleSubgeneres+"')]");
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
				By txtTitlePlacementInput = By.xpath("//*[@id='select2-metadatable_metadata_attributes_8_value-results']//span[contains(text(),'"+strTitlePlacement+"')]");
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
	
	// New Carousel feature implementation
		public boolean setCarousels_1(String strCarousels_1){
			try{
				
				if(existsElement(selectTitleCarousels_1, driver)){
					driver.findElement(selectTitleCarousels_1).click();
					By txtTitleCarousel_1Input = By.xpath("//*[@id='select2-metadatable_metadata_attributes_23_value-results']//span[contains(text(),'"+strCarousels_1+"')]");
					if(existsElement(txtTitleCarousel_1Input, driver)){
							driver.findElement(txtTitleCarousel_1Input).click();
							return true;
					}
					return false;
				}
				return false;
			}catch(Exception e){
				return false;
			}
		}
		
		
		public boolean setCarousels_2(String strCarousels_2){
			try{
				driver.findElement(addCarousels_2).click();
				Thread.sleep(2000);
		//		driver.findElement(selectTitleCarousels_2).isEnabled()
				if(existsElement(selectTitleCarousels_2, driver)){
					driver.findElement(selectTitleCarousels_2).click();
					By txtTitleCarousel_2Input = By.xpath("//span[contains(text(),'"+strCarousels_2+"')]");
					if(existsElement(txtTitleCarousel_2Input, driver)){
							driver.findElement(txtTitleCarousel_2Input).click();
							return true;
					}
					return false;
				}
				return true;
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
