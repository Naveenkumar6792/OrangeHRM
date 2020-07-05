package Utilis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {


		public XSSFWorkbook objWorkbook = null;
		public XSSFWorkbook objXSSFWorkbook = null;
		/**
		* @return the objXSSFWorkbook
		*/
		public XSSFWorkbook getObjXSSFWorkbook() {
		return objXSSFWorkbook;
		}
		/**
		* @param objXSSFWorkbook the objXSSFWorkbook to set
		*/
		public void setObjXSSFWorkbook(XSSFWorkbook objXSSFWorkbook) {
		this.objXSSFWorkbook = objXSSFWorkbook;
		}

		public XSSFSheet objXSSFWorksheet = null;
		public XSSFSheet objWorksheet = null;
		public XSSFWorkbook getObjWorkbook() {
		return objWorkbook;
		}
		public void setObjWorkbook(XSSFWorkbook objWorkbook) {
		this.objWorkbook = objWorkbook;
		}

		public  int GetColumnNumber(String strSheetName, String strColumn) {
		int colIdx = 0;
		objWorksheet = objWorkbook.getSheet(strSheetName);
		// int TotalRows = objWorksheet.getPhysicalNumberOfRows();
		int TotalCols = objWorksheet.getRow(0).getLastCellNum();
		XSSFRow rowHeader = objWorksheet.getRow(0);
		for(int idx=0;idx<TotalCols;idx++) {
		if(rowHeader.getCell(idx).getStringCellValue().contains(strColumn)) {
		colIdx = idx;
		break;
		}
		}

		return colIdx;

		}

		public Object[][] GetExcelData(String strSheetName){
		objWorksheet = objWorkbook.getSheet(strSheetName);
		XSSFRow Row=objWorksheet.getRow(0);     //get my Row which start from 0  
		   
		        int RowNum = objWorksheet.getPhysicalNumberOfRows();// count my number of Rows
		        int ColNum= Row.getLastCellNum(); // get last ColNum
		        HashMap<String, String> objRowData = new LinkedHashMap<String, String>();
		        Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
		         
		            for(int i=0; i<RowNum-1; i++) //Loop work for Rows
		            {  
		                XSSFRow row= objWorksheet.getRow(i+1);
		                 
		                for (int j=0; j<ColNum; j++) //Loop work for colNum
		                {
		                   
		                        XSSFCell cell= row.getCell(j);
		                        if(cell==null)
		                            Data[i][j]= ""; //if it get Null value it pass no data
		                        else
		                        {
		                        HSSFDataFormatter formatter = new HSSFDataFormatter();
		                            String value= formatter.formatCellValue(cell);
		                            objRowData.put(Row.getCell(j).getStringCellValue(),value);
		//d                            Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
		                        }
		                }
		                Data[i][0] = i;
		                Data[i][1] = objRowData;
		                objRowData = new HashMap<String, String>();
		            }
		           
		return Data;


		}

		public Object[][] GetExcelData(String strSheetName, Integer Count){
		objWorksheet = objWorkbook.getSheet(strSheetName);
		XSSFRow Row=objWorksheet.getRow(0);     //get my Row which start from 0  
		   
//		        int RowNum = objWorksheet.getPhysicalNumberOfRows();// count my number of Rows
		        int ColNum= Row.getLastCellNum(); // get last ColNum
		        HashMap<String, String> objRowData = new LinkedHashMap<String, String>();
		        Object Data[][]= new Object[Count][2]; // pass my  count data in array
		         
		            for(int i=0; i<=Count-1; i++) //Loop work for Rows
		            {  
		                XSSFRow row= objWorksheet.getRow(i+1);
		                 //System.out.println("Row = "+i+1);
		                for (int j=0; j<ColNum; j++) //Loop work for colNum
		                {
		                   
		                //System.out.println(j);
		                        XSSFCell cell= row.getCell(j);
		                        if(cell==null) {
//		                          Data[i][j]= ""; //if it get Null value it pass no data
		                        }
//		                          
		                        else
		                        {
		                        if(cell.getCellTypeEnum() == CellType.FORMULA) {
		                        if(cell.getCachedFormulaResultTypeEnum()==CellType.NUMERIC) {
		                        String value = String.valueOf(new BigDecimal(cell.getNumericCellValue()).longValue());
		                        objRowData.put(Row.getCell(j).getStringCellValue(),value);
		                        }else if(cell.getCachedFormulaResultTypeEnum()==CellType.STRING) {
		                        String value = String.valueOf(cell.getStringCellValue());
		                        objRowData.put(Row.getCell(j).getStringCellValue(),value);
		                        }
		                        }else {
		                        HSSFDataFormatter formatter = new HSSFDataFormatter();
		                                String value= formatter.formatCellValue(cell);
		                                objRowData.put(Row.getCell(j).getStringCellValue(),value);
		    //d                            Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
		                        }
		                       
		                        }
		                }
		                Data[i][0] = i;
		                Data[i][1] = objRowData;
		                objRowData = new HashMap<String, String>();
		            }
		           
		return Data;


		}

		public static void main(String args[]) {
			
			ExcelUtils objUtil = new ExcelUtils();
			XSSFWorkbook objWork = null;
			try {
			objWork = new XSSFWorkbook(new FileInputStream(new File("C:\\Users\\naveen.baskaran\\Desktop\\LoginValidation.xlsx")));
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			objUtil.setObjWorkbook(objWork);

			objUtil.GetExcelData("Sheet1");
			objUtil.GetExcelData("Sheet1",4);

			}



}
