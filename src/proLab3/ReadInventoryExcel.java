package proLab3;

import java.io.File;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ReadInventoryExcel {
	ArrayList<Person> families[] = new ArrayList[4];
	
	public void read(String file) throws Exception {
	

			
		families[0]=new ArrayList<>();
		families[1]=new ArrayList<>();
		families[2]=new ArrayList<>();
		families[3]=new ArrayList<>();

		

			int i=0,sheetNumber=0;
			Workbook workbook = WorkbookFactory.create(new File("C:\\Users\\uzumc\\OneDrive\\Masaüstü\\Prolab 3.csv"));

			
			Iterator<Sheet> sheetItarator = workbook.sheetIterator();
			
			while (sheetItarator.hasNext()) {
				Sheet numberSheet = sheetItarator.next();
				System.out.println("Sheet Name: " + numberSheet.getSheetName());
				Iterator<Row> rowIterator = numberSheet.rowIterator();
				i=0;
				while (rowIterator.hasNext()) {
					Person temp = new Person();
					Row row = rowIterator.next();
					if (row.getRowNum() == 0) {
						continue;
					}
					Cell cell1 = row.getCell(0);
					Integer id = Integer.valueOf((int) cell1.getNumericCellValue());
					System.out.print(id + " ");

					Cell cell2 = row.getCell(1);
					String firstName = cell2.getStringCellValue();
					System.out.print(firstName + " ");

					Cell cell3 = row.getCell(2);
					String lastName = cell3.getStringCellValue();
					System.out.print(lastName + " ");

					//String dateString=null;
					Cell cell4 = row.getCell(3);
					 Date date = null;
					//Date date =cell4.getDateCellValue(); 
					if(cell4.getCellType() == CellType.NUMERIC||cell4.getCellType() == CellType.FORMULA)
					   {
					    
					 String cellValue=String.valueOf(cell4.getNumericCellValue());
					     if(DateUtil.isCellDateFormatted(cell4))
					      {
					          DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					          date = cell4.getDateCellValue();
					          cellValue = df.format(date);
					       }
					          System.out.print(cellValue+" ");
					    }
					

					Cell cell5 = row.getCell(4);
					String partner=" ";
					if(cell5.getStringCellValue()!=null) {
					 partner = cell5.getStringCellValue();
						System.out.print(partner + " ");		
					}
				

					Cell cell6 = row.getCell(5);
					String momName = cell6.getStringCellValue();
					System.out.print(momName + " ");

					Cell cell7 = row.getCell(6);
					String dadName = cell7.getStringCellValue();
					System.out.print(dadName + " ");

					Cell cell8 = row.getCell(7);
					String blood = cell8.getStringCellValue();
					System.out.print(blood + " ");

					Cell cell9 = row.getCell(8);
					String job ="";
					if(cell9!=null) {
						job = cell9.getStringCellValue();
						System.out.print(job + " ");	
					}
					

					Cell cell10 = row.getCell(9);
					String maritalStatus = " ";
					if(cell10!=null) {
					 maritalStatus = cell10.getStringCellValue();
						System.out.print(maritalStatus + " ");	
					}
					

					Cell cell11 = row.getCell(10);
					String maidenName = " ";
					if(cell11!=null) {
						maidenName = cell11.getStringCellValue();
						System.out.print(maidenName + " ");		
					}
				

					Cell cell12 = row.getCell(11);
					String gender = cell12.getStringCellValue();
					System.out.print(gender + " ");

					System.out.println();
					
					temp.setId(id);
					temp.setFirstName(firstName);
					temp.setLastName(lastName);
					temp.setBirthDay(date);
					temp.setPartner(partner);
					temp.setMomName(momName);
					temp.setDadName(dadName);
					temp.setBloodType(blood);
					temp.setJob(job);
					temp.setMaritalStatus(maritalStatus);
					temp.setMaidenName(maidenName);
					temp.setGender(gender);
					families[sheetNumber].add(i, temp);
					
					System.out.println(i+" ---"+families[sheetNumber].get(i).getFirstName()+" " +families[sheetNumber].get(i++).getLastName());
					
				}
				sheetNumber++;
//				numberSheet = sheetItarator.next();
//				System.out.println("Sheet Name: " + numberSheet.getSheetName());
//				rowIterator = numberSheet.rowIterator();
			}
			workbook.close();

		
		}
}
