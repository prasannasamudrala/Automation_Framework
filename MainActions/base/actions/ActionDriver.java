package base.actions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class ActionDriver extends Base {
	
	public static String s1;
	public static DateFormat dateFormat;
	public static Date date;

	public static String b = "</br></a>";
	String img = "<a target = \"_blank\"href=\"screenshots/";
	
// ************This is used for Giving Input to text boxes or edit boxes************
	public void type(String xpath, String data, String locator) throws IOException{
		try {
			Reporter.log("Clearing Data in" + locator + b);
			driver.findElement(By.xpath(xpath)).clear();
			Reporter.log("Cleared Data in" + locator + b);
			Reporter.log("Entering Data in" + locator + b);
			driver.findElement(By.xpath(xpath)).sendKeys(data);
			takeScreenShot(locator);
			Reporter.log(img+locator+".png>"+"\">"+"Entered Data in" + locator + b);
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log(data+"not entered in" + locator + b);
			takeScreenShot(locator);
		}
	}
	
	public void typeId(String id, String data, String locator) throws IOException{
		try {
			Reporter.log("Clearing Data in" + locator + b);
			driver.findElement(By.id(id)).clear();
			Reporter.log("Cleared Data in" + locator + b);
			Reporter.log("Entering Data in" + locator + b);
			driver.findElement(By.id(id)).sendKeys(data);
			takeScreenShot(locator);
			Reporter.log(img+locator+".png>"+"\">"+"Entered Data in" + locator + b);
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log(data+"not entered in" + locator + b);
			takeScreenShot(locator);
		}
	}
	
	public void Name(String name, String data, String locator) throws IOException{
		try {
			Reporter.log("Clearing Data in" + locator + b);
			driver.findElement(By.name(name)).clear();
			Reporter.log("Cleared Data in" + locator + b);
			Reporter.log("Entering Data in" + locator + b);
			driver.findElement(By.name(name)).sendKeys(data);
			takeScreenShot(locator);
			Reporter.log(img+locator+".png>"+"\">"+"Entered Data in" + locator + b);
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log(data+"not entered in" + locator + b);
			takeScreenShot(locator);
		}
	}
	
	
//***This function is used to click an objects like button,link,radio button,check boxes*****
	public void click(String xpath, String locator){
		try {
			Reporter.log("clicking an object" + locator + b);
			driver.findElement(By.xpath(xpath)).click();
			Reporter.log("clicked an object" + locator + b);
			Reporter.log(img+locator+".png"+"\">"+"Clicked an Object " + locator + b );
			takeScreenShot(locator);
			
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("click is not performed" + locator + b);
			takeScreenShot(locator);
			
		}
	}
//*************This is used for capturing snapshots**********************
	public void takeScreenShot(String locator) {
		// TODO Auto-generated method stub
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File des = new File("E:\\Mani Evng\\Automation_Framework\\Results\\screenshots" + locator + ".png");
		try {
			FileUtils.copyFile(src, des);
		} catch (Exception e) {
			
			e.printStackTrace();

		}

		
	}
		
	//*********This is used for reading data from excel file******************
	
		public String readExcelData(String filepath, int sheetindex, int rowid,int colid) throws EncryptedDocumentException, InvalidFormatException, IOException
		{
			try {
				FileInputStream fis= new FileInputStream(filepath);
				Workbook wb=WorkbookFactory.create(fis);
				Sheet s=wb.getSheetAt(sheetindex);
				Row r = s.getRow(rowid);

				Cell c=r.getCell(colid);

				                     if(c.getCellType()==Cell.CELL_TYPE_STRING)
						{
							s1=c.getStringCellValue();
							
						}
						else if(c.getCellType()==Cell.CELL_TYPE_NUMERIC)
						{
							s1=String.valueOf(c.getNumericCellValue());
							
						}
				                     
			} catch (Exception e) {
				Reporter.log("Unable to read data " + b);
				
			}
			return s1;
		}
	//-----------------------------------------------------------------------------------	
		
	//********************This is used for writing data into excel file*****************
		
		public  void writeDataintoExcel(String filepath,int sheetindex, int rowid,int colid, String inputvalue) throws EncryptedDocumentException, InvalidFormatException, IOException
		{
			try {
				FileInputStream is = new FileInputStream(filepath);

				// Create workbook and access file into Workbook
				Workbook wb = WorkbookFactory.create(is);

				// Get sheet by sending sheet id or sheet name
				Sheet s = wb.getSheetAt(sheetindex);
				// Sheet s = wb.getSheetAt(“Sheet1”);

				// Create object for Row and pass which row need to be accessed
				Row r = s.getRow(rowid);

				// Create new cell and set data by passing column number and Cell Value
				r.createCell(colid).setCellValue(inputvalue);

				// Initilize file outputStream
				FileOutputStream fOut = new FileOutputStream(filepath);

				// Write into workbook
				wb.write(fOut);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Reporter.log("Unable To Write Data  Into Excel"  + b);
			}
		}

	//----------------------------------------------------------------------------------

		
	//*************This is used for Handling popup window******************************
		public void popupHandle()
		{
			try {
				Alert a = driver.switchTo().alert();
				a.accept();
				Reporter.log("Popup Window Handled Successfully"  + b);
			} catch (Exception e) {
				Reporter.log("Unable To Handle Popup"  + b);
			}
		}
		
	//---------------------------------------------------------------------------------
		
		
//**********This is used for performing mouse over actions*************************
		public void mouseOverAction(String xpath, String locator)
		{
			try {
				WebElement menu = driver.findElement(By.xpath(xpath));
				acn = new Actions(driver);
				acn.moveToElement(menu).build().perform();
				Reporter.log("Mouse Over Action Performed " + locator + b);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Reporter.log("Unable to Perform Mouse Over Action" + locator + b);
			}
		}

	//---------------------------------------------------------------------------------

//**************This Function is used to perform Drag & Drop Operation*************
		public void dragDrop(String sourcelocator,String sourcexpath,String destinationlocator,String destxpath)
		{
			try {
				acn = new Actions(driver);
				WebElement src = driver.findElement(By.xpath(sourcexpath)); //source
				WebElement des = driver.findElement(By.xpath(destxpath)); //destination
				acn.dragAndDrop(src, des).build().perform();
				Reporter.log("Draged "+ sourcelocator + " Droped " + destinationlocator +b);
			} catch (Exception e) {
				Reporter.log("Unable to Perform Drag & Drop Operation" +b);
			}
		}
	//----------------------------------------------------------------------------------
		
//*********************This function is used for selecting data from combobox	******
	public void selectVisibletext(String xpath,String data,String locator )
	{
		try {
			
			WebElement element = driver.findElement(By.xpath(xpath)); 
			Select select = new Select(element);
			select.selectByVisibleText(data);
			Reporter.log(data + "is selected from "+locator  +b);
			takeScreenShot(locator);
			
		} catch (Exception e) {
			Reporter.log(data+"is not selected from list");
			takeScreenShot(locator);
			
		}
		
	}

	//----------------------------------------------------------------------------------------

	public String readMultipleDatafromExcel(String filepath,int sheetid) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		FileInputStream fis= new FileInputStream(filepath);
		Workbook wb=WorkbookFactory.create(fis);
		
	//   focus on sheet
		 Sheet s=wb.getSheetAt(sheetid);
		
		int rowcount=s.getLastRowNum();
		for (int i=1;i<rowcount+1;i++)
		{
			Row r = s.getRow(i);
			int cellcount = r.getLastCellNum();
			for (int j=0;j<cellcount;j++)
			{
				Cell c=r.getCell(j);
				if(c.getCellType()==Cell.CELL_TYPE_STRING)
				{
					s1=c.getStringCellValue();
					//System.out.println(s1);
					return s1;
				}
				else if(c.getCellType()==c.CELL_TYPE_NUMERIC)
				{
					s1=String.valueOf(c.getNumericCellValue());
					//System.out.println(s1);
					return s1;
				}
				
			}
		}
		return s1;
		

	}


	//*************************************************************************************

	public String currentdatetime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   //get current date time with Date()
		   Date date = new Date();
		   return dateFormat.format(date);
	}



	}


