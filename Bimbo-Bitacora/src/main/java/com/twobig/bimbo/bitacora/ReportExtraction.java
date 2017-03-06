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

public class ReportExtraction {
	
	public void GetReportExtraction(String fileNameExtraction,String country,
			String year,String month,String day,int numArgs){
		

		File file = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para leer
//			file = new File(fileNameExtraction);
			file = new File(Constants.FILE_ERRORS_PATH_EXTRACTIONS + fileNameExtraction);
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			int j = 1;
			String line = "";

			int c = 1;
			List<String> listMov = new ArrayList<String>();
			List<String> listSqoop = new ArrayList<String>();

			HSSFSheet sheet = MainBitacora.workbook.createSheet(Constants.FIRST_SHEET_NAME);
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
			cell.setCellValue(Constants.FIRST_COLUMN_NAME);
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(Constants.SECOND_COLUMN_NAME);
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(Constants.THIRD_COLUMN_NAME);
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(Constants.QUARTER_COLUMN_NAME);
			cell = row.createCell(4);
			cell.setCellStyle(styleRosa);
			cell.setCellValue(Constants.FIFTH_COLUMN_NAME);
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue(Constants.SIXTH_COLUMN_NAME);
			while ((line = br.readLine()) != null) {

				row = sheet.createRow(j);
				if (line.contains(Constants.FIRST_PRINCIPAL_ERROR_TEXT_EXTRACTION) || (line.contains(Constants.SECOND_PRINCIPAL_ERROR_TEXT_EXTRACTION))
						|| (line.contains(Constants.THIRD_PRINCIPAL_ERROR_TEXT_EXTRACTION))) {
					String array = line.substring(18);
					if (line
							.contains(Constants.FIRST_SECONDARY_ERROR_TEXT_EXTRACTION)) {
						String[] tableErrorArr = line
								.split(Constants.SECOND_SECONDARY_ERROR_TEXT_EXTRACTION);
						if (tableErrorArr[1].contains(Constants.THIRD_SECONDARY_ERROR_TEXT_EXTRACTION)) {
							tableErrorArr = tableErrorArr[1].split(Constants.THIRD_SECONDARY_ERROR_TEXT_EXTRACTION);
							listSqoop.add(Constants.MESSAGE_ERROR_TEXT_1_EXTRACTION
									+ tableErrorArr[0] + "\n");
						} else if (tableErrorArr[1].contains(Constants.QUARTER_SECONDARY_ERROR_TEXT_EXTRACTION)) {
							tableErrorArr = tableErrorArr[1].split(Constants.FIFTH_SECONDARY_ERROR_TEXT_EXTRACTION);
							listSqoop.add(Constants.MESSAGE_ERROR_TEXT_2_EXTRACTION
									+ tableErrorArr[1] + "\n");
						}
					} else {
						listSqoop.add(array + "\n");
					}
				} else if (line.contains(Constants.QUARTER_PRINCIPAL_ERROR_TEXT_EXTRACTION) || 
						line.contains(Constants.FIFTH_PRINCIPAL_ERROR_TEXT_EXTRACTION)) {
					
					if (line.contains(Constants.SIXTH_SECONDARY_ERROR_TEXT_EXTRACTION)) {
						String[] tablaErrorArr = line.split(Constants.THIRD_SECONDARY_ERROR_TEXT_EXTRACTION);
						listMov.add(Constants.MESSAGE_ERROR_TEXT_1_EXTRACTION
								+ tablaErrorArr[1] + "\n");
					} else if (line.contains(Constants.SEVENTH_SECONDARY_ERROR_TEXT_EXTRACTION)) {
						String[] tableErrorArr = line.split(Constants.EIGHTH_SECONDARY_ERROR_TEXT_EXTRACTION);
						if (tableErrorArr[1].contains(Constants.NINETH_SECONDARY_ERROR_TEXT_EXTRACTION)) {
							tableErrorArr = tableErrorArr[1].split(Constants.NINETH_SECONDARY_ERROR_TEXT_EXTRACTION);
							listMov.add(Constants.MESSAGE_ERROR_TEXT_3_EXTRACTION
									+ tableErrorArr[0] + "en "
									+ tableErrorArr[1] + "\n");
						} else {
							listMov.add(Constants.MESSAGE_ERROR_TEXT_3_EXTRACTION
									+ tableErrorArr[1] + "\n");
						}
					} else {
						listMov.add(line + "\n");
					}
				}

				if (line.contains(Constants.SQOOP_TEXT)) {
					Datos data = cleanLineError(line);

					cell = row.createCell(0);
					cell.setCellValue(data.getFecha());
					cell = row.createCell(1);
					cell.setCellValue(data.getPais());
					cell = row.createCell(2);
					cell.setCellValue(data.getAgencia());
					cell = row.createCell(3);
					cell.setCellValue(data.getTabla());
					cell = row.createCell(4);
					cell.setCellStyle(styleRosa);
					cell.setCellValue(Constants.SQOOP_TEXT);
					// listaSqoop.add(linea);
					if (listMov.size() > 0) {
						//System.out.println(listMov);
						for (int i = 0; i < listMov.size(); i++) {
							cell = row.createCell(i + 5);
							cell.setCellValue(listMov.get(i));
						}
					}else if (listSqoop.size() > 0) {
						//System.out.println(listSqoop);
						for (int i = 0; i < listSqoop.size(); i++) {
							cell = row.createCell(i + 5);
							cell.setCellValue(listSqoop.get(i));
						}
					}
					listMov = new ArrayList<String>();
					listSqoop = new ArrayList<String>();
					j++;

				} else if (line.contains(Constants.MOVE_TEXT)) {
					// System.out.println( c + ": MOVE " + linea +"***");
					Datos data = cleanLineError(line);
					cell = row.createCell(0);
					cell.setCellValue(data.getFecha());
					cell = row.createCell(1);
					cell.setCellValue(data.getPais());
					cell = row.createCell(2);
					cell.setCellValue(data.getAgencia());
					cell = row.createCell(3);
					cell.setCellValue(data.getTabla());
					cell = row.createCell(4);
					cell.setCellStyle(styleRosa);
					cell.setCellValue(Constants.MOVE_TEXT);

					// listaMov.add(linea);
					if (listMov.size() > 0) {
						//System.out.println(listMov);
						for (int i = 0; i < listMov.size(); i++) {
							cell = row.createCell(i + 5);
							cell.setCellValue(listMov.get(i));
						}
					}else if (listSqoop.size() > 0) {
						//System.out.println(listSqoop);
						for (int i = 0; i < listSqoop.size(); i++) {
							cell = row.createCell(i + 5);
							cell.setCellValue(listSqoop.get(i));
						}
					}
					listMov = new ArrayList<String>();
					listSqoop = new ArrayList<String>();
					j++;
				}

			} // fin del while
		
//			OutputStream out;
//			if(numArgs == 4) {
//				out = new FileOutputStream(Constants.XLS_FILE_PATH + "Errores_"+ country + "_" + 
//						year + "-" + month + "-" + day + ".xls");
//			 }else {
//				 out = new FileOutputStream(Constants.XLS_FILE_PATH + "Errores_"+ "todos_" + 
//						 year + "-" + month + "-" + day + ".xls");	
//			 }
//			workbook.write(out);
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
	
	private static Datos cleanLineError(String linea) {
		String elimi = linea
				.replace(Constants.TENTH_SECONDARY_ERROR_TEXT_EXTRACTION,
						"").replaceAll("\\);", "").replaceAll("'", "");
		String[] arreglo = elimi.split(",");
		Datos data = new Datos();
		data.setPais(arreglo[0]);
		data.setAgencia(arreglo[1]);
		data.setTabla(arreglo[2]);
		data.setFecha(arreglo[3]);
		data.setFecha2(arreglo[5]);
		data.setSqoop(arreglo[6]);

		return data;
	}

}
