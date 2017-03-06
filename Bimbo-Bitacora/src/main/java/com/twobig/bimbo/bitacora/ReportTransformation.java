package com.twobig.bimbo.bitacora;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.twobig.bimbo.constants.Constants;

public class ReportTransformation {
	
	public void GetReportTransformation(String fileNameTransformation,String country,
			String year,String month,String day,int numArgs){
		DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);

		File file = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para leer
//			file = new File(fileNameTransformation);
			file = new File(Constants.FILE_ERRORS_PATH_TRANSFORMATION + fileNameTransformation);
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			int j = 1;
			String line = "";
			boolean query=false;

			int c = 1;
			List<String> listQuery = new ArrayList<String>();
	
			HSSFSheet sheet = MainBitacora.workbook.createSheet(Constants.SECOND_SHEET_NAME);
			HSSFRow row;
			HSSFCell cell;
			HSSFCellStyle style = MainBitacora.workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.AQUA.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFCellStyle styleRosa = MainBitacora.workbook.createCellStyle();
			styleRosa.setFillForegroundColor(HSSFColor.AQUA.index);
			styleRosa.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			row = sheet.createRow(0);
			row.setRowStyle(style);
			cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(Constants.FIRST_COLUMN_NAME_SHEET_TWO);
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(Constants.SECOND_COLUMN_NAME_SHEET_TWO);
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(Constants.THIRD_COLUMN_NAME_SHEET_TWO);
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(Constants.QUARTER_COLUMN_NAME_SHEET_TWO);
			
			String tmp="";
			
			while ((line = br.readLine()) != null) {

				row = sheet.createRow(j);
				String [] lineTmp = null;
				if(line.contains(".txt-")) {
					lineTmp = line.split(".txt-");
					if(lineTmp.length == 1) {
						lineTmp = new String[2];
						lineTmp[1] = line;
					}
				}else if(line.contains(".txt:")) {
					lineTmp = line.split(".txt:");
				}else {
					lineTmp = new String[2];
					lineTmp[1] = line;
				}
				
				if(query) {
					if (lineTmp[1].endsWith(";")){
						tmp= tmp + lineTmp[1];
						listQuery.add(tmp);
						tmp="";
					}else if(lineTmp[1].endsWith("Continuing without it.")){
						query=false;
						String [] array = lineTmp[1].split(",");
						listQuery.add(array[0]);
					}else {
						query=true;
						tmp= tmp + lineTmp[1];
					}
				}else {
					if(lineTmp[1].startsWith(Constants.FIRST_PRINCIPAL_ERROR_TEXT_TRANSFORMATION) ||
							lineTmp[1].startsWith(Constants.FIRST_PRINCIPAL_ERROR_TEXT_TRANSFORMATION.toUpperCase()) ||
							lineTmp[1].startsWith(Constants.SECOND_PRINCIPAL_ERROR_TEXT_TRANSFORMATION) || 
							lineTmp[1].startsWith(Constants.SECOND_PRINCIPAL_ERROR_TEXT_TRANSFORMATION.toUpperCase()) ||
							lineTmp[1].startsWith(Constants.THIRD_PRINCIPAL_ERROR_TEXT_TRANSFORMATION) || 
							lineTmp[1].startsWith(Constants.THIRD_PRINCIPAL_ERROR_TEXT_TRANSFORMATION.toUpperCase()) ||
							lineTmp[1].startsWith(Constants.QUARTER_PRINCIPAL_ERROR_TEXT_TRANSFORMATION) ||
							lineTmp[1].startsWith(Constants.QUARTER_PRINCIPAL_ERROR_TEXT_TRANSFORMATION.toUpperCase())) {
						tmp= tmp + lineTmp[1];
						query=true;
						if(lineTmp[1].endsWith(";")) {
							listQuery.add(tmp);
							tmp="";
						}
					}else {
						query=false;
//						System.err.println(lineTmp[1]);
					}
					
					if(lineTmp[1].startsWith("FAILED") || lineTmp[1].startsWith("failed")) {
						cell = row.createCell(1);
						cell.setCellValue(lineTmp[0]);
						cell = row.createCell(2);
						cell.setCellValue(lineTmp[1]);
//						System.out.println(listQuery);
//						for (int i = 0; i < listQuery.size(); i++) {
//							if(i==listQuery.size()-1) {
								cell = row.createCell(0);
								cell.setCellValue(listQuery.get(listQuery.size()-1));
//							}else {
								cell = row.createCell(3);
								cell.setCellValue(listQuery.get(listQuery.size()-2));
//							}
//						}
						listQuery = new ArrayList<String>();
						j++;
					}
				}
			} // fin del while
			
//			System.out.println(listQuery);
		
//			if(numArgs == 7) {
//				out = new FileOutputStream(Constants.XLS_FILE_PATH + "ErroresT_"+ country + "_" + 
//						year + "-" + month + "-" + day + ".xls");
//			 }else {
//				 out = new FileOutputStream(Constants.XLS_FILE_PATH + "Errores_"+ "todos_" + 
//						 year + "-" + month + "-" + day + ".xls");	
//			 }
			
		} // fin try
		catch (Exception e) {
			e.printStackTrace();
		} finally { // Se cierra el fichero
					// para otra operación, debe estar cerrado.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
//	private static Datos cleanLineError(String linea) {
//		String elimi = linea
//				.replace(Constants.TENTH_SECONDARY_ERROR_TEXT_,
//						"").replaceAll("\\);", "").replaceAll("'", "");
//		String[] arreglo = elimi.split(",");
//		Datos data = new Datos();
//		data.setPais(arreglo[0]);
//		data.setAgencia(arreglo[1]);
//		data.setTabla(arreglo[2]);
//		data.setFecha(arreglo[3]);
//		data.setFecha2(arreglo[5]);
//		data.setSqoop(arreglo[6]);
//
//		return data;
//	}

}
