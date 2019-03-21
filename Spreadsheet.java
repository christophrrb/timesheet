import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class Spreadsheet {

	//javac -cp ~/java_libs/...

	public Spreadsheet(String[] monday, String[] tuesday, String[] wednesday, String[] thursday, String[] friday) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("FirstSheet");

		HSSFRow rowHead = sheet.createRow((short) 0);
		rowHead.createCell(0).setCellValue("Day");
		rowHead.createCell(1).setCellValue("Time In");
		rowHead.createCell(2).setCellValue("Time Out");
		rowHead.createCell(3).setCellValue("Time In");
		rowHead.createCell(4).setCellValue("Time Out");

		HSSFRow mondayRow = sheet.createRow((short) 1);
		mondayRow.createCell(0).setCellValue("Monday");
		mondayRow.createCell(1).setCellValue(monday[0]);
		mondayRow.createCell(2).setCellValue(monday[1]);
		mondayRow.createCell(3).setCellValue(monday[2]);
		mondayRow.createCell(4).setCellValue(monday[3]);

		HSSFRow tuesdayRow = sheet.createRow((short) 2);
		tuesdayRow.createCell(0).setCellValue("Tuesday");
		tuesdayRow.createCell(1).setCellValue(tuesday[0]);
		tuesdayRow.createCell(2).setCellValue(tuesday[1]);
		tuesdayRow.createCell(3).setCellValue(tuesday[2]);
		tuesdayRow.createCell(4).setCellValue(tuesday[3]);

		HSSFRow wednesdayRow = sheet.createRow((short) 3);
		wednesdayRow.createCell(0).setCellValue("Wednesday");
		wednesdayRow.createCell(1).setCellValue(wednesday[0]);
		wednesdayRow.createCell(2).setCellValue(wednesday[1]);
		wednesdayRow.createCell(3).setCellValue(wednesday[2]);
		wednesdayRow.createCell(4).setCellValue(wednesday[3]);

		HSSFRow thursdayRow = sheet.createRow((short) 4);
		thursdayRow.createCell(0).setCellValue("Thursday");
		thursdayRow.createCell(1).setCellValue(thursday[0]);
		thursdayRow.createCell(2).setCellValue(thursday[1]);
		thursdayRow.createCell(3).setCellValue(thursday[2]);
		thursdayRow.createCell(4).setCellValue(thursday[3]);

		HSSFRow fridayRow = sheet.createRow((short) 5);
		fridayRow.createCell(0).setCellValue("Friday");
		fridayRow.createCell(1).setCellValue(friday[0]);
		fridayRow.createCell(2).setCellValue(friday[1]);
		fridayRow.createCell(3).setCellValue(friday[2]);
		fridayRow.createCell(4).setCellValue(friday[3]);

		String fileName = "timesheet.xls";

		/*int i = 1;
		while (new File(fileName).exists() || i < 1000) {
			fileName = fileName + i;
		}*/

		try {
			FileOutputStream fos = new FileOutputStream(new File(fileName));
			workbook.write(fos);
			fos.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}